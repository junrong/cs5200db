package edu.neu.cs5200.boostore.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the shoppingcart database table.
 * 
 */


@NamedQueries({
	@NamedQuery(
		name="Shoppingcart.findAll",
		query="SELECT s FROM Shoppingcart s"),
	@NamedQuery(
		name = "Shoppingcart.findByUser",
		query = "select s from Shoppingcart s where s.userid = :userId"),
	@NamedQuery(
		name = "Shoppingcart.insertRecord",
		query = "insert into shoppingcart (bookid, userid, number) values (:bookId, :userId, :number)")
		})
@Entity


public class Shoppingcart implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private ShoppingcartPK id;

	private int number;

	//bi-directional many-to-one association to Book
	@ManyToOne
	@JoinColumn(name="bookid")
	private Book book;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="userid")
	private User user;

	public Shoppingcart() {
		super();
	}

	public ShoppingcartPK getId() {
		return this.id;
	}

	public void setId(ShoppingcartPK id) {
		this.id = id;
	}

	public float getNumber() {
		return this.number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Shoppingcart(ShoppingcartPK id, int number, Book book,
			User user) {
		super();
		this.id = id;
		this.number = number;
		this.book = book;
		this.user = user;
	}

	
	
	
}