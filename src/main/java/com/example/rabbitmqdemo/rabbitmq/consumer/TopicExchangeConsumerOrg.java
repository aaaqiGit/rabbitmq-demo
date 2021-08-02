package com.example.rabbitmqdemo.rabbitmq.consumer;


import com.alibaba.fastjson.JSONObject;
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
@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.TOPIC_ORGQUEUE))
@Slf4j
public class TopicExchangeConsumerOrg {

//    @RabbitHandler
//    public void process(Map<String, Object> message) {
//        System.out.println("message:" + message.toString());
//    }

    @RabbitHandler
    public void process(JSONObject message) {
        System.out.println("message:" + JSONObject.toJSONString(message));
    }

}
