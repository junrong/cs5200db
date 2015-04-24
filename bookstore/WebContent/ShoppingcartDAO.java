package edu.neu.cs5200.boostore.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import edu.neu.cs5200.boostore.model.Shoppingcart;

public class ShoppingcartDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("boostore");
	EntityManager em = null;
	
	public Shoppingcart ViewBookByID(int bookId){
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
	public void UpdateBookToCartByID(Shoppingcart newCart){
		em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createNamedQuery("UpdateCartByID");
		query.setParameter("bookId", newCart.getBookId());
		query.setParameter("userId", newCart.getUserId());
		query.setParameter("number", newCart.getNumber());
		query.getResultList();
		em.getTransaction().commit();
		em.close();
	}
	
	public void add_to_shopping_cart(Shoppingcart newbook)
	{
		em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(newBook);
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
		Query query = em.createNamedQuery("Shoppingcart.insertRecord");
		query.setParameter("bookId", bookid);
		query.setParameter("userId", userid);
		query.setParameter("number", number);
		em.getTransaction().commit();
		em.close();
		
	}

}

