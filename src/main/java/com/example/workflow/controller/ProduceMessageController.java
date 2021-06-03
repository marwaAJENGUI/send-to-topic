package com.example.workflow.controller;

import lombok.extern.slf4j.Slf4j;
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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
@RestController
@Slf4j
public class ProduceMessageController {
    @Autowired
    JmsProducer jmsProducer;
    static String basePath= System.getProperty("user.dir")+"\\src\\main\\resources";
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(value="/api/message")
    public Workflow sendMessage(@RequestBody Workflow workflow, @RequestHeader(value ="Authorization") String  authorization){   	
        log.info(">>>>>>> ProduceMessageController");
        log.info("Working Directory = " + System.getProperty("user.dir"));

        String directoryName = basePath+workflow.getDirPath();
        String fileName = basePath+workflow.getPath();

        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdirs();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
            log.info(directory+" created!");
        }

        File file = new File(fileName);
        try{
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            log.info("AbsoluteFile: "+file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(workflow.getXml());
            bw.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        WorkflowInfo workflowInfo = new WorkflowInfo(workflow);
        BroadcastMessage broadcastMessage=new BroadcastMessage(workflowInfo,authorization);
    	jmsProducer.sendMessage(broadcastMessage);
        return workflow;
    }
}
