package edu.neu.cs5200.agn4.ds.model;

import java.util.Date;

public class Comment {
	private int id;
	private String username; //foreign key reference to User
	private int movieid; //foreign key reference to Movie
	private String comment;
	private Date date;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	public Comment(int id, String username, int movieid, String comment,
			Date date) {
		super();
		this.id = id;
		this.username = username;
		this.movieid = movieid;
		this.comment = comment;
		this.date = date;
	}
	
	
	public Comment() {
		super();
	}

}
