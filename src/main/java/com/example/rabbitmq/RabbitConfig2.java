/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: sanrenxing </p>
 */
package com.example.rabbitmq;


import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

 

 

 

 
@Configuration
public class RabbitConfig2 {
   
    
    public static final String DELAY_QUEUE = "delay.queue";
    public static final String DELAY_EXCHANGE = "delay.exchange";
    public static final String DELAY_KEY = "delay.key";

    @Bean
    public CustomExchange delayExchange() {
            Map<String, Object> args = new HashMap<>();
            args.put("x-delayed-type", "direct");
            return new CustomExchange(DELAY_EXCHANGE, "x-delayed-message", true, false,args);
    }

    /**
     * 定义延时队列
     *
     */
    @Bean
    public Queue delayQueue() {
            return new Queue(DELAY_QUEUE, true);
    }

    @Bean
    public Binding binding() {
            return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_KEY).noargs();
    }
 
}
