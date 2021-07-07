package com.example.workflow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.example.workflow.jms.publisher.JmsProducer;
import com.example.workflow.model.Info;
import com.example.workflow.model.InfoRequest;
import com.example.workflow.model.Workflow;
import com.example.workflow.model.WorkflowInfo;
import com.example.workflow.services.FileService;
import com.example.workflow.services.WorkflowInfoService;

import lombok.extern.log4j.Log4j;

@Log4j
@CrossOrigin
@RestController
public class ProduceMessageController {
    @Autowired
    JmsProducer jmsProducer;
    @Autowired
    FileService fileService;
    @Autowired
    WorkflowInfoService workflowInfoService;
    static String basePath= System.getProperty("user.dir")+"\\src\\main\\resources";
    @PostMapping(value="/message")
    public WorkflowInfo sendWorkflowInfo(@RequestBody Workflow workflow, @RequestHeader(value ="Authorization") String  authorization){   	
        log.debug(">>>>>>>>>>> ProduceMessageController->sendMessage: start ...");
    	fileService.creatXMLFile(workflow);
    	WorkflowInfo workflowInfo = new WorkflowInfo(workflow);
    	workflowInfo=workflowInfoService.addWorkflowInfo(workflowInfo);
        workflowInfo=jmsProducer.sendMessage(workflowInfo);
        log.debug(">>>>>>>>>>> ProduceMessageController->sendMessage: end!");
        return workflowInfo;
    }
    
    @PostMapping(value="/info-request")
    public void sendInfoRequest( @RequestBody InfoRequest infoRequest){
    	jmsProducer.sendInfoRequest(infoRequest);
    }
    
}
