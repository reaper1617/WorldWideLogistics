package com.gerasimchuk.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {


    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(MainController.class);


    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index() {
        log.info("In method index of main controller");

        return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam(value = "login_error", required = false)String error){
        if (error!=null) return "/error";
        return "/login/logged";
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public String logged(){
        return "/login/logged";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(){
        return "/error";
    }
}
