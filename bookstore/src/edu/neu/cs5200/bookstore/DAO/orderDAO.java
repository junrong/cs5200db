package edu.neu.cs5200.bookstore.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.bookstore.model.Order;
import edu.neu.cs5200.bookstore.model.OrderInfo;



public class orderDAO {

	EntityManagerFactory factory =  Persistence.createEntityManagerFactory("bookstore");
	
	
public void createOrder(Order order){
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();		
		em.persist(order);
		em.getTransaction().commit();
		em.close();	
		
	}
	
	public Order findById(int id){
		EntityManager em = factory.createEntityManager();
		Order order = new Order();
		order = em.find(Order.class, id);
		return order;
	}
	
	
	public List<Order> getAllAdmin(){
		List<Order> orders = new ArrayList<Order>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select admin from Admin");
		orders = query.getResultList();
		return orders;
		
	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		orderDAO orderdao = new orderDAO();
		Order order = new Order();
		OrderInfo orderinfo = new OrderInfo();
		
		order.setOrderInfo(orderinfo);
		order.setOrdernum(123423);
		order.setUserid(3);
		orderdao.createOrder(order);
		
		
		
		

	}

}
