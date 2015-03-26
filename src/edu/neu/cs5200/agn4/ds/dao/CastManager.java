package edu.neu.cs5200.agn4.ds.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import edu.neu.cs5200.agn4.ds.model.Cast;

public class CastManager {
	DataSource ds;
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public CastManager(){
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

	
	//Create a cast
	public void createCast(Cast newCast){
		String createCast = "INSERT INTO Cast (id, actorid, movieid, characterName)"
    	 		+ " VALUES (?, ?, ?, ?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(createCast);
			statement.setInt(1, newCast.getId());
			statement.setInt(2, newCast.getActorid());
			statement.setInt(3, newCast.getMovieid());
			statement.setString(4, newCast.getCharacterName());
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//Retrieve all casts
	public List<Cast> readAllCast(){
		List<Cast> casts = new ArrayList<Cast>();
		String readAllCasts = "SELECT * FROM Cast";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(readAllCasts);
            results = statement.executeQuery();
            while(results.next()){
            	Cast cast = new Cast();
            	cast.setId(results.getInt("id"));
            	cast.setActorid(results.getInt("actorId"));
            	cast.setMovieid(results.getInt("movieId"));
            	cast.setCharacterName(results.getString("characterName"));
                casts.add(cast);
            }          
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;	
	}
	
	//Retrieve all casts for actor
	public List<Cast> readAllCastForActor(int actorId){
		List<Cast> casts = new ArrayList<Cast>();
		String readAllCastForActor = "SELECT * FROM Cast WHERE actorId=?";


		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(readAllCastForActor);
			statement.setInt(1, actorId);
            results = statement.executeQuery();
            while(results.next()){
            	Cast cast = new Cast();
            	cast.setId(results.getInt("id"));
            	cast.setActorid(results.getInt("actorId"));
            	cast.setMovieid(results.getInt("movieId"));
            	cast.setCharacterName(results.getString("characterName"));
                casts.add(cast);
            }          
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;	
		
	}
	 
	//Retrieve all casts for movie
	public List<Cast> readAllCastForMovie(int movieId){
		List<Cast> casts = new ArrayList<Cast>();
		String readAllCastForMovie = "SELECT * FROM Cast WHERE movieId=?";


		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(readAllCastForMovie);
			statement.setInt(1, movieId);
            results = statement.executeQuery();
            while(results.next()){
            	Cast cast = new Cast();
            	cast.setId(results.getInt("id"));
            	cast.setActorid(results.getInt("actorId"));
            	cast.setMovieid(results.getInt("movieId"));
            	cast.setCharacterName(results.getString("characterName"));
                casts.add(cast);
            }          
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return casts;	
		
		
	}
	//Retrieve cast for id
	public Cast readCastForId(int castId){
		Cast cast = new Cast();
		String readCastForId = "SELECT * FROM Cast WHERE Id=?";
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(readCastForId);
			statement.setInt(1, castId);
            results = statement.executeQuery();
            if(results.next()){
            	cast.setId(results.getInt("id"));
            	cast.setActorid(results.getInt("actorId"));
            	cast.setMovieid(results.getInt("movieId"));
            	cast.setCharacterName(results.getString("characterName"));
            }          
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cast;	
		
	}
	//Update cast
	public void updateCast(int castId, Cast newCast){
		String updateCast = "UPDATE Cast SET id=?, characterName=?, actorId=?, movieId=? WHERE id=?";
		try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(updateCast);
            statement.setInt(1, newCast.getId());
            statement.setInt(2, newCast.getActorid());
            statement.setInt(3, newCast.getMovieid());
            statement.setString(4, newCast.getCharacterName());
            statement.setInt(5, castId);
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
	
	//Delete cast
	public void deleteCast(int castId){
		String deleteCast = "DELETE FROM Cast WHERE id=?";
		try {
            connection = ds.getConnection();
            statement = connection.prepareStatement(deleteCast);
            statement.setInt(1, castId);
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
