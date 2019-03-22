package com.amqptest.amqptest.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
	 * RabbitMQ消息发送类
	 */
	@Component
	public class RabbitSender implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {

	private static final Logger logger = LoggerFactory.getLogger("gateway_mq");

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Value("${mq.exchange}")
	private String payExchange;

	@Value("${mq.ingate.queue}")
	private String ingateQueue;

	@PostConstruct
	private void init() {
		rabbitTemplate.setConfirmCallback(this);
		rabbitTemplate.setReturnCallback(this);
		rabbitTemplate.setChannelTransacted(true);
	}

	@Transactional
	public void sendIngateQueue(TradePayModelRes msg) {
		logger.info("进闸支付消息已发送 {}", msg.getOutTradeNo());
		rabbitTemplate.convertAndSend(payExchange, ingateQueue, msg);
	}

	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		if (ack) {
			logger.info("消息已确认 cause:{} - {}", cause, correlationData.toString());
		} else {
			logger.info("消息未确认 cause:{} - {}", cause, correlationData.toString());
		}
	}

	@Override
	public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
		logger.info("消息被退回 {}", message.toString());
	}
}