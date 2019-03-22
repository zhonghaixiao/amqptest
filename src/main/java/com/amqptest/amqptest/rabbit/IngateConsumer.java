package com.amqptest.amqptest.rabbit;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
	@RabbitListener(queues = "${mq.ingate.queue}")
	public class IngateConsumer {

	private static Logger logger = LoggerFactory.getLogger("gateway_mq");

	@RabbitHandler
	public void process(TradePayModelRes tradePayModelRes, Channel channel, Message message) {
		logger.info("收到进闸支付消息 {}", tradePayModelRes.toString());
		try {
			//do samothing
			channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
			logger.info("确认消费进闸支付消息 {}", tradePayModelRes.getOutTradeNo());
			logger.info("确认消费进闸支付消息 {}", tradePayModelRes.getBody());
		} catch (Exception e) {
			logger.info("进闸支付入库异常 {} - {}", tradePayModelRes.getOutTradeNo(), e);
		}
	}
}