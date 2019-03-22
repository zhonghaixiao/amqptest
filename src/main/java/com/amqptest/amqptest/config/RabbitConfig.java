package com.amqptest.amqptest.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.transaction.RabbitTransactionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${mq.exchange}")
    private String payExchange;

    @Value("${mq.ingate.queue}")
    private String ingateQueue;

    @Bean
    public DirectExchange payExchange() {
        return new DirectExchange(payExchange,true,false);
    }

    @Bean
    public Queue ingateQueue() {
        return new Queue(ingateQueue,true);
    }

    @Bean
    public Binding ingateQueueBinding() {
        return BindingBuilder.bind(ingateQueue()).to(payExchange()).withQueueName();
    }

    /**
     * 配置启用rabbitmq事务
     * @param connectionFactory
     * @return
     */
    @Bean
    public RabbitTransactionManager rabbitTransactionManager(CachingConnectionFactory connectionFactory) {
        return new RabbitTransactionManager(connectionFactory);
    }


}
