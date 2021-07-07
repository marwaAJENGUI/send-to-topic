package com.example.workflow.jms.publisher;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.example.workflow.model.InfoRequest;
import com.example.workflow.model.WorkflowInfo;

@Slf4j
@Component
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    @Value("${topic}")
    private String topic;

    public WorkflowInfo sendMessage(WorkflowInfo workflowInfo) throws JmsException{
        log.info(">>>>>>> Attempting Send message to Topic: "+ topic);
        log.info(">>>>>>> Workflow: "+ workflowInfo);
        jmsTemplate.convertAndSend(topic, workflowInfo);
        return workflowInfo;
    }
    
    public void sendInfoRequest(InfoRequest infoRequest) throws JmsException{
        log.info(">>>>>>> Attempting Send infoRequest message to Topic: "+ topic);
        log.info(">>>>>>> Workflow: "+ infoRequest);
        jmsTemplate.convertAndSend(topic, infoRequest);
        log.info(">>>>>>> InfoRequest sent to Topic: "+ topic);
    }
}
