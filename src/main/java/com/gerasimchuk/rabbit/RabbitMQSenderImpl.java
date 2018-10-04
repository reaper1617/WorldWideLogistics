package com.gerasimchuk.rabbit;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitMQSenderImpl implements RabbitMQSender {

    private final static String QUEUE_NAME = "hello";

    public RabbitMQSenderImpl() {
        init();
    }
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;
    private void init(){
        factory = new ConnectionFactory();
        factory.setHost("localhost");
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void sendMessage(String message) {
        try {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(" [x] Sent '" + message + "'");
    }

    public void close(){
        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }


}
