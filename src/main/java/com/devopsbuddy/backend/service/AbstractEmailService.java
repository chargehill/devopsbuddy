package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by octavio on 12/18/16.
 */
public  abstract class AbstractEmailService implements EmailService {

    @Value("${default.to.address}")
    private String defaultToAddress;

    protected SimpleMailMessage prepareMailMessageFromFeedback(FeedbackDTO feedback){
        SimpleMailMessage result = new SimpleMailMessage();
        result.setTo(defaultToAddress);
        result.setFrom(feedback.getEmail());
        result.setSubject("DevOpsBuddy: Feedback received from " + feedback.getFirstName() + " " +
        feedback.getLastName() + "!");
        result.setText(feedback.getFeedback());
        return result;
    }

    @Override
    public void sendFeedbackEmail(FeedbackDTO feedback) {
        SimpleMailMessage mailMessage = prepareMailMessageFromFeedback(feedback);
        sendGenericEmailMessage(mailMessage);
    }
}
