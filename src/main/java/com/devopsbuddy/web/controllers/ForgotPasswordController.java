package com.devopsbuddy.web.controllers;

import com.devopsbuddy.backend.persistence.domain.backend.PasswordResetToken;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.service.EmailService;
import com.devopsbuddy.backend.service.PasswordResetTokenService;
import com.devopsbuddy.utils.UserUtils;
import com.devopsbuddy.backend.service.I18nService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by octavio on 12/26/16.
 */

@Controller
public class ForgotPasswordController {
    private static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordController.class);

    public static final String EMAIL_ADDRESS_VIEW_NAME = "forgotmypassword/emailForm";
    public static final String FORGOT_PASSWORD_URL_MAPPING = "/forgotmypassword";
    public static final String MAIL_SENT_KEY = "mailSent";
    public static final String CHANGE_PASSWORD_PATH = "/changeuserpassword" ;
    public static final String EMAIL_MESSAGE_TEXT_PROPERTY_NAME = "forgotmypassword.email.text";

    @Autowired
    private I18nService i18nService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenService;

    @Value("${webmaster.email}")
    private String webMasterEmail;

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.GET)
    public String forgotPasswordGet(){
        return EMAIL_ADDRESS_VIEW_NAME;
    }

    @RequestMapping(value = FORGOT_PASSWORD_URL_MAPPING, method = RequestMethod.POST)
    public String forgotPasswordPost(HttpServletRequest request, @RequestParam("email") String email, ModelMap model){
        PasswordResetToken resetToken = passwordResetTokenService.createPasswordResetTokenForEmail(email);
        if(resetToken == null){
            LOG.warn("Couldn't fing password reset token for email {}", email);
        } else{
            User user = resetToken.getUser();
            String token = resetToken.getToken();
            String resetPasswordUrl = UserUtils.createPasswordResetUrl(request,user.getId(),token);
            LOG.debug("Reset password URL {}", resetPasswordUrl);

            String emailText = i18nService.getMessage(EMAIL_MESSAGE_TEXT_PROPERTY_NAME, request.getLocale());
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setTo(user.getEmail());
            mailMessage.setSubject("[Devopsbuddy]: How to reset your password");
            mailMessage.setText(emailText + "\r\n\r\n" + resetPasswordUrl);
            mailMessage.setFrom(webMasterEmail);
            emailService.sendGenericEmailMessage(mailMessage);
        }
        model.addAttribute(MAIL_SENT_KEY, "true");

        return EMAIL_ADDRESS_VIEW_NAME;
    }

}
