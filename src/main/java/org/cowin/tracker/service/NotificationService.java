package org.cowin.tracker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.cowin.tracker.entity.Session;
import org.cowin.tracker.entity.Subscriber;
import org.cowin.tracker.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;

@Service
@Slf4j
public class NotificationService {
    private static final String EMAIL_SUBJECT = "VACCINE TRACKER";

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ITemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    public void sendEmail(String to, String body) {
        emailSender.send(
                Email.builder()
                        .from(from)
                        .to(Collections.singletonList(to))
                        .subject(EMAIL_SUBJECT)
                        .body(body)
                        .build()
        );
    }

    public void notify(Subscriber subscriber) {
        subscriber.getSessions().sort(Comparator.comparing(Session::getDate)
                .thenComparing(Session::getMinAgeLimit)
                .thenComparing(Session::getAvailableCapacity));
        Context context = new Context(Locale.US);
        context.setVariable("sessions", subscriber.getSessions());
        sendEmail(subscriber.getEmail(), templateEngine.process("AvailabilityTable", context));
    }
}
