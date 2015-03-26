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

import edu.neu.cs5200.agn4.ds.model.User;

public class UserManager {
	DataSource ds;
	Connection connection = null;
	PreparedStatement statement = null;
	ResultSet results = null;
	
	public UserManager(){
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
	
	
	//Create an user
	public void createUser(User newUser){
		String createUsersql = "INSERT INTO User"
				+ "(username, password, firstname, lastname, email, DoB)"
				+ "VALUES (?,?,?,?,?,?)";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(createUsersql);
			
			statement.setString(1, newUser.getUsername());
			statement.setString(2, newUser.getPassword());
			statement.setString(3, newUser.getFirstname());
			statement.setString(4, newUser.getLastname());
			statement.setString(5, newUser.getEmail());
			statement.setDate(6, new Date(newUser.getDob().getTime()));
			statement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
	
	
	//Retrieve all users
	public List<User> readAllUsers(){
		String readAllUsers = "SELECT * FROM User";
		List<User> users = new ArrayList<User>();
		
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(readAllUsers);
			results = statement.executeQuery();
			
			while(results.next()){				
				String username = results.getString("username");
				String password = results.getString("password");
				String firstname = results.getString("firstname");
				String lastname = results.getString("lastname");
				String email = results.getString("email");
				Date DoB = results.getDate("DoB");
				User user = new User(username,password, firstname,lastname, email, DoB);
				users.add(user);
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
		return users;
	}
	
	//Retrieve one user
	public User readUser(String username){
		User user = new User();
		String readUser = "SELECT * FROM User WHERE username=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(readUser);
			statement.setString(1, username);
			results = statement.executeQuery();
			if(results.next()){
				username = results.getString("username");
				String firstname = results.getString("firstName");
				String lastname = results.getString("lastName");
				String password = results.getString("password");
				String email = results.getString("email");
				Date DoB=results.getDate("DoB");
				user = new User(username,password,firstname,lastname, email, DoB);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return user;	
	}
	
	//Update an user
	public void updateUser(String username, User user){
		String updateUser = "UPDATE User SET username=?, password=?, firstName=?, "
		 		+ "lastName=?, email=?, dateOfBirth=? WHERE username=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(updateUser);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getFirstname());
			statement.setString(4, user.getLastname());
			statement.setString(5, user.getEmail());
			statement.setDate(6, new Date(user.getDob().getTime()));
			statement.setString(7, username);
			statement.execute();
			
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
	}
	
	//delete an user
	public void deleteUser(String username){
		String deleteUser = "DELETE FROM User WHERE username=?";
		try {
			Connection connection = ds.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteUser);
			
			statement.setString(1, username);
			statement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
