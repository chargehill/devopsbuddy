package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;
import java.util.List;

/**
 * Created by octavio on 12/18/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private static final String[] PUBLIC_PATTERNS= {"/webjars/**", "/css/**", "/js/**", "/images/**", "/", "/about/**", "/contact/**", "/error/**/*", "/console/**"};

    @Autowired
    private Environment env;

    @Autowired
    private UserSecurityService userSecurityService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<String> activeProfiles  = Arrays.asList(env.getActiveProfiles());
        if(activeProfiles.contains("dev")){
            http.csrf().disable();
            http.headers().frameOptions().disable();
        }


        http
                .authorizeRequests()
                .antMatchers(PUBLIC_PATTERNS).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/payload")
                .failureUrl("/login?error").permitAll()
                .and()
                .logout().permitAll();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userSecurityService);
    }


}
