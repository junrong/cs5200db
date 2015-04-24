package edu.neu.cs5200.bookstore.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.*;

import edu.neu.cs5200.bookstore.model.Book;

import edu.neu.cs5200.bookstore.model.Shoppingcart;
import edu.neu.cs5200.bookstore.model.User;

public class ShoppingcartDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("bookstore");
	EntityManager em = null;
	
	public Book ViewBookByID(int bookId){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("FindBookByID");
		query.setParameter("bookId", bookId);
		Book book = null;
		List<Book> books = query.getResultList();
		if(!books.isEmpty())
			book = books.get(0);
		em.getTransaction().commit();
		em.close();
		return book;
	}
	
	public void createCart(int userId, Shoppingcart cart, int bookId){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(cart);
		User user = em.find(User.class, userId);
		Book book = em.find(Book.class, bookId);
		cart.setUser(user);
		cart.setBook(book);
		em.merge(cart);
		
		user.getShoppingcarts().add(cart);
		em.merge(user);
		
		book.getShoppingcarts().add(cart);
		em.merge(book);
		
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Shoppingcart> listAllCarts(){
		List<Shoppingcart> carts = new ArrayList<Shoppingcart>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select cart from Shoppingcart cart");
		carts = (List<Shoppingcart>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return carts;
	}
	
	public List<Shoppingcart> listCartsForUser(int userId){
		List<Shoppingcart> carts = new ArrayList<Shoppingcart>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		List<Shoppingcart> allCarts = listAllCarts();
		for(Shoppingcart cart: allCarts)
		{
			if(userId == cart.getUser().getId()){//review.getUserBean().getId())
				carts.add(cart);//reviews.add(review);
			}
		}
		
		em.getTransaction().commit();
		em.close();
		return carts;
	}
	
	
	
	
	public void UpdateBookToCartByID(Shoppingcart newCart){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("UpdateCartByID");
		query.setParameter("bookId", newCart.getBook());
		query.setParameter("userId", newCart.getUser());
		query.setParameter("number", newCart.getNumber());
		query.getResultList();
		em.getTransaction().commit();
		em.close();
	}
	
	public void add_to_shopping_cart(Shoppingcart newbook)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(newbook);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<Shoppingcart> readAllBooksByUserID(int userid) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("Shoppingcart.findByUser");
		query.setParameter("userId", userid);
		
		List<Shoppingcart> shoppingcarts = query.getResultList();
		em.getTransaction().commit();
		em.close();
		return shoppingcarts;
	}
	
	public void insertShoppingcartRecord(int userid, int bookid, int number) {
		em = factory.createEntityManager();
		em.getTransaction().begin();
		
		
		Shoppingcart cart = new Shoppingcart();
		
	/*	cart.setBook(bookid);
		cart.setUser(userid);
		cart.setNumber(number);*/
		/*Query query = em.createNamedQuery("Shoppingcart.insertRecord");
		query.setParameter("bookId", bookid);
		query.setParameter("userId", userid);
		query.setParameter("number", number);*/
		
		
		/*User user = em.find(User.class, userId);
		Book book = em.find(Book.class, bookId);		
		review.setUserBean(user);
		review.setBookBean(book);
		em.merge(review);
		
		user.getReviews().add(review);
		em.merge(user);
		
		book.getReviews().add(review);
		em.merge(book);
		
		em.getTransaction().commit();
		em.close();*/
		
		em.getTransaction().commit();
		em.close();
		
	}

}

