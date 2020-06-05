/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: sanrenxing </p>
 */
package com.example.rabbitmq;

 
import java.nio.charset.StandardCharsets;

import lombok.extern.log4j.Log4j2;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
//@Component
@Log4j2
public class MsgConsumer {
    
    @RabbitListener(
            bindings =
            {
                    @QueueBinding(value = @Queue(value = RabbitConfig.FANOUT_QUEUE_NAME, durable = "true"),
                            exchange = @Exchange(value = RabbitConfig.FANOUT_EXCHANGE, type = "fanout"))
            })
    
    @RabbitHandler
    public void processFanoutMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Fanout message : " + msg);
    }

    //****************************************************
   

    @RabbitListener(
            bindings =
            {
                    @QueueBinding(value = @Queue(value = RabbitConfig.DIRECT_QUEUE_NAME, durable = "true"),
                            exchange = @Exchange(value = RabbitConfig.DIRECT_EXCHANGE),
                            key = RabbitConfig.DIRECT_ROUTINGKEY)
            })
    
    @RabbitHandler
    public void processDirectMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Direct message : " + msg);
    }

    //********************************************
    @RabbitListener(
            bindings =
            {
                    @QueueBinding(value = @Queue(value = RabbitConfig.TOPIC_QUEUE_NAME, durable = "true"),
                            exchange = @Exchange(value = RabbitConfig.TOPIC_EXCHANGE, type = "topic"),
                            key = RabbitConfig.TOPIC_ROUTINGKEY)
            })
    @RabbitHandler
    public void processTopicMsg(Message massage) {
        String msg = new String(massage.getBody(), StandardCharsets.UTF_8);
        log.info("received Topic message : " + msg);
    }
}
