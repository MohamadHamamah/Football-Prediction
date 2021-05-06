package com.mohamad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mohamad.entity.Season;

public class SeasonModel {
	
	public void addSeason(DataSource dataSource, Season season) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "INSERT INTO season (season_start) VALUES (?);";
			statement = connect.prepareStatement(query);
			statement.setString(1, season.getSeasonName());
			// Step 3: Execute SQL query
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
	
	public Season getSeason(DataSource dataSource, int seasonId){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;       
		Season season = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM season "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			statement.setInt(1, seasonId);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				season = new Season(resultSet.getInt("id"),resultSet.getString("season_start"),null);
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
		
		return season;
	}
}
