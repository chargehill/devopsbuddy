package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.web.controllers.ForgotPasswordController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by octavio on 12/24/16.
 */
public class UserUtils {


    private UserUtils() {
    }

    public static User createBasicUser(String userName, String email) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword("secret");
        user.setEmail(email);
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhone("123456789");
        user.setCountry("MX");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");
        return user;
    }

    public static String createPasswordResetUrl(HttpServletRequest request, long userId, String token) {
        String result = String.format("%s://%s:%d%s%s?id=%d&token=%s", request.getScheme(), request.getServerName(), request.getServerPort(),
                request.getContextPath(), ForgotPasswordController.CHANGE_PASSWORD_PATH, userId, token);
        return result;
    }
}
