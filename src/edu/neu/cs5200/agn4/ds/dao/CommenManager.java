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

import edu.neu.cs5200.agn4.ds.model.Comment;

public class CommenManager {
	DataSource ds;
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public CommenManager(){
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
	
	
	//Create Comment
	public void createComment(Comment newComment){
    	String createcomment = "INSERT INTO Comment (id, userid, movieid, comment, date)"
    			+ "VALUES (?,?,?,?.?)";
    	try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(createcomment);
			statement.setInt(1, newComment.getId());
			statement.setString(2, newComment.getUsername());
			statement.setInt(3, newComment.getMovieid());
			statement.setString(3, newComment.getComment());
			statement.setDate(5, new Date(newComment.getDate().getTime()));
			statement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
	
	//Retrieve all comments
	public List<Comment> readAllComments(){
    	List<Comment> comments = new ArrayList<Comment>();
    	String readAllComments = "SELECT * FROM Comment";
    	
    	try {
    		Connection connection = ds.getConnection();
			PreparedStatement  statement = connection.prepareStatement(readAllComments);
			results = statement.executeQuery();
			while(results.next()){
				int id = results.getInt("id");
				String username = results.getString("username");
				int movieid = results.getInt("movieid");
				String comment1 = results.getString("comment");
				Date date = results.getDate("date"); 
				
				Comment comment = new Comment(id, username, movieid, comment1, date);
				comments.add(comment);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}    	
		return comments;
    }
	
	//Retrieve all comments for user
	public List<Comment> readAllCommentsForUsername(String username){
    	String readcommentsforuser = "SELECT * FROM Comment WHERE username=?";
    	List<Comment> usercomments = new ArrayList<Comment>();    	
    	try {
    		Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(readcommentsforuser);			
			statement.setString(1, username);
			results = statement.executeQuery();
			while(results.next()){
				int id = results.getInt("id");
				String username1 = results.getString("username");
				int movieid = results.getInt("movieid");
				String comment1 = results.getString("comment");
				Date date = results.getDate("date"); 
				
				Comment comment = new Comment(id, username1, movieid, comment1, date);
				usercomments.add(comment);
			}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return usercomments; 	
    }
	
	
	//Retrieve all comments for movie
	public List<Comment> readAllCommentsForMovie(int movieid){
    	String readcommentsformovie = "SELECT * FROM Comment WHERE movieid=?";
    	List<Comment> moviecomments = new ArrayList<Comment>();
    	    	
    	try {
    		Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(readcommentsformovie);			
			statement.setInt(1, movieid);
			results = statement.executeQuery();
			while(results.next()){
				int id = results.getInt("id");
				String username1 = results.getString("username");
				int movie = results.getInt("movieid");
				String comment1 = results.getString("comment");
				Date date = results.getDate("date"); 
				
				Comment comment = new Comment(id, username1, movie, comment1, date);
				moviecomments.add(comment);
			}
    	} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
		return moviecomments; 	
    }
	
	
	//Retrieve comment for id
	 public Comment readCommentForId(int commentId){
	    	Comment comment = new Comment();
	    	String readcommentforid = "SELECT * FROM Comment WHERE id=?";
	    	
	    	try {
	    		Connection connection = ds.getConnection();
				PreparedStatement statement = connection.prepareStatement(readcommentforid);
				statement.setInt(1, commentId);
				results = statement.executeQuery();
				if(results.next()){
					int id = results.getInt("id");
					String username1 = results.getString("username");
					int movie = results.getInt("movieid");
					String comment1 = results.getString("comment");
					Date date = results.getDate("date"); 
					comment = new Comment(id, username1, movie, comment1, date);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 	
			return comment; 	
	    }
	
	
	//Update comment
	 public void updateComment(Integer commentId, Comment newComment) {
	    	String updateComment = "UPDATE Comment SET id=?, username=?, movieId=?, comment=?, date=?, WHERE id=?";
	    	
	        try {
	        	Connection connection = ds.getConnection();
	        	PreparedStatement statement = connection.prepareStatement(updateComment);
	            statement.setInt(1, newComment.getId());
	            statement.setString(2, newComment.getUsername());
	            statement.setInt(3, newComment.getMovieid());
	            statement.setString(4, newComment.getComment());
	            statement.setDate(5, new Date(newComment.getDate().getTime()));            
	            statement.setInt(6, commentId);
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
	
	
	//Delete comment
	 public void deleteComment(Integer commentId) {
	    	String deleteComment = "DELETE FROM Comment WHERE id=?";
	        try {
	        	Connection connection = ds.getConnection();
	        	PreparedStatement statement = connection.prepareStatement(deleteComment);
	            statement.setInt(1, commentId);
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
