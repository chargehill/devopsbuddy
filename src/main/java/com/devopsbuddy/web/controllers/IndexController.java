package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by octavio on 11/27/16.
 */

@Controller
public class IndexController {

    @RequestMapping("/")
    public String home(){
        return "index";
    }

}
