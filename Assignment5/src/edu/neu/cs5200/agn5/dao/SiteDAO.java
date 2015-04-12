package edu.neu.cs5200.agn5.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.agn5.model.Site;



@Path("/site")
public class SiteDAO {	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Assignment5");
	EntityManager em = factory.createEntityManager();

	
	//public Site findSite(int siteId)
	@GET
	@Path("findById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	private Site findSiteById(@PathParam("id") Integer siteId) {
		
		return em.find(Site.class, siteId);
		
	}
	
	///test for JWS
	@GET
	@Path("/hello")
	public String hellworld(){
		
		return "HELLO WORLD FORM JSP!!!!";
		
	}
	
	
	//public List<Site> findAllSites()
	@GET
	@Path("/findall")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites(){
		
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
		
		
	}
	//public List<Site> updateSite(int siteId, Site site)
	@PUT
	@Path("/updateSite/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(int siteId, Site site){
		
		em.getTransaction().begin();
		site.setId(siteId);
		em.merge(site);
		em.getTransaction().commit();
		
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
	
		
	}
	
	
	//public List<Site> removeSite(int siteId) 
	@DELETE
	@Path("/deleteSite/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(Integer siteId){
		Site site = new Site();
		em.getTransaction().begin();
		site = em.find(Site.class, siteId);
		em.remove(site);
		em.getTransaction().commit();
		
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
		
	}
	
	
	//public List<Site> createSite(Site site)	
	@POST
	@Path("/createSite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	private Site createSite(Site site) {
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		return site;
	}
	
	/*public static void main(String[] args) {
		SiteDAO dao = new SiteDAO();
		
		//Site site = new Site();
		Site site = new Site(8, "Site 19", 30.12, 30.12);
		//site = dao.findSiteById(3);
		//System.out.println(site.getName());
		
		List<Site> sites = dao.findAllSites();
		for(Site site : sites)
		{
			System.out.println(site.getName());
		}
		
		List<Site> sites = dao.updateSite(5, site);
		for(Site site1 : sites)
		{
			System.out.println(site.getName());
		}
		
		List<Site> sites = dao.removeSite(5);
		for(Site site1 : sites)
		{
			System.out.println(site.getName());
		}
		
		
	}*/

	


	

}
