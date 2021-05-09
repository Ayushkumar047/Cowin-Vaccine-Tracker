package org.cowin.tracker.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Email {
    private String from;
    private List<String> to;
    private String subject;
    private String body;
}
