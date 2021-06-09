package com.example.workflow.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Service;

import com.example.workflow.model.Workflow;
import com.example.workflow.model.WorkflowInfo;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class FileService {
	static String basePath = System.getProperty("user.dir") + "\\src\\main\\resources\\";

	public void creatDirectory(String directoryName) {
		log.debug(">>>>>>>>>>> FileService->creatDirectory("+ directoryName+") : creation xml file ...");
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdirs();
			// If you require it to make the entire directory path including parents,
			// use directory.mkdirs(); here instead.
			log.info(">>>>>>>>>>> FileService->creatDirectory("+directory + ") directory created!");
		}

	}

	public void creatFile( String filePath, String fileContent) {
		log.debug(">>>>>>>>>>> FileService->creatFile( filePath, fileContent) : creation xml file ...");
		File file = new File(filePath);
		try {
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			log.info(">>>>>>>>>>> AbsoluteFile: " + file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(fileContent);
			bw.close();
			log.debug(">>>>>>>>>>> FileService->creatFile( filePath, fileContent) : xml file created!");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}
	
	public void creatXMLFile (Workflow workflow) {
		log.debug("FileService->creatXMLFile( workflow) : creation xml file ...");
		this.creatDirectory(basePath+workflow.getDirPath());
		this.creatFile(basePath+workflow.getFilePath(), workflow.getXml());
		log.debug("FileService->creatXMLFile( workflow) : xml file created ...");
	}
}
