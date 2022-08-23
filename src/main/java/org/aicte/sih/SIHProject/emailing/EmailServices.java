package org.aicte.sih.SIHProject.emailing;

import org.aicte.sih.SIHProject.api.college.dto.entities.CollegeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class EmailServices {

    @Autowired
    private SmtpEmailSender smtpEmailSender;

    public void sendCollegeRegistrationSuccessfulEmail(CollegeEntity college) {
        MessageEmail email = new MessageEmail();
        email.setSubject("Registration Successful | Export.App");
        email.setTo(Collections.singletonList(college.getEmail()));
        String body = college.getName() + " has been successfully registered!";
        email.setBody(body);
        email.setHtml(true);
        smtpEmailSender.sendMessage(email);
    }

}
