package edu.neu.cs5200.agn4.ds.model;

public class Cast {
	private int id;
	private int actorid; //foreign key reference to Actor;
	private int movieid; //foreign key reference to Movie;
	private String characterName;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getActorid() {
		return actorid;
	}
	public void setActorid(int actorid) {
		this.actorid = actorid;
	}
	public int getMovieid() {
		return movieid;
	}
	public void setMovieid(int movieid) {
		this.movieid = movieid;
	}
	public String getCharacterName() {
		return characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	
	
	public Cast(int id, int actorid, int movieid, String characterName) {
		super();
		this.id = id;
		this.actorid = actorid;
		this.movieid = movieid;
		this.characterName = characterName;
	}
	
	public Cast() {
		super();
	}
	
	

}
