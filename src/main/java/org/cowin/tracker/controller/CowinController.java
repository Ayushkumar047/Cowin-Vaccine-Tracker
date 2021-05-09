package org.cowin.tracker.controller;

import org.cowin.tracker.entity.Session;
import org.cowin.tracker.entity.Subscriber;
import org.cowin.tracker.model.ListResponse;
import org.cowin.tracker.model.Search;
import org.cowin.tracker.service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cowin")
public class CowinController {

    private final AppointmentService appointmentService;

    public CowinController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    /*
     *   SEARCH VACCINES by pincode, date range, age group
     *
     * */

    @GetMapping("/vaccines/search")
    public ResponseEntity<ListResponse<Session>> vaccinesSearch(@Valid Search search) {
        return ResponseEntity.ok(new ListResponse<>(appointmentService.search(search)));
    }

    @GetMapping("/vaccines/subscribers")
    public ResponseEntity<ListResponse<Subscriber>> findSubscriberSessions() {
        return ResponseEntity.ok(new ListResponse<>(appointmentService.findSubscriberSessions()));
    }

    @GetMapping("/sync")
    public ResponseEntity sync() {
        appointmentService.scheduler();
        return ResponseEntity.ok().build();
    }
}
