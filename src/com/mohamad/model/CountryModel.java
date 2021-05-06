package com.mohamad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mohamad.entity.Country;

public class CountryModel {

	public void addCountry(DataSource dataSource, Country country ) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "INSERT INTO country (country_name) VALUES (?);";
			statement = connect.prepareStatement(query);
			statement.setString(1, country.getCountryName());
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

	public void updateCountry(DataSource dataSource, Country country) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "UPDATE country "
					+ "SET country_name = ? "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			statement.setString(1, country.getCountryName());
			statement.setInt(2, country.getCountryId());
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
	
	public Country getCountry(DataSource dataSource, int countryId){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;       
		Country country = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM country "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			statement.setInt(1, countryId);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				country = new Country(resultSet.getInt("id"),resultSet.getString("country_name"), null);
			}	
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error in get country");
			}
		finally {
			try {
				connect.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return country;
	}
	
	public List<Country> getCountries(DataSource dataSource){
		List<Country> countries = new LinkedList<Country>(getCountriesMap(dataSource).values());
		return countries;
	}
	
	public Map< Integer, Country> getCountriesMap(DataSource dataSource){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;      
		Map<Integer,Country> countries = new HashMap<Integer,Country>();
		Country country = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM country ";
			statement = connect.prepareStatement(query);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				country = new Country(resultSet.getInt("id"),resultSet.getString("country_name"), null);
				countries.put(country.getCountryId(),country);
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
		
		return countries;
	}

}
