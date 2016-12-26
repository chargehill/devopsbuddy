package com.devopsbuddy.test.integration;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by octavio on 12/26/16.
 */
public class AbstractServiceIntegrationTest {
    @Autowired
    protected UserService userService;


    protected User createUser(TestName testName) {
        Role role = new Role(RoleEnum.BASIC);
        Set<Role> roles = new HashSet<>();
        roles.add(role);

        String userName = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";
        User basicUser = UserUtils.createBasicUser(userName, email);

        return userService.createUser(basicUser, PlanEnum.BASIC, roles);
    }


}
