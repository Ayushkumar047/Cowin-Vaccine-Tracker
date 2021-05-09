package org.cowin.tracker.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.cowin.tracker.entity.Session;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Appointment {
    private List<Session> sessions;
    private List<Center> centers;
}
