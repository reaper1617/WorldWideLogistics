package com.gerasimchuk.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/** User Controller
 * @author Reaper
 * @version 1.0
 */

@Controller
public class UserController {

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserController.class);


    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index() {
        log.info("Controller: UserController, metod = index,  action = \"/\", request = GET");
        return "/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        log.info("Controller: UserController, metod = login,  action = \"/login\", request = GET");
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam(value = "login_error", required = false)String error){
        log.info("Controller: UserController, metod = login,  action = \"/login\", request = POST");
        if (error!=null){
            log.info("Login error during executing login method");
            return "/error";
        }
        log.info("Login successful");
        return "/login/logged";
    }

    @RequestMapping(value = "/logged", method = RequestMethod.GET)
    public String logged(){
        log.info("Controller: UserController, metod = logged,  action = \"/logged\", request = GET");
        return "/login/logged";
    }

    @RequestMapping(value = "/error", method = RequestMethod.GET)
    public String error(){
        log.info("Controller: UserController, metod = error,  action = \"/error\", request = GET");
        return "/error";
    }
}
