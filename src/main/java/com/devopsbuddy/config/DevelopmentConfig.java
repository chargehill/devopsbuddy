package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.MockEmailService;
import org.h2.server.web.WebServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by octavio on 12/18/16.
 */

@Configuration
@Profile("dev")
@PropertySource("File:///Users/${user.name}/desktop/Udemy/FullStack/config/app-dev.properties")
public class DevelopmentConfig {

    @Value("${stripe.test.private.key}")
    private String stripeDevKey;


    @Bean
    public ServletRegistrationBean h2ConsoleServletRegistration(){
        ServletRegistrationBean result = new ServletRegistrationBean(new WebServlet());
        result.addUrlMappings("/console/*");
        return result;
    }


    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }


    @Bean
    public String stripeKey(){
        return stripeDevKey;
    }
}
