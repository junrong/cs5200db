package edu.neu.cs5200.agn4.ds.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.agn4.ds.model.Actor;

public class ActorManager {
	DataSource ds;
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public ActorManager(){
		Context ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Assignment4DB");
			System.out.println(ds);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	//create Actor
	 public void createActor(Actor newActor) {
	    	String createActor = "INSERT INTO Actor" +
	                "(id, firstName, lastName, dateOfBirth) VALUES (?, ?, ?, ?)";
	    	try {
				Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(createActor);
	            statement.setObject(1, newActor.getId());
	            statement.setString(2, newActor.getFirstname());
	            statement.setString(3, newActor.getLastname());
	            statement.setDate(4, new Date(newActor.getDob().getTime()));
	            statement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}        
	    }
	 
	//Retrieve all actors
	 public List<Actor> readAllActors() {
	        List<Actor> actors = new ArrayList<Actor>();
	        String readAllActors = "SELECT * FROM Actor";

	        try {
	        	Connection connection = ds.getConnection();
	        	PreparedStatement statement = connection.prepareStatement(readAllActors);
	            results = statement.executeQuery();
	            while (results.next()) {
	            	int id = results.getInt("id");
					String firstname = results.getString("firstName");
					String lastname = results.getString("lastname");
					Date DoB = results.getDate("DoB");
	                Actor actor = new Actor(id, firstname, lastname, DoB);
	                actors.add(actor);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return actors;
	    }
	 
	 
	//Retrieve one actor
	 public Actor readActor(Integer actorId) {
	    	Actor actor = new Actor();
	    	String readActor = "SELECT * FROM Actor WHERE id=?";
	        try {
	        	Connection connection = ds.getConnection();
	        	PreparedStatement statement = connection.prepareStatement(readActor);
	            statement.setInt(1, actorId);
	            results = statement.executeQuery();
	            if (results.next()) {
	            	int id = results.getInt("id");
					String firstname = results.getString("firstName");
					String lastname = results.getString("lastname");
					Date DoB = results.getDate("DoB");
	                actor = new Actor(id, firstname, lastname, DoB);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return actor;
	    }
	 
	 
	//Update actor
	 public void updateActor(Integer actorId, Actor actor) {
	    	String updateActor = "UPDATE Actor SET id=?, firstName=?, lastName=?, dateOfBirth=? WHERE id=?"; 
	        try {
	        	Connection connection = ds.getConnection();
	        	PreparedStatement statement = connection.prepareStatement(updateActor);
	            statement.setInt(1, actor.getId());
	            statement.setString(2, actor.getFirstname());
	            statement.setString(3, actor.getLastname());
	            statement.setDate(4, new Date(actor.getDob().getTime()));
	            statement.setInt(5, actorId);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	 
	 
	 
	//delete actor
	 public void deleteActor(Integer actorId) {
	    	String deleteActor = "DELETE FROM Actor WHERE id=?";
	        try {
	        	Connection connection = ds.getConnection();
	        	PreparedStatement statement = connection.prepareStatement(deleteActor);
	            statement.setInt(1, actorId);
	            statement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }

}
