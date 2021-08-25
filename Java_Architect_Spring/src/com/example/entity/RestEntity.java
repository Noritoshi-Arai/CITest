package com.example.entity;

public class RestEntity {
	private String id;
	private int year;
	private int possibleRest;
	private int mustRest;
	
	//コンストラクタ
	public RestEntity() {
		id = null;
		year = 0;
		possibleRest = 10;
		mustRest = 5;
	}
	
	public RestEntity(String id, int year, int possibleRest, int mustRest) {
		this.id = id;
		this.year = year;
		this.possibleRest = possibleRest;
		this.mustRest = mustRest;
	}
	
	//ID
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	//year
	public void setYear(int year) {
		this.year = year;
	}
	
	public int getYear() {
		return year;
	}
	
	//possible_rest
	public void setPossibleRest(int possibleRest) {
		this.possibleRest = possibleRest;
	}
	
	public int getPossibleRest() {
		return possibleRest;
	}
	
	//must_rest
	public void setMustRest(int mustRest) {
		this.mustRest = mustRest;
	}
	
	public int getMustRest() {
		return mustRest;
	}

}
