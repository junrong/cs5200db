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

import edu.neu.cs5200.agn4.ds.model.Movie;

public class MovieManager {
	DataSource ds;
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public MovieManager(){
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
	
	//create a movie
	public void createMovie(Movie newMovie) {
    	String createMovie = "INSERT INTO Movie (id, title, posterImage, releaseDate)"
    	 		+ " VALUES (?, ?, ?, ?)";
    	try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(createMovie);
            statement.setObject(1, newMovie.getId());
            statement.setString(2, newMovie.getTitle());
            statement.setString(3, newMovie.getPosterImage());
            statement.setDate(4, new Date(newMovie.getReleaseDate().getTime()));
            statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
	//Retrieve all movies
	public List<Movie> readAllMovies() {
        List<Movie> movies = new ArrayList<Movie>();
        String readAllMovies = "SELECT * FROM Movie";

        try {
        	Connection connection = ds.getConnection();
        	PreparedStatement statement = connection.prepareStatement(readAllMovies);
            results = statement.executeQuery();
            while (results.next()) {
            	int id = results.getInt("id");
				String title = results.getString("title");
				String posterImage = results.getString("posterImage");
				Date releaseDate = results.getDate("releaseDate");
				
                Movie movie = new Movie(id, title, posterImage, releaseDate);
                movies.add(movie);
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

        return movies;
    }
	
	
	//Retrieve a movie
	public Movie readMovie(Integer movieId) {
    	Movie movie = new Movie();
    	String readMovie = "SELECT * FROM Movie WHERE id = ?";
        try {
        	Connection connection = ds.getConnection();
        	PreparedStatement statement = connection.prepareStatement(readMovie);
        	statement.setInt(1, movieId);
            results = statement.executeQuery();
            if (results.next()) {
            	int id = results.getInt("id");
				String title = results.getString("title");
				String posterImage = results.getString("posterImage");
				Date releaseDate = results.getDate("releaseDate");
                movie = new Movie(id, title, posterImage, releaseDate);
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

        return movie;
    }
	
	
	//Update a movie
	public void updateMovie(Integer movieId, Movie movie) {
   	 String updateMovie = "UPDATE Movie SET id=?, title=?, "
   	 		+ "posterImage=?, releaseDate=? WHERE id=?";
   	    	    
       try {
    	   Connection connection = ds.getConnection();
       	PreparedStatement statement = connection.prepareStatement(updateMovie);
       	statement.setInt(1, movie.getId());
           statement.setString(2, movie.getTitle());
           statement.setString(3, movie.getPosterImage());
           statement.setDate(4, new Date(movie.getReleaseDate().getTime()));
           statement.setInt(5, movieId);
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
	
	
	//Delete a movie
	public void deleteMovie(Integer movieId) {
    	String deleteMovie = "DELETE FROM Movie WHERE id=?";
        try {
        	Connection connection = ds.getConnection();
        	PreparedStatement statement = connection.prepareStatement(deleteMovie);
            statement.setInt(1, movieId);
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
