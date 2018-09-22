package com.gerasimchuk.controllers;


import com.gerasimchuk.entities.User;
import com.gerasimchuk.repositories.UserRepository;
import com.gerasimchuk.services.impls.AuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/** User Controller
 * @author Reaper
 * @version 1.0
 */

@Controller
public class UserController {

    private UserRepository userRepository;
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    private static final org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(UserController.class);

    @Autowired
    public UserController(UserRepository userRepository, AuthenticationSuccessHandler authenticationSuccessHandler) {
        this.userRepository = userRepository;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
    }

    @RequestMapping(value = {"/","/index"}, method = RequestMethod.GET)
    public String index() {
        log.info("Controller: UserController, metod = index,  action = \"/\", request = GET");
//        authenticationSuccessHandler.
        return "redirect: /login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        log.info("Controller: UserController, metod = login,  action = \"/login\", request = GET");
        return "/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam(value = "login_error", required = false)String error, Model ui){
        log.info("Controller: UserController, metod = login,  action = \"/login\", request = POST");
        if (error!=null){
            log.error("Login error during executing login method");
            ui.addAttribute("actionFailed", "Login error during executing login method!");
            return "failure";
        }
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User u = userRepository.getByPersonalNumber(authentication.getName());
//        if (u == null) {
//            log.error("Error: no such user!");
//            ui.addAttribute("actionFailed", "Error: no such user!");
//            return "failure";
//        }
//        if(u.getDriver() != null){
//            log.info("Driver" + u.getPersonalNumber() + "successfully logged");
//            return "/driver/drivermainpage";
//        }
//        if (u.getManager() != null){
//            log.info("Manager" + u.getPersonalNumber() + "successfully logged");
//            return "/manager/managermainpage";
//        }
//        if (u.getAdmin() != null){
//            log.info("Admin" + u.getPersonalNumber() + "successfully logged");
//            return "admin/adminmainpage";
//        }
        return "failure";
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
