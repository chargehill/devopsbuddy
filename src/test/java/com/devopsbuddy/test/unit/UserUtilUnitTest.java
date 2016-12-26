package com.devopsbuddy.test.unit;

import com.devopsbuddy.utils.UserUtils;
import com.devopsbuddy.web.controllers.ForgotPasswordController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.UUID;

/**
 * Created by octavio on 12/26/16.
 */
public class UserUtilUnitTest {
    private MockHttpServletRequest mockHttpServletRequest;

    @Before
    public void init(){
        mockHttpServletRequest = new MockHttpServletRequest();
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


}
