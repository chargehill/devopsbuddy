package com.devopsbuddy;

import com.devopsbuddy.backend.persistence.domain.backend.Role;
import com.devopsbuddy.backend.persistence.domain.backend.User;
import com.devopsbuddy.backend.service.UserService;
import com.devopsbuddy.enums.PlanEnum;
import com.devopsbuddy.enums.RoleEnum;
import com.devopsbuddy.utils.UserUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DevopsbuddyApplication implements CommandLineRunner{

    /** The application logger **/

    private static final Logger LOG = LoggerFactory.getLogger(DevopsbuddyApplication.class);

    @Autowired
    private UserService userService;

	public static void main(String[] args) {
	    SpringApplication.run(DevopsbuddyApplication.class, args);
	}

    @Override
    public void run(String... strings) throws Exception {
	    User user = UserUtils.createBasicUser();
        Set<Role> roles = new HashSet<>();
        Role role = new Role(RoleEnum.BASIC);
        roles.add(role);

        LOG.debug("Creating user with user name {}", user.getUserName());
        User userCreated = userService.createUser(user, PlanEnum.BASIC, roles);
        LOG.info("User {} created", userCreated.getUserName());
    }
}
