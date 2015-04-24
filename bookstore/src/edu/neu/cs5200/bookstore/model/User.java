package edu.neu.cs5200.bookstore.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String email;

	private String firstname;

	private String lastname;

	private String password;

	private int permission;

	private String phone;

	private String username;

	//bi-directional one-to-one association to Admin
/*	@OneToOne(mappedBy="user")
	private Admin admin;

	//bi-directional one-to-one association to Customer
	@OneToOne(mappedBy="user")
	private Customer customer;

	//bi-directional one-to-one association to Order
	@OneToOne(mappedBy="user")
	private Order order;*/

	//bi-directional many-to-one association to Review
	@OneToMany(mappedBy="userBean")
	private List<Review> reviews;

	//bi-directional many-to-one association to Shoppingcart
	@OneToMany(mappedBy="user")
	private List<Shoppingcart> shoppingcarts;

	public User() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*public Admin getAdmin() {
		return this.admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Order getOrder() {
		return this.order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}*/

	public List<Review> getReviews() {
		return this.reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public Review addReview(Review review) {
		getReviews().add(review);
		review.setUserBean(this);

		return review;
	}

	public Review removeReview(Review review) {
		getReviews().remove(review);
		review.setUserBean(null);

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
		shoppingcart.setUser(this);

		return shoppingcart;
	}

	public Shoppingcart removeShoppingcart(Shoppingcart shoppingcart) {
		getShoppingcarts().remove(shoppingcart);
		shoppingcart.setUser(null);

		return shoppingcart;
	}

}