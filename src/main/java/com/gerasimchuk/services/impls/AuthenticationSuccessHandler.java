package com.gerasimchuk.services.impls;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String role = auth.getAuthorities().toString();
        String targetUrl = "";
        if(role.contains("MANAGER")) {
            targetUrl = "/managermainpage";
        } else if(role.contains("DRIVER")) {
            targetUrl = "/drivermainpage";
        }
        else if (role.contains("ADMIN")){
            targetUrl = "/adminmainpage";
        }
        return targetUrl;
    }
}
