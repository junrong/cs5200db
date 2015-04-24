package edu.neu.cs5200.bookstore.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.bookstore.model.Book;
import edu.neu.cs5200.bookstore.model.Review;
import edu.neu.cs5200.bookstore.model.User;

public class reviewDAO {
	
	EntityManagerFactory factory =  Persistence.createEntityManagerFactory("bookstore");
	
	
	public void createReview(int userId, Review review, int bookId){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(review);
		User user = em.find(User.class, userId);
		Book book = em.find(Book.class, bookId);		
		review.setUserBean(user);
		review.setBookBean(book);
		em.merge(review);
		
		user.getReviews().add(review);
		em.merge(user);
		
		book.getReviews().add(review);
		em.merge(book);
		
		em.getTransaction().commit();
		em.close();
	}
	
	
	public void likeBook(int userId, Book book){
		
	}
	
	public List<Review> listAllReviews()
	{
		List<Review> reviews = new ArrayList<Review>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select review from Review review");
		reviews = (List<Review>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return reviews;
	}
	
	public List<Review> listUniqueReviews()
	{
		List<Review> reviews = new ArrayList<Review>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Query query = em.createQuery("select review from Review review group by bookBean");
		reviews = (List<Review>)query.getResultList();
		
		em.getTransaction().commit();
		em.close();
		return reviews;
	}
	
	public List<Review> listNReviewsForBook(int n, int bookId)
	{
		List<Review> reviews = new ArrayList<Review>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		List<Review> allReviews = listAllReviews();
		if(allReviews.size()<n)
			n=allReviews.size();
		int i = n;
		for(int k= allReviews.size()-1; k >= 0 ; k--)
		{
			if(bookId == allReviews.get(k).getBookBean().getId())
				if(i > 0)
				{
					reviews.add(allReviews.get(k));
					i--;
				}
		}
		
		em.getTransaction().commit();
		em.close();
		return reviews;
	}
	
	
	public void updateReview(int reviewId, Review review){
		EntityManager em=factory.createEntityManager();
		em.getTransaction().begin();
		
		Review reviewed=em.find(Review.class, reviewId);
		reviewed.setComments(review.getComments());
		reviewed.setDate(review.getDate());
		reviewed.setRating(review.getRating());
		em.merge(reviewed);
		em.getTransaction().commit();
		em.close();
	}
	
	
	public List<Review> listReviewsForUser(int userId)
	{
		List<Review> reviews = new ArrayList<Review>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		List<Review> allReviews = listAllReviews();
		for(Review review: allReviews)
		{
			if(userId == review.getUserBean().getId())
				reviews.add(review);
		}
		
		em.getTransaction().commit();
		em.close();
		return reviews;
	}
	
	
	public void deleteReview(int reviewId){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		Review review = em.find(Review.class, reviewId);
        
        User user=review.getUserBean();
		Book movie=review.getBookBean();
		
		em.remove(review);

		em.getTransaction().commit();
		em.close();
	}

	public static void main(String[] args) {
/*reviewDAO dao = new reviewDAO();
List<Review> reviews = dao.listNReviewsForBook(5, 1);
for(Review review:reviews){
	System.out.println(review.getComments());
}*/
	/*	Review review = new Review();
		review.setComments("aaa");
		dao.createReview(1, review, 1);*/
	}

}
