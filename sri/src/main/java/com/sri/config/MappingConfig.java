package com.sri.config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sri.service.imple.MappingService;
import com.sri.util.model.Identifier;
import com.sri.util.model.PIITag;

/*
 * Enable the multiple thread supporting for the RESTful API supporting
 * 
 * */

@Configuration
public class MappingConfig {
	@Autowired
	MappingService mappingService;
	
	@Bean("resolveConfigFile")
	public void resolveConfigFile() {
		ObjectMapper objectMapper = new ObjectMapper();

	    BufferedReader br;
	    try {
	      // Identifier
	      br = new BufferedReader(new FileReader(new File("identifier.config"))); 
	      StringBuilder sb = new StringBuilder();
	      String st = null;
	      while ((st = br.readLine()) != null) {
	    	  sb.append(st);
	      }
	      List<Identifier> identifierList = objectMapper.readValue(sb.toString(), new TypeReference<List<Identifier>>(){});
	      System.out.println(identifierList);
	      
	      // PII Tag
	      br = new BufferedReader(new FileReader(new File("piitag.config"))); 
	      sb.setLength(0);
	      while ((st = br.readLine()) != null) {
	    	  sb.append(st);
	      }
	      List<Identifier> tagList = objectMapper.readValue(sb.toString(), new TypeReference<List<PIITag>>(){});
	      System.out.println(tagList);
	      
	      br.close();
	      
	      
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
		
	}
	
}