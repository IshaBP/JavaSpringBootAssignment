package com.opus.assignment.first.service;

import java.util.List;

import com.opus.assignment.first.words.Words;

public interface WordsService {
	
	List<Words> findAllOccurances(String value);
	List<Words> findAllWords();
	void insertData();
}
