package com.example.moviecatalogservice.model;

public class Rating {
	
	private int movieId;
	private String rating;

	public Rating (int movieId, String rating) {
		this.movieId=movieId;
		this.rating=rating;
	}
	
	public int getMovieId() {
		return movieId;
	}
	
	public void setMovieId(int movieId) {
		this.movieId=movieId;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setRating(String rating) {
		this.rating=rating;
	}
}
