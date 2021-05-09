package org.cowin.tracker.api;

import org.cowin.tracker.entity.District;
import org.cowin.tracker.model.Appointment;
import org.cowin.tracker.model.DistrictResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "CowinClient",
        url = "${cowin.base.url}",
        configuration = ClientConfig.class)
public interface CowinClient {

    @GetMapping(value = "/v2/appointment/sessions/public/findByPin", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Appointment> appointmentByPIN(@RequestParam Long pincode, @RequestParam String date);

    @GetMapping(value = "/v2/appointment/sessions/public/findByDistrict", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Appointment> appointmentByDistrict(@RequestParam("district_id") Long districtId, @RequestParam String date);

    @GetMapping(value = "/v2/appointment/sessions/public/calendarByDistrict", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Appointment> appointmentCalendarByDistrict(@RequestParam("district_id") Long districtId, @RequestParam String date);

    @GetMapping(value = "/v2/admin/location/districts/{stateId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<DistrictResponse> getDistricts(@PathVariable Long stateId);
}