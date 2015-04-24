package edu.neu.cs5200.bookstore.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the order_info database table.
 * 
 */
@Entity
//@Table(name="order_info")
//@NamedQuery(name="OrderInfo.findAll", query="SELECT o FROM OrderInfo o")
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int bookid;

	private int quantity;

	//bi-directional one-to-one association to Book
	@OneToOne
	@PrimaryKeyJoinColumn(name="id")
	private Book book;

	//bi-directional one-to-one association to Order
	@OneToOne
	@PrimaryKeyJoinColumn(name="id")
	private Order order;

	public OrderInfo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBookid() {
		return this.bookid;
	}

	public void setBookid(int bookid) {
		this.bookid = bookid;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Book getBook() {
		return this.book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

}