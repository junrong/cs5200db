package edu.neu.cs5200.bookstore.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


import edu.neu.cs5200.bookstore.model.Customer;

public class customerDAO {
	
	
EntityManagerFactory factory =  Persistence.createEntityManagerFactory("bookstore");

	
	public void createCustomer(Customer newacustomer){
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();		
		em.persist(newacustomer);
		em.getTransaction().commit();
		em.close();	
		
	}
	
	public Customer findById(int id){
		EntityManager em = factory.createEntityManager();
		Customer customer = new Customer();
		customer = em.find(Customer.class, id);
		return customer;
	}
	
	
	public List<Customer> getAllAdmin(){
		List<Customer> customer = new ArrayList<Customer>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select admin from Admin");
		customer = query.getResultList();
		return customer;
		
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		customerDAO customerdao = new customerDAO();
		Customer customer = new Customer();
		customer.setAddress("Quincy, MA");
		customer.setCardtype("visa");
		

	}

}
