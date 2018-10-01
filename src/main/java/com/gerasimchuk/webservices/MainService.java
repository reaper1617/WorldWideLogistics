//package com.gerasimchuk.webservices;
//
//
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import java.util.Date;
//
//
//@Path("/mainservice")
//public class MainService {
//
////
////    private String url = "jdbc:mysql://localhost:3306/mywwldatabase?useUnicode=true&amp;useJDBCCompliantTimezoneShift=true&amp;useLegacyDatetimeCode=false&amp;serverTimezone=UTC";
////    private String username = "root";
////    private String password = "root";
//
//
//    public MainService() {
//
//    }
////
////    @GET
////    @Produces(value = MediaType.APPLICATION_JSON)
////    @Path("/get")
////    public Collection<Order> get(){
////        Collection<Order> collection = new ArrayList<>();
////        try (Connection connection = DriverManager.getConnection(url,username,password);
////             Statement statement = connection.createStatement()) {
////
////            ResultSet  resultSet = statement.executeQuery("select * from orders");
////            while (resultSet.next()){
////                int id = resultSet.getInt("id");
////                String pNum = resultSet.getString("personal_number");
////                String descr = resultSet.getString("description");
////            }
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
////
////
////
////
////        return collection;
////    }
//
//    @GET
//    @Produces("application/json")
//    @Path("/get")
//    public String get(){
//        return "{" //
//                + "'date': '" + new Date() + "'," //
//                + "'location': '" + "London" + "'," //
//                + "'info': '" + "Cool" + "'" //
//                + "}";
//    }
//}
