package com.amqptest.amqptest.rabbit.tut1;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;

public class Tut1Sender {
    @Autowired
    private RabbitTemplate template;

    @Qualifier("hello")
    @Autowired
    private Queue queue;

//    @Scheduled(fixedDelay = 1, initialDelay = 1)
    public void send(){
        String message = "hello world!";
        template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
