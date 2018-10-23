package com.opus.assignment.first.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.MongoClient;
import com.opus.assignment.first.repository.WordsRepository;
import com.opus.assignment.first.words.Words;

@Service
public class WordsServiceImpl implements WordsService {	
	
	MongoOperations mongoOps = new MongoTemplate(new SimpleMongoDbFactory(new MongoClient(), "database1"));

	@Override
	public List<Words> findAllOccurances(String value) {
		Query query = new Query(Criteria.where("value").is(value));
		List<Words> ms1 = mongoOps.find(query, Words.class);
		TreeSet<String> files = new TreeSet<String>();
		for(Words w : ms1) {
			files.add(w.getFileName());
		}
		
		// Prints the data on console
		System.err.println("The files which have occurances of "+value+" are: "+files);
		System.err.println("The total number of occurances of "+value+" are: "+ms1.size());
		
		// Returns the records
		return ms1;
	}

	@Override
	public List<Words> findAllWords() {
		return mongoOps.findAll(Words.class);
	}
	
	@Override
	public void insertData() {	
		Words ws;
		int count=0;	
		File directory = new File("Excelfiles");
		File[] files = directory.listFiles();
		for (File file : files)
		{
			try {
				FileInputStream myInput = new FileInputStream(file);
				OPCPackage pkg = OPCPackage.open(file);
				XSSFWorkbook myWorkBook = new XSSFWorkbook(pkg);
				XSSFSheet mySheet = myWorkBook.getSheetAt(0);
				Iterator rowIter = mySheet.rowIterator();
				while (rowIter.hasNext()) {
					XSSFRow myRow = (XSSFRow) rowIter.next();
					Iterator cellIter = myRow.cellIterator();
					while (cellIter.hasNext()) {
						XSSFCell myCell = (XSSFCell) cellIter.next();
						int[] pos = {myCell.getRowIndex(), myCell.getColumnIndex()};
						ws = new Words(count,file.getName(),pos,myCell.getStringCellValue());
						mongoOps.insert(ws);
						count++;
					}
				}
			} catch (FileNotFoundException e) {
					e.printStackTrace();
			} catch (InvalidFormatException e) {
					e.printStackTrace();
			} catch (IOException e) {
					e.printStackTrace();
			}
		}
	}
}