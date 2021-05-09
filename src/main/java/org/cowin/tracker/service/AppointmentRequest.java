package org.cowin.tracker.service;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.cowin.tracker.api.CowinClient;
import org.cowin.tracker.entity.Session;
import org.cowin.tracker.model.Appointment;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

@Slf4j
@Getter
public class AppointmentRequest implements Callable<Appointment> {
    private final CowinClient cowinClient;
    private final Long districtId;
    private final String date;

    public AppointmentRequest(CowinClient cowinClient, Long districtId, LocalDate date) {
        this.cowinClient = cowinClient;
        this.districtId = districtId;
        this.date = DateTimeFormatter.ofPattern("dd-MM-yyyy").format(date);
    }

    @Override
    public Appointment call() {
        try {
            Appointment appointment = cowinClient.appointmentCalendarByDistrict(districtId, date).getBody();
            if (appointment != null && !CollectionUtils.isEmpty(appointment.getCenters())) {
                List<Session> sessions = new ArrayList<>();
                appointment.getCenters()
                        .forEach(center -> center.getSessions().forEach(session -> {
                            session.copyCenter(center);
                            sessions.add(session);
                        }));
                appointment.setSessions(sessions);
            }
            return appointment;
        } catch (Exception e) {
            log.error("[ ** ERROR ** GET APPOINTMENT ** district={} date={}]", districtId, date);
            return null;
        }
    }
}
