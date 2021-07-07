package com.example.workflow.services;

import java.io.IOException;

import com.example.workflow.model.Workflow;

public interface FileService {

	void creatXMLFile(Workflow workflow);

	void creatFile(String filePath, String fileContent);

	void creatDirectory(String directoryName);

	String getContent(String filePath) throws IOException;

}
