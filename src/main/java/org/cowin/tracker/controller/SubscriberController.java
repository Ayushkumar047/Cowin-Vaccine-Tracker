package org.cowin.tracker.controller;

import org.cowin.tracker.entity.Subscriber;
import org.cowin.tracker.model.ListResponse;
import org.cowin.tracker.service.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    @Autowired
    private SubscriberService subscriberService;

    @GetMapping
    public ResponseEntity<ListResponse<Subscriber>> list() {
        return ok(new ListResponse<>(subscriberService.findAll()));
    }

    @PostMapping
    public ResponseEntity<Subscriber> register(@Valid @RequestBody Subscriber subscriber) {
        return ok(subscriberService.register(subscriber));
    }

    @DeleteMapping("/{subscriberId}")
    public ResponseEntity delete(@PathVariable Long subscriberId) {
        subscriberService.delete(subscriberId);
        return ok().build();
    }
}
