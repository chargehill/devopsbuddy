package com.devopsbuddy.backend.service;

import com.devopsbuddy.backend.persistence.domain.backend.Plan;
import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.persistence.repositories.PlanRepository;
import com.devopsbuddy.backend.persistence.repositories.RoleRepository;
import com.devopsbuddy.backend.persistence.repositories.UserRepository;
import com.devopsbuddy.enums.PlanEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created by octavio on 12/24/16.
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


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


        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);

        user = userRepository.save(user);
        return user;
    }

    @Transactional
    public void updateUserPassword(long userId, String password){
        String encodedPassword = passwordEncoder.encode(password);
        userRepository.updateUserPassword(userId, encodedPassword);
        LOG.debug("Password updated successfully for user id {}", userId);
    }



    public User findUserByName(String userName){
        return userRepository.findByUserName(userName);
    }


    public User findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }


}
