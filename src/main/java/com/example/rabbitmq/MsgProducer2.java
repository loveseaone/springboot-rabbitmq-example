/**
 * <p>Copyright: Copyright (c) 2020</p>
 * <p>Company: sanrenxing </p>
 */
package com.example.rabbitmq;

 
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MsgProducer2 {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendFanoutTestQueue( Map<String,Object> msg){
        
        CorrelationData correlationData = new CorrelationData(msg.get("messageId").toString());
        rabbitTemplate.convertAndSend(RabbitConfig.FANOUT_EXCHANGE,
                "", msg, message -> {
            message.getMessageProperties().setExpiration(String.valueOf(5*1000)); // 设置延迟毫秒值
            return message;
        },correlationData);
 
    }

    public void sendDirectTestQueue( Map<String,Object> msg){
        CorrelationData correlationData = new CorrelationData(msg.get("messageId").toString());
        rabbitTemplate.convertAndSend(RabbitConfig.DIRECT_EXCHANGE,
                RabbitConfig.DIRECT_ROUTINGKEY, msg,message->{
                    message.getMessageProperties().setExpiration(String.valueOf(5*1000));
                    return message;
                },correlationData);
    }

    public void sendTopicTestQueue(Map<String,Object> msg){
        CorrelationData correlationData = new CorrelationData(msg.get("messageId").toString());
        rabbitTemplate.convertAndSend(RabbitConfig.TOPIC_EXCHANGE,
                RabbitConfig.TOPIC_ROUTINGKEY, msg,message->{
                    message.getMessageProperties().setExpiration(String.valueOf(5*1000));
                    return message;
                },correlationData);
    }

    
}
