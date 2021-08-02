package com.example.rabbitmqdemo.rabbitmq;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.Objects;

@Component
public class RabbitmqConfirmCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    private Logger logger = LoggerFactory.getLogger(RabbitmqConfirmCallback.class);

    /**
     * 监听消息是否到达Exchange
     *
     * @param correlationData 包含消息的唯一标识的对象
     * @param ack             true 标识 ack，false 标识 nack
     * @param cause           nack 投递失败的原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            logger.info("消息投递成功~消息Id：{}", correlationData.getId());
        } else {
            logger.error("消息投递失败，Id：{}，错误提示：{}", correlationData.getId(), cause);
        }
    }

    @Override
    public void returnedMessage(ReturnedMessage returned) {
        logger.info("确认消息送到队列(Queue)结果：");
        logger.info("message body: {}", Objects.isNull(returned) ? "" : JSONObject.toJSONString(returned));
        logger.info("replyCode: {}", returned.getReplyCode());
        logger.info("replyText: {}", returned.getReplyText());
        logger.info("exchange: {}", returned.getExchange());
        logger.info("routingKey: {}", returned.getRoutingKey());
        logger.info("------------> end <------------");
    }

    @SuppressWarnings("unchecked")
    private <T> T byteToObject(byte[] bytes, Class<T> clazz) {
        T t;
        try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            t = (T) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return t;
    }
}
