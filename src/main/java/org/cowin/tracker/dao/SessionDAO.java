package org.cowin.tracker.dao;

import org.cowin.tracker.entity.Session;
import org.cowin.tracker.entity.Subscriber;
import org.cowin.tracker.repository.SessionRepository;
import org.cowin.tracker.repository.SessionSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class SessionDAO {

    @Autowired
    private SessionRepository sessionRepository;

    public Integer delete(Long districtId, LocalDate from, LocalDate to) {
        return sessionRepository.deleteByDistrictIdAndDateBetween(districtId, from, to);
    }

    public void save(List<Session> sessions) {
        sessionRepository.saveAll(sessions);
    }

    public List<Session> search(Long pincode, LocalDate from, LocalDate to, Long ageGroup, String feeType) {
        return sessionRepository.findByPincodeAndDateBetweenAndMinAgeLimitAndFeeType(pincode, from, to, ageGroup, feeType);
    }

    public List<Session> search(Subscriber subscriber) {
        return sessionRepository.findAll(new SessionSpecification(subscriber));
    }

}
