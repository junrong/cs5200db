package edu.neu.cs5200.agn4.ds.model;

import java.util.Date;

public class Actor {
	private int id;
	private String firstname;
	private String lastname;
	private Date dob;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Actor(int id, String firstname, String lastname, Date dob) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.dob = dob;
	}
	public Actor() {
		super();
	}
	
	

}
