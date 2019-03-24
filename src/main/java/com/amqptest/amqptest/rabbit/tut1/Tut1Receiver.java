package com.amqptest.amqptest.rabbit.tut1;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "hello")
public class Tut1Receiver {

    @RabbitHandler
    private void receive(String in){
        System.out.println(" [x] Received '" + in + "'");
    }

}
