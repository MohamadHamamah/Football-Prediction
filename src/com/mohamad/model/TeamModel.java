package com.mohamad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap; 

import javax.sql.DataSource;

import com.mohamad.entity.Team;
import com.mohamad.entity.Country;

public class TeamModel {

	public void addTeam(DataSource dataSource, Team team) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "INSERT INTO team (team_name, country_id) VALUES (?,?);";
			statement = connect.prepareStatement(query);
			statement.setString(1, team.getTeamName());
			statement.setInt(2, team.getCountry().getCountryId());
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

	public void updateTeam(DataSource dataSource, Team team) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "UPDATE team "
					+ "SET team_name = ?, country_id=? "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			statement.setString(1, team.getTeamName());
			statement.setInt(2, team.getCountry().getCountryId());
			statement.setInt(3, team.getTeamId());
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
	public Team getTeam(DataSource dataSource, int teamId){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;       
		Team team = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM team "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			statement.setInt(1, teamId);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				team = new Team(resultSet.getInt("id"),resultSet.getString("team_name"), new CountryModel().getCountry(dataSource, resultSet.getInt("country_id")));
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
		
		return team;
	}

	public List<Team> getTeams(DataSource dataSource){
		List<Team> teams = new LinkedList<Team>(getTeamsMap(dataSource).values());
		return teams;
	}
	
	public Map<Integer, Team> getTeamsMap(DataSource dataSource){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;      
		Map<Integer,Team> teams = new HashMap<Integer,Team>();
		
		Map< Integer, Country > countries = new CountryModel().getCountriesMap(dataSource);
		
		Team team = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM team ";
			statement = connect.prepareStatement(query);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				team = new Team(resultSet.getInt("id"), resultSet.getString("team_name"), countries.get(resultSet.getInt("country_id")) );
				teams.put(team.getTeamId(), team);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error in get Teams Map");
			}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return teams;
	}

}
