package com.amqptest.amqptest.config;

import com.amqptest.amqptest.rabbit.tut1.Tut1Receiver;
import com.amqptest.amqptest.rabbit.tut1.Tut1Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Tut1Config {

    @Qualifier("hello")
    @Bean
    public Queue hello(){
        return new Queue("hello");
    }

    @Bean
    public Tut1Receiver receiver(){
        return new Tut1Receiver();
    }

    @Bean
    public Tut1Sender sender(){
        return new Tut1Sender();
    }



}
