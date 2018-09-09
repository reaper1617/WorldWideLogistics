package com.gerasimchuk.services.impls;


import com.gerasimchuk.services.interfaces.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


/**
 * Implementation of {@link SecurityService} interface
 * @author Reaper
 * @version 1.0
 */

@Service
public class SecurityServiceImpl implements SecurityService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SecurityServiceImpl.class);

    @Autowired
    public SecurityServiceImpl(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails){
            return ((UserDetails) userDetails).getUsername();
        }

        return null;
    }

    public void autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
        authenticationManager.authenticate(authenticationToken);
        if (authenticationToken.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            logger.debug(String.format("Successfully logged as %s", username));
        }

    }
}
