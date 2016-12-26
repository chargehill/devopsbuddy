package com.devopsbuddy.test.integration;

import com.devopsbuddy.DevopsbuddyApplication;
import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Set;

/**
 * Created by octavio on 12/24/16.
 */

@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = DevopsbuddyApplication.class)
@SpringBootTest(classes = DevopsbuddyApplication.class)
public class UserIntegrationTest extends AbstractIntegrationTest{


    @Rule
    public TestName testName = new TestName();


    @Before
    public void init(){
        Assert.assertNotNull(planRepository);
        Assert.assertNotNull(roleRepository);
        Assert.assertNotNull(userRepository);
    }

    @Test
    public void testCreateNewPlan() throws Exception{
        Plan basicPlan = new Plan(PlanEnum.BASIC);
        planRepository.save(basicPlan);
        Plan plan = planRepository.findOne(PlanEnum.BASIC.getId());
        Assert.assertNotNull(plan);
    }


    @Test
    public void testCreateNewRole() throws Exception{
        Role basicRole = new Role(RoleEnum.BASIC);
        roleRepository.save(basicRole);
        Role role = roleRepository.findOne(RoleEnum.BASIC.getId());
        Assert.assertNotNull(role);
    }

    @Test
    public void createNewUser(){
        String userName = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        User savedUser = createUser(userName, email);

        User retrievedUser = userRepository.findOne(savedUser.getId());
        Assert.assertNotNull(retrievedUser);
        Assert.assertTrue(retrievedUser.getId() != 0);
        Assert.assertNotNull(retrievedUser.getPlan());
        Assert.assertNotNull(retrievedUser.getPlan().getId());
        Set<Role> roles = retrievedUser.getRoles();
        Assert.assertTrue(roles.size()== 1);

        for (Role role: roles) {
            Assert.assertNotNull(role.getId());
        }
    }



    @Test
    public void testDeleteUser() throws Exception{
        String userName = testName.getMethodName();
        String email = testName.getMethodName() + "@devopsbuddy.com";

        User user = createUser(userName, email);
        userRepository.delete(user.getId());
    }





}
