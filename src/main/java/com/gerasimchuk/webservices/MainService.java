package com.gerasimchuk.webservices;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.Order;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Path("/mainservice")
public class MainService {

    public MainService() {
    }


    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(){
        return "{" //
                + "'date': '" + new Date() + "'," //
                + "'location': '" + "London" + "'," //
                + "'info': '" + "Cool" + "'" //
                + "}";
    }

    @GET
    @Path("/orders")
    @Produces(MediaType.APPLICATION_JSON)
    public List<OrderDTO> getOrders(){

        List<OrderDTO> collection = new ArrayList<OrderDTO>();
//        collection.add(new Order());
//        collection.add(new Order());
//        collection.add(new Order());
//        collection.add(new Order());
        collection.add(new OrderDTO("id", "pN1","descr1", "status1", "at1", null));
        collection.add(new OrderDTO("id", "pN1","descr1", "status1", "at1", null));
        collection.add(new OrderDTO("id", "pN1","descr1", "status1", "at1", null));
        collection.add(new OrderDTO("id", "pN1","descr1", "status1", "at1", null));
        collection.add(new OrderDTO("id", "pN1","descr1", "status1", "at1", null));
        return collection;
    }

}
