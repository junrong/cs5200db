package edu.neu.cs5200.bookstore.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the book database table.
 * 
 */
@Entity
@NamedQuery(name="Book.findAll", query="SELECT b FROM Book b")
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String author;

	private String bookId;

	private String category;

	private String imgurl;

	private String title;

	/*//bi-directional one-to-one association to OrderInfo
	@OneToOne(mappedBy="book")
	private OrderInfo orderInfo;
*/
	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="bookBean")
	private List<Review> reviews;

	//bi-directional many-to-one association to Shoppingcart
	@OneToMany(mappedBy="book")
	private List<Shoppingcart> shoppingcarts;

	public Book() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getBookId() {
		return this.bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImgurl() {
		return this.imgurl;
	}

	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	/*public OrderInfo getOrderInfo() {
		return this.orderInfo;
	}

	public void setOrderInfo(OrderInfo orderInfo) {
		this.orderInfo = orderInfo;
	}*/

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setBookBean(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setBookBean(null);

		return review;
	}

	public List<Shoppingcart> getShoppingcarts() {
		return this.shoppingcarts;
	}

	public void setShoppingcarts(List<Shoppingcart> shoppingcarts) {
		this.shoppingcarts = shoppingcarts;
	}

	public Shoppingcart addShoppingcart(Shoppingcart shoppingcart) {
		getShoppingcarts().add(shoppingcart);
		shoppingcart.setBook(this);

		return shoppingcart;
	}

	public Shoppingcart removeShoppingcart(Shoppingcart shoppingcart) {
		getShoppingcarts().remove(shoppingcart);
		shoppingcart.setBook(null);

		return shoppingcart;
	}

}