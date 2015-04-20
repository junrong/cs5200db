package dao;

import java.util.ArrayList;
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
import javax.xml.bind.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

import java.io.*;

import model.Site;
import model.siteList;

@Path("/site")
public class SiteDAO {	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("Assignment6");
	EntityManager em = factory.createEntityManager();

	
	//public Site findSite(int siteId)
	@GET
	@Path("/findById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSiteById(@PathParam("id") int siteId) {
		
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
	public List<Site> updateSite(@PathParam("id") int siteId, Site site){
		
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
	public List<Site> removeSite(@PathParam("id") int siteId){
		List<Site> sites = new ArrayList<Site>();
		Site site = new Site();
		em.getTransaction().begin();
		site = em.find(Site.class, siteId);
		em.remove(site);
		/*Query query = em.createQuery("select site from Site site");
		sites = query.getResultList();*/
		
		em.getTransaction().commit();
		
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
		//return sites;
		
	}
	
	
	//public List<Site> createSite(Site site)	
	@POST
	@Path("/createSite")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site) {
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		
		Query query = em.createQuery("select site from Site site");
		return (List<Site>)query.getResultList();
		//return findAllSites();
	}
	
	public void exportsitedaoDBtoXmlFile(siteList sites, String filename){
		File xml = new File(filename);
		try{
			JAXBContext jaxbc = JAXBContext.newInstance(siteList.class);
			Marshaller marshaller = jaxbc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(sites, xml);
		}catch (JAXBException e) {
			e.printStackTrace();
		}
	}
	
	
	public void convertXmlFileToOutputFile(String inputXmlFileName,String outputFileName,String xsltFileName)
	{
		File inputXmlFile = new File(inputXmlFileName);
		File outputXmlFile = new File(outputFileName);
		File xsltFile = new File(xsltFileName);
		
		StreamSource input = new StreamSource(inputXmlFile);
		StreamSource xslt    = new StreamSource(xsltFile);
		StreamResult output = new StreamResult(outputXmlFile);
		
		TransformerFactory factory = TransformerFactory.newInstance();
		try {
			Transformer transformer = factory.newTransformer(xslt);
			transformer.transform(input, output);
		} catch (TransformerConfigurationException e) {
						e.printStackTrace();
		} catch (TransformerException e) {
						e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
		SiteDAO dao = new SiteDAO();
		
		//Site site = new Site();
		//Site site = new Site(8, "Site 19", 30.12, 30.12);
		//site = dao.findSiteById(3);
		//System.out.println(site.getName());
		
		/*List<Site> sites = dao.createSite(site);
		for(Site site1 : sites)
		{
			System.out.println(site1.getName());
		}*/
		
		/*List<Site> sites = dao.updateSite(5, site);
		for(Site site1 : sites)
		{
			System.out.println(site.getName());
		}
		
		List<Site> sites = dao.removeSite(5);
		for(Site site1 : sites)
		{
			System.out.println(site.getName());
		}*/
		
		List<Site> sites=dao.findAllSites();
		for(Site site:sites){
			System.out.println(site.getId());
		}
		siteList theSites = new siteList();
		theSites.setSites(sites);
        
		dao.exportsitedaoDBtoXmlFile(theSites, "xml/sites.xml");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/sites.html", "xml/sites2html.xslt");
		dao.convertXmlFileToOutputFile("xml/sites.xml", "xml/equipments.html", "xml/sites2equipment.xslt");
		
	
	}
	


	

}
