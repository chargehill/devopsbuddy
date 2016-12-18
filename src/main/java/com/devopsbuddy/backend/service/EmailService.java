package com.devopsbuddy.backend.service;

import com.devopsbuddy.web.domain.frontend.FeedbackDTO;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by octavio on 12/18/16.
 */
public interface EmailService {
    public void sendFeedbackEmail(FeedbackDTO feedback);

    public void sendGenericEmailMessage(SimpleMailMessage message);
}
