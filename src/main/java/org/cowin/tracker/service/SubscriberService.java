package org.cowin.tracker.service;

import lombok.extern.slf4j.Slf4j;
import org.cowin.tracker.dao.SubscriberDAO;
import org.cowin.tracker.entity.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Slf4j
@Service
public class SubscriberService {
    @Autowired
    private SubscriberDAO subscriberDAO;

    public Subscriber register(Subscriber subscriber) {
        Subscriber savedSubscriber = subscriberDAO.findByEmail(subscriber.getEmail());
        if (savedSubscriber != null) {
            savedSubscriber.setPincodes(subscriber.getPincodes());
            savedSubscriber.setDistricts(subscriber.getDistricts());
            savedSubscriber.setVaccines(subscriber.getVaccines());
            savedSubscriber.setAgeGroups(subscriber.getAgeGroups());
            savedSubscriber.setFeeTypes(subscriber.getFeeTypes());
        } else {
            savedSubscriber = subscriber;
        }
        return subscriberDAO.save(savedSubscriber);
    }

    public void delete(Long subscriberId) {
        subscriberDAO.delete(subscriberId);
    }


    public List<Subscriber> findAll() {
        return subscriberDAO.findAll();
    }
}
