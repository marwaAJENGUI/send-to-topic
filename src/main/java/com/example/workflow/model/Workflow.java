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
	public String getDirPath(){
        String file = (this.draft)?"draft":"valid";
        return "\\Workflow\\"+this.customer+"\\"+this.module+"\\"+file;
    }
	public String getFilePath(){
        String file = (this.draft)?"draft":"valid";
        return "\\Workflow\\"+this.customer+"\\"+this.module+"\\"+file+"\\"+this.name+"_"+this.version+this.extension;
    }
}
