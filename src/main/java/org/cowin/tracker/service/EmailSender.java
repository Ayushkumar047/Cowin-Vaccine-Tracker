package org.cowin.tracker.service;

import lombok.extern.slf4j.Slf4j;
import org.cowin.tracker.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.stream.IntStream;

@Component
@Slf4j
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void send(Email email) {
        MimeMessagePreparator preparator =
                mimeMessage -> {
                    MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false);
                    helper.setTo(getEmails(email.getTo()));
                    helper.setFrom(new InternetAddress(email.getFrom()));
                    helper.setSubject(email.getSubject());
                    helper.setText(email.getBody(), true);
                };
        try {
            mailSender.send(preparator);
        } catch (MailException ex) {
            log.error("Error sending email:", ex);
        }
    }

    private InternetAddress[] getEmails(List<String> emails) {
        InternetAddress[] addresses = new InternetAddress[emails.size()];
        IntStream.range(0, emails.size())
                .forEach(
                        index -> {
                            String email = emails.get(index);
                            try {
                                InternetAddress address = new InternetAddress(email);
                                addresses[index] = address;
                            } catch (AddressException e) {
                                log.error("Invalid email:{}", email);
                            }
                        });
        return addresses;
    }
}
