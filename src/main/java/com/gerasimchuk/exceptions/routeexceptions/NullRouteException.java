package com.gerasimchuk.exceptions.routeexceptions;

/** Null route Exception Class
 * @author Reaper
 * @version 1.0
 */

public class NullRouteException extends RouteException {


    public NullRouteException() {
        super("No such route!");
    }

    public NullRouteException(String message) {
        super(message);
    }
}
