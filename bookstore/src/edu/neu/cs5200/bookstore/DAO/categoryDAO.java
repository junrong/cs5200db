package edu.neu.cs5200.bookstore.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import edu.neu.cs5200.bookstore.model.Category;


public class categoryDAO {
	
	EntityManagerFactory factory =  Persistence.createEntityManagerFactory("bookstore");
	
	
	public boolean createCategory(Category newCatg){
		boolean success = false;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//check whether existing
		//if(!em.contains(newUser)){
			em.persist(newCatg);
		//}else{
		//	em.merge(newUser);
		//}
		//em.persist(newUser);
		em.getTransaction().commit();
		em.close();
		
		/*int confirm = findUserId(newUser.getUsername());
		if(confirm!= 0){
			success = true;			
		}*/
		
		return true;
		
	}
	
	
	
	public static void main(String[] args) {
		//userDAO dao = new userDAO();
		//User user = new User();
		/*user.setUsername("eee");
		user.setPassword("eeee");
		//dao.verifyUser(user);
		dao.verifyAdmin(user);*/
		//dao.findUserId("rong");
		//User user = new User(3,"junrong2","yan","rong2",1,"junrong@ccs.neu.edu","6178038137",1);
		//int a = dao.findUserId("rong");
		//System.out.println(a);
		/*List<User> users = new ArrayList<User>();
		users = dao.getAllUsers();
		for(User user : users){
			System.out.println(user.getFirstname());
		}*/
		

	}


}
