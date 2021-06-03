package com.example.workflow.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.workflow.jms.publisher.JmsProducer;
import com.example.workflow.model.BroadcastMessage;
import com.example.workflow.model.Workflow;
import com.example.workflow.model.WorkflowInfo;
import com.example.workflow.services.FileService;

import lombok.extern.log4j.Log4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
@Log4j
@RestController
public class ProduceMessageController {
    @Autowired
    JmsProducer jmsProducer;
    @Autowired
    FileService fileService;
    static String basePath= System.getProperty("user.dir")+"\\src\\main\\resources";
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/api/message")
    public Workflow sendMessage(@RequestBody Workflow workflow, @RequestHeader(value ="Authorization") String  authorization){   	
        log.debug(">>>>>>>>>>> ProduceMessageController->sendMessage: start ...");
    	fileService.creatXMLFile(workflow);
        WorkflowInfo workflowInfo = new WorkflowInfo(workflow);
        BroadcastMessage broadcastMessage=new BroadcastMessage(workflowInfo,authorization);
    	jmsProducer.sendMessage(broadcastMessage);
        log.debug(">>>>>>>>>>> ProduceMessageController->sendMessage: end!");
        return workflow;
    }
}
