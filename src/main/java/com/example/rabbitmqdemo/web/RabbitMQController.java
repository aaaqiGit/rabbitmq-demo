package com.example.rabbitmqdemo.web;

import com.example.rabbitmqdemo.rabbitmq.RabbitMQDemo;
import com.example.rabbitmqdemo.rabbitmq.RabbitMQService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;


@RestController
@RequestMapping("/mall/rabbitmq")
@Api(value = "RabbitMQController",tags = "发送消息")
public class RabbitMQController {

    @Resource
    private RabbitMQService rabbitMQService;

    /**
     * 发送消息
     *
     * @author
     */
    @ApiOperation(value = "Direct Exchange")
    @PostMapping("/sendMsg")
    public String sendMsg(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsg(msg);
    }

    /**
     * 发布消息
     *
     * @author
     */
    @ApiOperation(value = "Fanout exchange")
    @PostMapping("/publish")
    public String publish(@RequestParam(name = "msg") String msg) throws Exception {
        return rabbitMQService.sendMsgByFanoutExchange(msg);
    }

    /**
     * 通配符交换机发送消息
     *
     * @author
     */
    @ApiOperation(value = "Topic Exchange")
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
    @ApiOperation(value = "test")
    @PostMapping("/topicSendOrg")
    public String topicOrgSend(@RequestBody RabbitMQDemo request) throws Exception {
        return rabbitMQService.sendMsgByTopicExchange(request);
    }

    @ApiOperation(value = "Headers Exchange")
    @PostMapping("/headersSend")
    @SuppressWarnings("unchecked")
    public String headersSend(@RequestParam(name = "msg") String msg,
                              @RequestParam(name = "json") String json) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(json, Map.class);
        return rabbitMQService.sendMsgByHeadersExchange(msg, map);
    }
}
