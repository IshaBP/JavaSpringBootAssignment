package com.opus.assignment.first.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.opus.assignment.first.words.Words;

@Repository
public interface WordsRepository extends MongoRepository<Words, Integer>{
}
