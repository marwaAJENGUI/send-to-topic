package com.example.workflow.jms.publisher;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.workflow.model.BroadcastMessage;
import com.example.workflow.model.Workflow;
import com.example.workflow.model.WorkflowInfo;

@Slf4j
@Component
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${topic}")
    private String topic;

    public void sendMessage(BroadcastMessage broadcastMessage){
        try{
            log.info(">>>>>>> Attempting Send message to Topic: "+ topic);
            log.info(">>>>>>> Workflow: "+ broadcastMessage);
            jmsTemplate.convertAndSend(topic, broadcastMessage);
        } catch(Exception e){
        	log.info(">>>>>> Recieved Exception during send Message: "+ e.getMessage());
        }
    }
}
