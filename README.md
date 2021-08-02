
# rabbitmq-demo

## 须知

- 项目跑之前检查端口是否可用
- 本地、服务器 mq 是否启动
- 如果报错 `AmqpException: No method found for class [B` ，把 `@RabbitListener(queuesToDeclare = @Queue(RabbitMQConfig.TOPIC_EXCHANGE_QUEUE_C))`放到方法上
  

- 文档：http://localhost:8088/doc.html
- rabbitmq 控制台： http://localhost:15672/     账号密码：guest
- 消息确认机制（TopicExchangeConsumerOrg类） 可结合控制台 调试

如下：
1. 观察details 这栏后面的消息
![](https://tva1.sinaimg.cn/large/008i3skNgy1gt2lv9a327j323i0rawiw.jpg)

2. 发送消息
![](https://tva1.sinaimg.cn/large/008i3skNgy1gt2lx3gbvaj31640u0dio.jpg)

3. 发送消息后会被 TopicExchangeConsumerOrg 类监听，这个监听的是 orgQueue 这个队列，交换机名称是 orgExchange
![](https://tva1.sinaimg.cn/large/008i3skNgy1gt2lzxnd5zj31860m8gni.jpg)
   
从请求 --> 消息被Exchange的四种类型 --> 监听队列被消费，可断点调试