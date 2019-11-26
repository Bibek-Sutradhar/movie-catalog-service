package com.example.moviecatalogservice.model;

public class CatalogItem {
	
	private int id;
	private String name;
	private String desc;
	private String rating;
	
	public CatalogItem(int id, String name, String desc, String rating) {
		this.id=id;
		this.name=name;
		this.desc = desc;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id=id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
}
