package com.example.rabbitmqdemo.web;

import com.example.rabbitmqdemo.rabbitmq.RabbitMQDemo;
import com.example.rabbitmqdemo.rabbitmq.RabbitMQService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author
 * @name RabbitMQController
 * @date 2020-07-21 23:04
 **/
@RestController
@RequestMapping("/mall/rabbitmq")
public class RabbitMQController {

    @Resource
    private RabbitMQService rabbitMQService;

    /**
     * 发送消息
     *
     * @author
     */

    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsg(msg);
    }

    /**
     * 发布消息
     *
     * @author
     */

    @PostMapping("/publish")
    public String publish(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsgByFanoutExchange(msg);
    }

    /**
     * 通配符交换机发送消息
     *
     * @author
     */

    @PostMapping("/topicSend")
    public String topicSend(@RequestParam(name = "msg") String msg,
                            @RequestParam(name = "routingKey") String routingKey) throws Exception {
        return rabbitMQService.sendMsgByTopicExchange(msg, routingKey);
    }

    /**
     * 通配符交换机发送消息
     *
     * @author
     */

    @PostMapping("/topicSendOrg")
    public String topicOrgSend(@RequestBody RabbitMQDemo request) throws Exception {
        return rabbitMQService.sendMsgByTopicExchange(request);
    }


    @PostMapping("/headersSend")
    @SuppressWarnings("unchecked")
    public String headersSend(@RequestParam(name = "msg") String msg,
                              @RequestParam(name = "json") String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        return rabbitMQService.sendMsgByHeadersExchange(msg, map);
    }
}
