package edu.neu.cs5200.bookstore.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.bookstore.model.User;

public class userDAO {
	
	EntityManagerFactory factory =  Persistence.createEntityManagerFactory("bookstore");
	
	
	public boolean createUser(User newUser){
		boolean success = false;
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		//check whether existing
		//if(!em.contains(newUser)){
			em.persist(newUser);
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
	
	public User getUser(int userId){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();

		User user = em.find(User.class, userId);
		

		em.getTransaction().commit();
		em.close();
		
		return user;
	}
	
	
	
	public int findUserId(String username) {
		int userId = 0;
		List<User> users = new ArrayList<User>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		users = getAllUsers();
		for (User user: users)
		{
			if (user.getUsername().equals(username) )
				userId = user.getId();
		}

		em.getTransaction().commit();
		em.close();
		//System.out.println(userId);
		return userId;
	}




	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();		
		//em.getTransaction().begin();
		EntityManager em = factory.createEntityManager();
		Query query = em.createNamedQuery("User.findAll");
		users = (List<User>)query.getResultList();

		//em.getTransaction().commit();
		//em.close();
		return users;
	}
	
	
	public boolean verifyUser(User user){
		boolean checkUser = false;
		
		EntityManager em = factory.createEntityManager();
		
		//System.out.println(userId);
		em.getTransaction().begin();
		String username = user.getUsername();
		String password = user.getPassword();
		int userId = findUserId(username);
		User realuser = em.find(User.class, userId);
		
		if(realuser != null && realuser.getPermission() != 2){
			if(password.equals(realuser.getPassword())){
				checkUser = true;
				//System.out.println(checkUser);
			}
		}
		em.getTransaction().commit();
		em.close();
		
		return  checkUser;
	}

	
	/*public boolean checkPWD(P1,P1){
		
	}
	*/
	public boolean verifyAdmin(User user){
		boolean checkUser = false;
		
		EntityManager em = factory.createEntityManager();
		
		//System.out.println(userId);
		em.getTransaction().begin();
		String username = user.getUsername();
		//String password = user.getPassword();
		int userId = findUserId(username);
		User realuser = em.find(User.class, userId);
		
		if(realuser.getPermission() == 2){
			//if(password.equals(realuser.getPassword())){
				checkUser = true;
				//System.out.println(checkUser);
			//}
		}
		em.getTransaction().commit();
		em.close();
		
		return  checkUser;
	}



	public static void main(String[] args) {
		userDAO dao = new userDAO();
		User user = new User();
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
