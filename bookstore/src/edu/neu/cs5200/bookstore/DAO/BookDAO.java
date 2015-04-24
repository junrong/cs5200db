package edu.neu.cs5200.bookstore.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.bookstore.model.Book;
import edu.neu.cs5200.bookstore.model.User;

public class BookDAO {

		EntityManagerFactory factory =  Persistence.createEntityManagerFactory("bookstore");
		
		
		
		public List<Book> listAllBooks(){
			List<Book> books = new ArrayList<Book>();
			EntityManager em = factory.createEntityManager();
			//em.getTransaction().begin();
			Query query = em.createNamedQuery("Book.findAll");
			books = query.getResultList();
			//em.getTransaction().commit();
			//em.close();
			return books;						
		}
		
		public List<Book> listComputersBooks(){
			BookDAO bookdao = new BookDAO();
			List<Book> books = bookdao.listAllBooks();			
			List<Book> cp_books = new ArrayList<Book>();
			for(Book book : books){
				if(book.getCategory().equals("Computers")){
					//System.out.println(book.getAuthor());
					cp_books.add(book);
				}
			}
			return cp_books;			
		}
		
		
		public void createBook(Book newBook){
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			em.persist(newBook);
			em.getTransaction().commit();
			em.close();			
		}
		
		
		public void updateBook(int bookId, Book newBook){
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			Book exist_book = em.find(Book.class, bookId);
			exist_book.setAuthor(newBook.getAuthor());
			exist_book.setCategory(newBook.getCategory());
			exist_book.setImgurl(newBook.getImgurl());
			exist_book.setTitle(newBook.getTitle());
			
			em.merge(exist_book);
			em.getTransaction().commit();
			em.close();
		}
		
		public void deleteBook(int bookId){
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();
			Book book = em.find(Book.class, bookId);
			em.remove(book);
			em.getTransaction().commit();
			em.close();
			System.out.println("Successfully deleted the book!!!");
		}
		
		public void listReviewForBook(){
			
		}
		
		
		/*public User getUser(int userId){
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();

			User user = em.find(User.class, userId);
			

			em.getTransaction().commit();
			em.close();
			
			return user;
		}
		*/
		public Book getBookforId(int id){			
			EntityManager em = factory.createEntityManager();
			em.getTransaction().begin();

			
			Book getBook = em.find(Book.class, id);
			
			em.getTransaction().commit();
			em.close();
			//System.out.println(getBook.getTitle());
			return getBook;
		}
		
	/*	
		public Book findBookforBookId(String google_book_id){
			Book book = new Book();
			return book;
		}
		
		*/

	

	public static void main(String[] args) {
		BookDAO dao = new BookDAO();
		dao.getBookforId(10);
		//Book book = new Book();
		//2,"java","jack","https:wwww","Computers"
		/*book.setTitle("java");
		book.setAuthor("Jackson");
		book.setImgurl("https:www");
		book.setCategory("Computer");
		dao.createBook(book);*/
		//List<Book> books = dao.listAllBooks();
		/*List<Book> books = dao.listComputersBooks();
		for(Book book : books){
			System.out.println(book.getAuthor());
		}*/

	}

}
