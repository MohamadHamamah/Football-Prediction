package com.mohamad.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mohamad.entity.*;

public class WeekModel {
	public void addWeek(DataSource dataSource, Week week) {
		// Step 1: Initialize connection objects
		Connection connect = null;
        Statement statement = null;      
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "INSERT INTO week (season_id, week_no) VALUES ((SELECT max(id) FROM season), "
					+ "(SELECT max(week_no) FROM football_prediction.week  WHERE season_id=(SELECT max(id) FROM season)));";
			statement = connect.createStatement();
			// Step 3: Execute SQL query
			statement.executeUpdate(query);	
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
        if (week.getMatches() != null) {
			new MatchModel().addMatches(dataSource, week.getMatches(), getLastWeek(dataSource).getWeekId());
		}
	}
	
	public Week getLastWeek(DataSource dataSource) {
		// Step 1: Initialize connection objects
		Connection connect = null;
        Statement statement = null;
        ResultSet resultSet = null;  
        Week week = null;
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * FROM football_prediction.week  WHERE season_id=((SELECT max(id) FROM football_prediction.season))";
			statement = connect.createStatement();
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery(query);
			// Step 4: Process the result set
			while(resultSet.next()){
				week = new Week(resultSet.getInt("id"), new Season(resultSet.getInt("season_id")), resultSet.getInt("week_no"));
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
        
        return week;
	}
	
	public Week getWeek(DataSource dataSource, int weekId){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null; 
		Week week = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM week "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			statement.setInt(1, weekId);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				week = new Week(resultSet.getInt("id"), new SeasonModel().getSeason(dataSource, resultSet.getInt("season_id")), resultSet.getInt("week_no"));
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
		
		return week;
	}
}
