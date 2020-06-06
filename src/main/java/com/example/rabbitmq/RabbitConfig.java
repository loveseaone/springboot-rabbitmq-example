/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: sanrenxing </p>
 */
package com.example.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 

 

 
@Configuration
public class RabbitConfig {
    //队列名
    public static final String FANOUT_QUEUE_NAME = "test_fanout_queue";
  
    public static final String FANOUT_EXCHANGE = "testFanoutExchange";

    public static final String DIRECT_QUEUE_NAME = "test_direct_queue";
    public static final String DIRECT_EXCHANGE = "testDirectExchange";
    public static final String DIRECT_ROUTINGKEY = "test";

    public static final String TOPIC_QUEUE_NAME = "test_topic_queue";
    public static final String TOPIC_EXCHANGE = "testTopicExchange";
    public static final String TOPIC_ROUTINGKEY = "test.*";

    //创建队列
    @Bean
    public Queue createFanoutQueue() {
        return new Queue(FANOUT_QUEUE_NAME);
    }

    
    //创建队列
    @Bean
    public Queue createDirectQueue() {
        return new Queue(DIRECT_QUEUE_NAME);
    }

    //创建队列
    @Bean
    public Queue createTopicQueue() {
        return new Queue(TOPIC_QUEUE_NAME);
    }

    //创建交换机
    @Bean
    public FanoutExchange defFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECT_EXCHANGE);
    }
    
    @Bean
    TopicExchange defTopicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }
    
    
    //队列与交换机进行绑定
    @Bean
    Binding bindingFanout() {
        return BindingBuilder.bind(createFanoutQueue()).
                to(defFanoutExchange());
    }


    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(createDirectQueue()).
                to(directExchange()).
                with(DIRECT_ROUTINGKEY);
    }

   

    @Bean
    Binding bindingTopic() {
        return BindingBuilder.bind(createTopicQueue()).
                to(defTopicExchange()).
                with(TOPIC_ROUTINGKEY);
    }
 
}
