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
import org.junit.Test;
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
public class RepositoriesIntegrationTest {
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


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
        Plan p = new Plan(PlanEnum.BASIC);
        planRepository.save(p);

        Role r = new Role(RoleEnum.BASIC);
        roleRepository.save(r);

        User user = UserUtils.createBasicUser();
        user.setPlan(p);
        user.add(r);
        User savedUser = userRepository.save(user);

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


}
