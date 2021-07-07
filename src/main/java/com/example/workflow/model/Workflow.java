package com.example.workflow.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class Workflow implements Serializable{
	long id ;
	String customer ;
	String module;
	String name ;
	long version ;
	Date dateCreated ;
	Date dateModified ;
	boolean draft;
	String xml;
	String action;
	String extension=".bpmn20.xml";
	
	public Workflow(WorkflowInfo workflowInfo){
		this.id=workflowInfo.getId();
		this.customer= workflowInfo.getCustomer();
		this.module= workflowInfo.getModule();
		this.name= workflowInfo.getName();
		this.version= workflowInfo.getVersion();
		this.dateCreated = workflowInfo.getDateCreated();
		this.dateModified=workflowInfo.getDateModified();
		this.draft=workflowInfo.isDraft();
		this.action=workflowInfo.getAction();
		this.extension=workflowInfo.getExtension();
	}
	
	public String getDirPath(){
        String file = (this.draft)?"draft":"valid";
        return "Workflow/"+this.module+"/"+this.customer+"/"+file;
    }
	public String getFilePath(){
        String file = (this.draft)?"draft":"valid";
        return "Workflow/"+this.module+"/"+this.customer+"/"+file+"/"+this.name+"_"+this.version+this.extension;
    }
}
