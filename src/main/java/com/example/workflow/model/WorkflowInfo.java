package com.example.workflow.model;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
public class WorkflowInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2332038432199314268L;
	long id;
	String customer;
	String module;
	String name;
	long version;
	Date dateCreated;
	Date dateModified;
	String filePath;
	boolean draft;
	String action;
	
	public WorkflowInfo(Workflow workflow){
		this.customer= workflow.getCustomer();
		this.module= workflow.getModule();
		this.name= workflow.getName();
		this.version= workflow.getVersion();
		this.dateCreated = workflow.getDateCreated();
		this.dateModified=workflow.getDateModified();
		this.filePath=workflow.getPath();
		this.draft=workflow.isDraft();
		this.action=workflow.getAction();
	}
}