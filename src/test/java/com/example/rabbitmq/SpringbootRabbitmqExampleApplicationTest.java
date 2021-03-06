package com.example.rabbitmq;
 
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.extern.log4j.Log4j2;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j2
public class SpringbootRabbitmqExampleApplicationTest {

    @Autowired
    MsgProducer msgProducer;
    @Autowired
    MsgProducer2 msgProducer2;
  
    
    @Test
    public void testMsgProducer(){
        
      String message=initMessage();
      
      while (true){
          try {
            Thread.sleep(1);
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                   
                   msgProducer.sendFanoutTestQueue(message);
                   msgProducer.sendDirectTestQueue(message);
                   msgProducer.sendTopicTestQueue(message);
                   msgProducer2.sendTopicTestQueue(message);
                }
            }).start();
            log.info("message:{}",message);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
          
      }
    }
    
    @Test
    public void testMsgProducer2(){
        
      String message=initMessage();
      
      while (true){
         
            new Thread(new Runnable() {
                
                @Override
                public void run() {
                   
                   msgProducer.sendFanoutTestQueue(message);
                   msgProducer.sendDirectTestQueue(message);
                   msgProducer.sendTopicTestQueue(message);
                   msgProducer2.sendTopicTestQueue(message);
                }
            }).start();
            log.info("message:{}",message);
          
      }
    }
    
    public String initMessage(){

        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        return map.toString();
    }
    
    
}

 