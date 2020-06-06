/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: sanrenxing </p>
 */
package com.example.rabbitmq;

 
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgProducer2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

   

    public void sendTopicTestQueue(String msg){
         
          rabbitTemplate.convertAndSend(
                RabbitConfig2.DELAY_EXCHANGE,
                RabbitConfig2.DELAY_KEY,
                msg,
                message -> {
                    message.getMessageProperties().setDelay(6000);// 单位 毫秒
                    return message;
                });
        
 
    }
}
