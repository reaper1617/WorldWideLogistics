package com.gerasimchuk.services;

/**
 * Service for security
 * @author Reaper
 * @version 1.0
 */

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);

}
