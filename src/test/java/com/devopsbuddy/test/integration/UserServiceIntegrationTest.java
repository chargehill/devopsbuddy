package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by octavio on 12/24/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DevopsbuddyApplication.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;
    @Rule
    public TestName testName = new TestName();


    @Test
    public void testCreateNewUser() throws Exception{
        Role role = new Role(RoleEnum.BASIC);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        String userName = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";
        User basicUser = UserUtils.createBasicUser(userName, email);

        User user = userService.createUser(basicUser, PlanEnum.BASIC, roles);
        Assert.assertNotNull(user);
        Assert.assertNotNull(user.getId());

    }

}
