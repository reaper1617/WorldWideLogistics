package com.gerasimchuk.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Date;

@Path("/mainservice")
public class MainService {

    public MainService() {
    }


    @GET
    @Produces("application/json")
    @Path("/get")
    public String get(){
        return "{" //
                + "'date': '" + new Date() + "'," //
                + "'location': '" + "London" + "'," //
                + "'info': '" + "Cool" + "'" //
                + "}";
    }

}
