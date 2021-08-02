package com.example.rabbitmqdemo.rabbitmq.consumer;


import com.example.rabbitmqdemo.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: aqi
 * @Date: 2021/7/29 13:15
 * @Description:
 */
@Component
@Slf4j
public class TopicExchangeConsumerOrg {

//    @RabbitHandler
//    public void process(Map<String, Object> message) {
//        System.out.println("message:" + message.toString());
//    }

    @RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.TOPIC_ORGQUEUE))
    @RabbitHandler
    public void process(String message) {
        System.out.println("message:" + message);
    }

}
