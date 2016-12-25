package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by octavio on 12/24/16.
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public User createUser(User user, PlanEnum planEnum, Set<Role> roles){
        Plan plan = new Plan(planEnum);
        if(!planRepository.exists(planEnum.getId())){
            planRepository.save(plan);
        }
        user.setPlan(plan);

        for (Role role: roles) {
            if(!roleRepository.exists(role.getId())){
                roleRepository.save(role);
            }
            user.add(role);
        }


        user = userRepository.save(user);
        return user;
    }


}
