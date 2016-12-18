package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by octavio on 12/17/16.
 */

@Controller
public class CopyController {

    @RequestMapping("/about")
    public String about(){
        return "copy/about";
    }

}
