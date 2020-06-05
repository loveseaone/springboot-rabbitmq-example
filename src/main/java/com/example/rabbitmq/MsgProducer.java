/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: sanrenxing </p>
 */
package com.example.rabbitmq;

 
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendFanoutTestQueue(String message){
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE,
                "", message);
    }

    public void sendDirectTestQueue(String message){
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE,
                RabbitConfig.DIRECT_ROUTINGKEY, message);
    }

    public void sendTopicTestQueue(String message){
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                RabbitConfig.TOPIC_ROUTINGKEY, message);
    }

    
}
