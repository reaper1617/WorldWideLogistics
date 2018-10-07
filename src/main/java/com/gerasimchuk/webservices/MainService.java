package com.gerasimchuk.webservices;

import com.gerasimchuk.dto.OrderDTO;
import com.gerasimchuk.entities.Order;
import com.gerasimchuk.enums.OrderStatus;

import javax.jws.WebService;
import javax.servlet.annotation.WebServlet;
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


    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(MainService.class);
    public MainService() {
    }


    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String get(){
        LOGGER.info("In REST: /get");
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
        LOGGER.info("In REST: /orders");
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
            LOGGER.info("Trying to get connection");
            connection = DriverManager.getConnection(url,"root", "root");
            LOGGER.info("Connection:" + connection);
            LOGGER.info("Trying to create statement.");
            statement = connection.createStatement();
            LOGGER.info("Statement:" + statement);
            LOGGER.info("Trying to get result set");
            ResultSet resultSet = statement.executeQuery("select * from orders");
            LOGGER.info("Result set:" + resultSet);
            while(resultSet.next()){
                LOGGER.info("in result set iter:");
                System.out.println("///////////////////////////////////////////////////");
                int id = resultSet.getInt("id");
                LOGGER.info("Order id:" + id);
                String personalNumber = resultSet.getString("personal_number");
                LOGGER.info("PNUM:" + personalNumber);
                String description = resultSet.getString("description");
                LOGGER.info("DESCR:"+description);
                String date = resultSet.getString("date");
                LOGGER.info("DATE:" + date);
                String status = resultSet.getString("status"); //????
                LOGGER.info("STATUS" + status);
                System.out.println("///////////////////////////////////////////////////");
                collection.add(new OrderDTO(Integer.toString(id),personalNumber,description,status,null,null));
            }
        } catch (SQLException e) {
            LOGGER.error("Catched sqlEx");
            e.printStackTrace();
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



        return collection;
    }

}
