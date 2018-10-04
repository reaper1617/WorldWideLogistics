package com.gerasimchuk.webservices;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.enums.OrderStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.*;
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
//        collection.add(new OrderDTO("id", "pN1","descr1", "status1", "at1", null));
//        collection.add(new OrderDTO("id2", "pN2","descr2", "status2", "at1", null));
//        collection.add(new OrderDTO("id3", "pN3","descr3", "status3", "at1", null));
//        collection.add(new OrderDTO("id4", "pN4","descr4", "status4", "at1", null));
//        collection.add(new OrderDTO("id5", "pN5","descr5", "status5", "at1", null));
//        collection.add(new OrderDTO("id5", "pN5","descr5", "status5", "at1", null));
//        collection.add(new OrderDTO("id5", "pN5","descr5", "status5", "at1", null));

        //
        String url = "jdbc:mysql://localhost:3306/mywwldatabase"+
                "?verifyServerCertificate=false"+
                "&useSSL=false"+
                "&requireSSL=false"+
                "&useLegacyDatetimeCode=false"+
                "&amp"+
                "&serverTimezone=UTC";


        Connection connection = null;
        Statement statement = null;
        try{
            connection = DriverManager.getConnection(url,"root", "root");
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orders");

            while(resultSet.next()){

                int id = resultSet.getInt("id");
                String personalNumber = resultSet.getString("personal_number");
                String description = resultSet.getString("description");
                String date = resultSet.getString("date");
                int status = resultSet.getInt("status"); //????
                collection.add(new OrderDTO(Integer.toString(id),personalNumber,description,Integer.toString(status),null,null));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        finally {
            if (statement !=null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!= null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }




        //


        return collection;
    }

}
