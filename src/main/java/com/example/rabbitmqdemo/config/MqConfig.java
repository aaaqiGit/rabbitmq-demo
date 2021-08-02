//package com.example.rabbitmqdemo.config;
//
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.core.TopicExchange;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.beans.BeansException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.beans.factory.config.BeanPostProcessor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//
//@Configuration
//public class MqConfig implements BeanPostProcessor {
//
//    @Resource
//    private RabbitAdmin rabbitAdmin;
//
//    @Value("${rabbitmq.topic}")
//    public String topic;
//
//    @Value("${rabbitmq.exchange}")
//    public String exchange;
//
//    @Value("${rabbitmq.routing}")
//    public String routing;
//
//    @Value("${rabbitmq.queue.durable}")
//    public boolean queueDurable;
//    @Value("${rabbitmq.queue.exclusive}")
//    public boolean queueExclusive;
//    @Value("${rabbitmq.queue.autoDelete}")
//    public boolean queueAutoDelete;
//    @Value("${rabbitmq.durable}")
//    public boolean exchangeDurable;
//    @Value("${rabbitmq.autoDelete}")
//    public boolean exchangeAutoDelete;
//
//    @Bean
//    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
//        // 只有设置为 true，spring 才会加载 RabbitAdmin 这个类
//        rabbitAdmin.setAutoStartup(true);
//        return rabbitAdmin;
//    }
//
//    @Bean
//    public Queue rabbitmqDemoDirectQueue() {
//        /**
//         * 1、name:    队列名称
//         * 2、durable: 是否持久化
//         * 3、exclusive: 是否独享、排外的。如果设置为true，定义为排他队列。则只有创建者可以使用此队列。也就是private私有的。
//         * 4、autoDelete: 是否自动删除。也就是临时队列。当最后一个消费者断开连接后，会自动删除。
//         * */
////        return new Queue(topic, queueDurable, queueExclusive, queueAutoDelete);
//        return new Queue("orgQueue", true, false, false);
//    }
//
//    @Bean
//    public TopicExchange rabbitmqDemoTopicExchange() {
//        //配置TopicExchange交换机
//        return new TopicExchange("orgExchange", true, false);
//    }
//
//    @Bean
//    public Binding bindDirect() {
//        //链式写法，绑定交换机和队列，并设置匹配键
//        return BindingBuilder
//                //绑定队列
//                .bind(rabbitmqDemoDirectQueue())
//                //到交换机
//                .to(rabbitmqDemoTopicExchange())
//                //并设置匹配键
//                .with(routing);
//    }
//
//    @Override
//    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        rabbitAdmin.declareExchange(rabbitmqDemoTopicExchange());
//        rabbitAdmin.declareQueue(rabbitmqDemoDirectQueue());
//        return null;
//    }
//
//}
