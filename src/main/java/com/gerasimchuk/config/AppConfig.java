package com.gerasimchuk.config;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
 @EnableRabbit
 public class AppConfig {
    @Autowired
    ConnectionFactory connectionFactory;

     @Bean
     public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
       SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();

       //  ConnectionFactory connectionFactory = new CachingConnectionFactory("192.168.0.36");

       factory.setConnectionFactory(connectionFactory);
       factory.setMaxConcurrentConsumers(5);
       return factory;
     }
     // other @Bean definitions
 }