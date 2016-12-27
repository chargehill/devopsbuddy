package com.devopsbuddy.test.unit;

import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.utils.UserUtils;
import com.devopsbuddy.web.controllers.ForgotPasswordController;
import com.devopsbuddy.web.domain.frontend.BasicAccountPayload;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import java.util.UUID;

/**
 * Created by octavio on 12/26/16.
 */
public class UserUtilUnitTest {
    private MockHttpServletRequest mockHttpServletRequest;

    private PodamFactory podamFactory;

    @Before
    public void init(){
        mockHttpServletRequest = new MockHttpServletRequest();
        podamFactory = new PodamFactoryImpl();
    }

    @Test
    public void testPasswordResetEmailUrlConstruction() throws Exception{
        mockHttpServletRequest.setServerPort(8080);

        String token = UUID.randomUUID().toString();
        long userId = 123456;

        String expectedUrl = String.format("http://localhost:8080%s?id=%d&token=%s", ForgotPasswordController.CHANGE_PASSWORD_PATH, userId, token);
        String actualUrl = UserUtils.createPasswordResetUrl(mockHttpServletRequest, userId, token);

        Assert.assertEquals(expectedUrl, actualUrl);
    }


    @Test
    public void mapWebUserToDomainUser(){
        BasicAccountPayload webUser = podamFactory.manufacturePojoWithFullData(BasicAccountPayload.class);
        webUser.setEmail("me@example.com");

        User user = UserUtils.fromWebUserToDomainUser(webUser);
        Assert.assertEquals(webUser.getUsername(), user.getUsername());
        Assert.assertEquals(webUser.getPassword(), user.getPassword());
        Assert.assertEquals(webUser.getFirstName(), user.getFirstName());
        Assert.assertEquals(webUser.getLastName(), user.getLastName());
        Assert.assertEquals(webUser.getEmail(), user.getEmail());
        Assert.assertEquals(webUser.getPhoneNumber(), user.getPhone());
        Assert.assertEquals(webUser.getCountry(), user.getCountry());
        Assert.assertEquals(webUser.getDescription(), user.getDescription());


    }


}
