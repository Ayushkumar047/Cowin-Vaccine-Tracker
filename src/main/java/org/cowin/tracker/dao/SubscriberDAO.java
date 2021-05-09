package org.cowin.tracker.dao;

import org.cowin.tracker.entity.Subscriber;
import org.cowin.tracker.repository.SubscriberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriberDAO {
    @Autowired
    private SubscriberRepository subscriberRepository;

    public Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    public Subscriber findByEmail(String email) {
        return subscriberRepository.findByEmail(email);
    }

    public void delete(Long subscriberId) {
        subscriberRepository.deleteById(subscriberId);
    }

    public Boolean delete(String email) {
        return subscriberRepository.deleteByEmail(email);
    }

    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }
}
