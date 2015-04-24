package edu.neu.cs5200.bookstore.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the review database table.
 * 
 */
@Entity
@NamedQuery(name="Review.findAll", query="SELECT r FROM Review r")
public class Review implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String comments;

	private String date;

	private String rating;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="book")
	public Book bookBean;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="user")
	public User userBean;

	public Review() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getDate() {
		return this.date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getRating() {
		return this.rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Book getBookBean() {
		return this.bookBean;
	}

	public void setBookBean(Book bookBean) {
		this.bookBean = bookBean;
	}

	public User getUserBean() {
		return this.userBean;
	}

	public void setUserBean(User userBean) {
		this.userBean = userBean;
	}

}