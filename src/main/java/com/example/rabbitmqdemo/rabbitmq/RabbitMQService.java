package com.example.rabbitmqdemo.rabbitmq;

import java.util.Map;

/**
 * @author
 * @name RabbitMQService
 * @date 2020-07-21 23:32
 **/
public interface RabbitMQService {

    String sendMsg(String msg) throws Exception;

    String sendMsgByFanoutExchange(String msg) throws Exception;

    String sendMsgByTopicExchange(String msg, String routingKey) throws Exception;

    String sendMsgByTopicExchange(RabbitMQDemo msg) throws Exception;

    String sendMsgByHeadersExchange(String msg, Map<String, Object> map) throws Exception;
}
