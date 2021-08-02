package com.example.rabbitmqdemo.rabbitmq.consumer;


import com.example.rabbitmqdemo.config.RabbitMQConfig;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author: aqi
 * @Date: 2021/7/29 13:15
 * @Description: 消息确认机制
 *
 * 可参考：
 * ACK https://blog.csdn.net/pan_junbiao/article/details/112956537
 * https://www.cnblogs.com/Javame/p/12131550.html
 */
@Component
@Slf4j
public class TopicExchangeConsumerOrg {

    @RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.TOPIC_ORGQUEUE))
    @RabbitHandler
    public void process(Message message, Channel channel) throws IOException {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            String msg = new String(message.getBody(), "UTF-8");
            System.out.println("message:" + msg);
            /**
             * 确认消息，参数说明：
             * long deliveryTag：唯一标识 ID。
             * boolean multiple：是否批处理，当该参数为 true 时，
             *
             * deliveryTag:该消息的index
             *
             * multiple：是否批量. true：将一次性ack所有小于deliveryTag的消息。
             *
             * 则可以一次性确认 deliveryTag 小于等于传入值的所有消息。
             */
            channel.basicAck(deliveryTag, true);

            /**
             * 否定消息，参数说明：
             * long deliveryTag：唯一标识 ID。
             * boolean multiple：是否批处理，当该参数为 true 时，
             * 则可以一次性确认 deliveryTag 小于等于传入值的所有消息。
             * boolean requeue：如果 requeue 参数设置为 true，
             * 则 RabbitMQ 会重新将这条消息存入队列，以便发送给下一个订阅的消费者；
             * 如果 requeue 参数设置为 false，则 RabbitMQ 立即会还把消息从队列中移除，
             * 而不会把它发送给新的消费者。
             */
            ///channel.basicNack(deliveryTag, true, false);
        } catch (Exception e) {
            log.error(e.getMessage());
            /**
             * 拒绝消息，参数说明：
             * long deliveryTag：唯一标识 ID。
             * boolean requeue：如果 requeue 参数设置为 true，
             *
             * deliveryTag:该消息的index。
             *
             * requeue：被拒绝的是否重新入队列。
             *
             * 则 RabbitMQ 会重新将这条消息存入队列，以便发送给下一个订阅的消费者；
             * 如果 requeue 参数设置为 false，则 RabbitMQ 立即会还把消息从队列中移除，
             * 而不会把它发送给新的消费者。
             */
            channel.basicReject(deliveryTag, true);


//            void basicNack(long deliveryTag, boolean multiple, boolean requeue)
//
//            throws IOException;
//
//            deliveryTag:该消息的index。
//
//            multiple：是否批量. true：将一次性拒绝所有小于deliveryTag的消息。
//
//            requeue：被拒绝的是否重新入队列。
        }
    }

}
