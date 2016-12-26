package com.devopsbuddy.test.integration;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;
import org.junit.rules.TestName;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by octavio on 12/25/16.
 */
public abstract class AbstractIntegrationTest {
    @Autowired
    protected PlanRepository planRepository;
    @Autowired
    protected RoleRepository roleRepository;
    @Autowired
    protected UserRepository userRepository;


    protected User createUser(String userName, String email){
        Plan p = new Plan(PlanEnum.BASIC);
        planRepository.save(p);

        Role r = new Role(RoleEnum.BASIC);
        roleRepository.save(r);

        User user = UserUtils.createBasicUser(userName, email);
        user.setPlan(p);
        user.add(r);
        User savedUser = userRepository.save(user);
        return savedUser;

    }

    protected User createUser(TestName testName){
        return createUser(testName.getMethodName(), testName.getMethodName() + "@devopsbuddy.com");
    }


}
