package edu.neu.cs5200.bookstore.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the shoppingcart database table.
 * 
 */
@Embeddable
public class ShoppingcartPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(insertable=false, updatable=false)
	private int bookid;

	@Column(insertable=false, updatable=false)
	private int userid;

	public ShoppingcartPK() {
	}
	public int getBookid() {
		return this.bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getUserid() {
		return this.userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof ShoppingcartPK)) {
			return false;
		}
		ShoppingcartPK castOther = (ShoppingcartPK)other;
		return 
			(this.bookid == castOther.bookid)
			&& (this.userid == castOther.userid);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.bookid;
		hash = hash * prime + this.userid;
		
		return hash;
	}
}