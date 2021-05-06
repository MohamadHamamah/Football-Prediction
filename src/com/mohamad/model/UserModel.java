package com.mohamad.model;
import com.mohamad.entity.Match;
import com.mohamad.entity.Prediction;
import com.mohamad.entity.Team;
import com.mohamad.entity.User;
import com.mohamad.entity.Week;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

public class UserModel {

	public User getUser(DataSource dataSource , String email) {
		// Step 1: Initialize connection objects
		User user = null;
        Connection connect = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;       
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * FROM user WHERE user_email = ?" ;
			statement = connect.prepareStatement(query);
			statement.setString(1, email);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				user = new User(resultSet.getInt("id"), resultSet.getString("user_name"), resultSet.getString("user_email"), resultSet.getString("user_password"), resultSet.getString("user_gender").charAt(0), resultSet.getDate("user_birthdate"), resultSet.getString("user_mobile"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        return user;
	}

	public User getUser(DataSource dataSource , int id) {
		// Step 1: Initialize connection objects
		User user = null;
        Connection connect = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;       
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * FROM user WHERE id = ?" ;
			statement = connect.prepareStatement(query);
			statement.setInt(1, id);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				user = new User(resultSet.getInt("id"), resultSet.getString("user_name"), resultSet.getString("user_email"), resultSet.getString("user_password"), resultSet.getString("user_gender").charAt(0), resultSet.getDate("user_birthdate"), resultSet.getString("user_mobile"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        return user;
	}
	
	public List<User> getUsers(DataSource dataSource){
		// Step 1: Initialize connection objects
		List<User> users = new LinkedList<User>();
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;    
		User user = null;
		
		Match match = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * FROM user WHERE id > 0 ORDER BY user_points";
			statement = connect.prepareStatement(query);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				user = new User(resultSet.getInt("id"), resultSet.getString("user_name"), resultSet.getString("user_email"), resultSet.getString("user_password"), resultSet.getString("user_gender").charAt(0), resultSet.getDate("user_birthdate"), resultSet.getString("user_mobile"), resultSet.getInt("user_points"));
				users.add(user);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        
		return users;
	}
	
	public void addUser(DataSource dataSource, User newUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			String query = "INSERT INTO user (user_name,user_email,user_gender, "
					+ "user_birthdate, user_mobile, user_password) "
					+ "VALUES (?,?,?,?,?,?)";
			statement = connect.prepareStatement(query);
			setParameters(statement, newUser);	
			statement.setString(6, newUser.getPassword());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	public void updateUser(DataSource dataSource, User updatedUser) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			String query = "UPDATE user SET user_name = ? , user_email = ? , user_gender = ?,"
					+ " user_birthdate = ?, user_mobile = ?"
					+ " WHERE id = ? ";
			statement = connect.prepareStatement(query);
			statement.setInt(6, updatedUser.getUser_id());
			setParameters(statement,updatedUser);
			statement.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

	private void setParameters(PreparedStatement statement, User user) throws SQLException {
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getEmail());
		statement.setString(3, Character.toString(user.getGender()));
		statement.setDate(4, user.getBirthDate());
		statement.setString(5, user.getMobileNo());	
	}

	public void deleteUser(DataSource dataSource,int usersID) {
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();
			String query = "DELETE FROM user WHERE id = ? ";
			statement = connect.prepareStatement(query);
			statement.setInt(1, usersID);
			statement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 
		
	}

	public List<User> getUsersPredictions(DataSource dataSource) {
		
		return null;
	}

	public void updatePoints(DataSource dataSource, List<Prediction> predictions, List<Integer> points) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();	
			
			// Step 2: Create a SQL statements string
			String query = "UPDATE user SET user_points = user_points + ? "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			connect.setAutoCommit(false);
			for (int i = 0; i < predictions.size(); i++) {
				statement.setInt(1,points.get(i));
				statement.setInt(2,predictions.get(i).getUser().getUser_id());				
				statement.addBatch();
			}
			// Step 3: Execute SQL query
			statement.executeBatch();	
			connect.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public User getAdminUser(DataSource dataSource, String email) {
		// Step 1: Initialize connection objects
		User user = null;
        Connection connect = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;       
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * FROM user WHERE user_email = ? and role_id = 2" ;
			statement = connect.prepareStatement(query);
			statement.setString(1, email);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				user = new User(resultSet.getInt("id"), resultSet.getString("user_name"), resultSet.getString("user_email"), resultSet.getString("user_password"), resultSet.getString("user_gender").charAt(0), resultSet.getDate("user_birthdate"), resultSet.getString("user_mobile"));
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
        return user;
	}
	

}







