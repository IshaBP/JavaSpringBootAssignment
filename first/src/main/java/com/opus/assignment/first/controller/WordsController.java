package com.opus.assignment.first.controller;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opus.assignment.first.service.WordsService;
import com.opus.assignment.first.words.Words;

@RestController
@RequestMapping("/words")
public class WordsController {
	
	 private static final Log log = LogFactory.getLog(WordsController.class);
	
	  @Autowired
	  private WordsService wordsservice;
	  
	  @PostMapping
	  public void insertDataInDb() {
		  wordsservice.insertData();
		  log.info("Inserted");
	  } 
	  
	  @GetMapping(value = "/all")
	  public List<Words> getWords() {
	    return wordsservice.findAllWords();
	  } 
	  
	  @GetMapping(value = "/{value}")
	  public List<Words> getWordByValue(@PathVariable("value") String value) {
	    return wordsservice.findAllOccurances(value);
	  }
}
