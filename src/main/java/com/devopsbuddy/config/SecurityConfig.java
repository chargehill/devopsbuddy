package com.devopsbuddy.config;

import com.devopsbuddy.backend.service.UserSecurityService;
import com.devopsbuddy.web.controllers.ForgotPasswordController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

/**
 * Created by octavio on 12/18/16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    private static final String[] PUBLIC_PATTERNS= {
            "/webjars/**",
            "/css/**",
            "/js/**",
            "/images/**",
            "/",
            "/about/**",
            "/contact/**",
            "/error/**/*",
            "/console/**",
            ForgotPasswordController.FORGOT_PASSWORD_URL_MAPPING,
            ForgotPasswordController.CHANGE_PASSWORD_PATH
    };

    @Autowired
    private Environment env;

    @Autowired
    private UserSecurityService userSecurityService;

    private static final String SALT = "4512we12rtktnfbvjh678*sedffgxnnerfhdkslfntghhjebfjsjennerfjjsrfdnerfklllj;hll";


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(12,new SecureRandom(SALT.getBytes()));
    }

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
        auth
                .userDetailsService(userSecurityService)
                .passwordEncoder(passwordEncoder());
    }


}
