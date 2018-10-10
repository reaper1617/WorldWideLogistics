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
            ResultSet resultSet = statement.executeQuery("select * from orders order by id desc limit 10");
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

    @GET
    @Path("/stats")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Integer> getStats(){
        LOGGER.info("In REST: /stats");
        List<Integer> list = new ArrayList<Integer>();
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
            //ResultSet resultSet = statement.executeQuery("select count (ALL *) from trucks;");
            ResultSet resultSet = statement.executeQuery("select count(*) from trucks");
            int trucksTotal = 0;
            while (resultSet.next()){
                trucksTotal =  resultSet.getInt(1);
            }
            ResultSet resultSet2 = statement.executeQuery("select count(*) from trucks t inner join orders o on t.id != o.assigned_truck_id");
            int trucksFree = 0;
            while (resultSet2.next()){
                trucksFree =  resultSet2.getInt(1);
            }

            ResultSet resultSet3 = statement.executeQuery("select count(*) from trucks t where t.state = 'NOT_READY'");
            int trucksNotReady = 0;
            while (resultSet3.next()){
                trucksNotReady =  resultSet3.getInt(1);
            }

            ResultSet resultSet4 = statement.executeQuery("select count(*) from trucks t inner join orders o on t.id = o.assigned_truck_id");
            int trucksExecOrder = 0;
            while (resultSet4.next()){
                trucksExecOrder=  resultSet4.getInt(1);
            }

            ResultSet resultSet5 = statement.executeQuery("select count(*) from drivers");
            int driversTotal = 0;
            while (resultSet5.next()){
                driversTotal =  resultSet5.getInt(1);
            }

            ResultSet resultSet6 = statement.executeQuery("select count(*) from drivers d where d.status = 'FREE'");
            int driversFree = 0;
            while (resultSet6.next()){
                driversFree =  resultSet6.getInt(1);
            }

            ResultSet resultSet7 = statement.executeQuery("select count(*) from drivers d where d.status != 'FREE'");
            int driversExecOrders = 0;
            while (resultSet7.next()){
                driversFree =  resultSet7.getInt(1);
            }
            list.add(trucksTotal);
            list.add(trucksFree);
            list.add(trucksNotReady);
            list.add(trucksExecOrder);

            list.add(driversTotal);
            list.add(driversFree);
            list.add(driversExecOrders);

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
        return list;
    }
}
