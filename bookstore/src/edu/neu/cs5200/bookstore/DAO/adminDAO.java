package edu.neu.cs5200.bookstore.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.bookstore.model.Admin;

public class adminDAO {
	
	EntityManagerFactory factory =  Persistence.createEntityManagerFactory("bookstore");

	
	public void createAdmin(Admin newadmin){
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();		
		em.persist(newadmin);
		em.getTransaction().commit();
		em.close();	
		
	}
	
	public Admin findById(int id){
		EntityManager em = factory.createEntityManager();
		Admin admin = new Admin();
		admin = em.find(Admin.class, id);
		return admin;
	}
	
	
	public List<Admin> getAllAdmin(){
		List<Admin> admins = new ArrayList<Admin>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select admin from Admin");
		admins = query.getResultList();
		return admins;
		
	}
	
	
	public static void main(String[] args) {
		adminDAO admindao = new adminDAO();
		Admin admin = new Admin();
		admin.setAddress("Bosston Northeastern University");
		admin.setRole("administrator");
		
		
		
		

	}

}
