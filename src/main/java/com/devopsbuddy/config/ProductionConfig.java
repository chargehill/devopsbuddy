package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.SmtpEmailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by octavio on 12/18/16.
 */
@Configuration
@Profile("prod")
@PropertySource("file:///${user.home}/.devopsbuddy/app-prod.properties")
public class ProductionConfig {
    @Value("${stripe.prod.private.key}")
    private String stripeProdKey;

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

    @Bean
    public String stripeKey(){
        return stripeProdKey;
    }
}
