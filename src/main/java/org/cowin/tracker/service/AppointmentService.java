package org.cowin.tracker.service;

import lombok.extern.slf4j.Slf4j;
import org.cowin.tracker.api.CowinClient;
import org.cowin.tracker.dao.SessionDAO;
import org.cowin.tracker.entity.District;
import org.cowin.tracker.entity.Session;
import org.cowin.tracker.entity.Subscriber;
import org.cowin.tracker.model.Appointment;
import org.cowin.tracker.model.Search;
import org.cowin.tracker.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AppointmentService {

    @Autowired
    private CowinClient cowinClient;

    @Autowired
    private SessionDAO sessionDAO;

    @Autowired
    private SubscriberService subscriberService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private DistrictRepository districtRepository;


    @Scheduled(cron = "0 */5 * ? * *")
    public void scheduler() {
        log.info("[ ** Starting SYNC ** ]");
        List<District> districts = districtRepository.findAll();
        districts.parallelStream().forEach(district -> {
            LocalDate from = LocalDate.now();
            LocalDate to = LocalDate.now().plusMonths(1);
            sessionDAO.delete(district.getDistrictId(), from, to);
            List<Session> sessions = getAvailableSessions(district.getDistrictId(), from, to);
            sessionDAO.save(sessions);
        });
        log.info("[ ** Finished SYNC ** ]");
    }

    private void sendSubscriberMails() {
        List<Subscriber> subscribers = findSubscriberSessions();
        subscribers.forEach(subscriber -> {
            log.info("[ ** Notifying Subscriber: {} ** ]", subscriber.getEmail());
            notificationService.notify(subscriber);
        });
    }

    public List<Session> getAvailableSessions(Long districtId, LocalDate from, LocalDate to) {
        ExecutorService executorService = Executors.newFixedThreadPool(6);
        List<Session> availableSessions = new ArrayList<>();
        List<AppointmentRequest> appointmentRequests = new ArrayList<>();
        while (from.isBefore(to) || from.isEqual(to)) {
            AppointmentRequest appointmentRequest = new AppointmentRequest(cowinClient, districtId, from);
            appointmentRequests.add(appointmentRequest);
            from = from.plusDays(7);
        }
        try {
            List<Future<Appointment>> appointmentFutures = executorService.invokeAll(appointmentRequests);
            for (Future<Appointment> appointment : appointmentFutures) {
                List<Session> sessions = filterAvailableSessions(appointment.get(), districtId);
                if (!CollectionUtils.isEmpty(sessions)) {
                    availableSessions.addAll(sessions);
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            closeExecutorService(executorService);
        }
        return availableSessions;
    }

    private void closeExecutorService(ExecutorService executorService) {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    private List<Session> filterAvailableSessions(Appointment appointment, Long districtId) {
        return null == appointment || CollectionUtils.isEmpty(appointment.getCenters())
                ? Collections.emptyList()
                : appointment.getSessions()
                .stream()
                .peek(session -> session.setDistrictId(districtId))
                .filter(session -> session.getAvailableCapacity() > 0)
                .collect(Collectors.toList());
    }


    public List<Session> search(Search search) {
        List<Session> sessions = sessionDAO.search(
                search.getPincode(),
                search.getFrom(),
                search.getTo(),
                search.getAgeGroup(),
                search.getFeeType());
        return sessions;
    }

    public List<Subscriber> findSubscriberSessions() {
        List<Subscriber> subscribers = subscriberService.findAll();

        return subscribers.parallelStream().peek(subscriber -> {
            List<Session> sessions = sessionDAO.search(subscriber);
            subscriber.setSessions(sessions);
        }).filter(subscriber -> !CollectionUtils.isEmpty(subscriber.getSessions())).collect(Collectors.toList());
    }
}
