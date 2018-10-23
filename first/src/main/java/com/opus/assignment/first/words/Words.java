package com.opus.assignment.first.words;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "Words")
public class Words {

	@Id
	private int id;
	private String fileName;
	private int[] position;
	private String value;
	
	public Words() {
	}

	public Words(int id, String fileName, int[] position, String value) {
		super();
		this.id = id;
		this.fileName = fileName;
		this.position = position;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int[] getPosition() {
		return position;
	}

	public void setPosition(int[] position) {
		this.position = position;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Words [id=" + id + ", fileName=" + fileName + ", position=" + position + ", value=" + value + "]";
	}

}
