package com.devopsbuddy.utils;

import com.devopsbuddy.backend.persistence.domain.backend.User;

/**
 * Created by octavio on 12/24/16.
 */
public class UserUtils {


    private UserUtils() {
    }

    public static User createBasicUser() {
        User user = new User();
        user.setUserName("basicUser");
        user.setPassword("secret");
        user.setEmail("me@example.com");
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setPhone("123456789");
        user.setCountry("MX");
        user.setEnabled(true);
        user.setDescription("A basic user");
        user.setProfileImageUrl("https://blabla.images.com/basicuser");
        return user;
    }

}
