package edu.neu.cs5200.bookstore.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.neu.cs5200.bookstore.model.Customer;
import edu.neu.cs5200.bookstore.model.OrderInfo;

public class orderinfoDAO {
	
	EntityManagerFactory factory =  Persistence.createEntityManagerFactory("bookstore");

	
public void createOrderInfo(OrderInfo newordeinfo){
		
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();		
		em.persist(newordeinfo);
		em.getTransaction().commit();
		em.close();	
		
	}
	
	public OrderInfo findById(int id){
		EntityManager em = factory.createEntityManager();
		OrderInfo newordeinfo = new OrderInfo();
		newordeinfo = em.find(OrderInfo.class, id);
		return newordeinfo;
	}
	
	
	public List<OrderInfo> getAllOrderInfo(){
		List<OrderInfo> newordeinfo = new ArrayList<OrderInfo>();
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		Query query = em.createQuery("Select orderinfo from OrderInfo");
		newordeinfo = query.getResultList();
		return newordeinfo;
		
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		orderinfoDAO infodao = new orderinfoDAO();
		OrderInfo info = new OrderInfo();
		info.setBookid(2);
		info.setQuantity(3);
		

	}

}
