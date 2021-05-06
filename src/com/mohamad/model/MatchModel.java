package com.mohamad.model;

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

import com.mohamad.entity.Match;
import com.mohamad.entity.Team;
import com.mohamad.entity.Week;

public class MatchModel {

	public void addMatch(DataSource dataSource, Match match) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "INSERT INTO football_prediction.match (week_id, home_team, away_team, "
					+ "competition, match_time, match_doublable) "
					+ "VALUES (?,?,?,?,?,?);";
			statement = connect.prepareStatement(query);
			setParameters(statement, match);
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
	
	public void addMatches(DataSource dataSource, List<Match> matches, int weekId) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();	
			
			// Step 2: Create a SQL statements string
			String query = "INSERT INTO football_prediction.match (week_id, home_team, away_team, "
					+ "competition, match_time, match_doublable) "
					+ "VALUES (?,?,?,?,?,?);";
			statement = connect.prepareStatement(query);
			connect.setAutoCommit(false);
			for (Match match : matches) {
				setParameters(statement, match);
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
	
	public void updateMatch(DataSource dataSource, Match match) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "UPDATE football_prediction.match SET week_id = ? , home_team = ?, away_team = ?, "
					+ "competition = ?, match_time = ?, match_doublable = ?)  "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			setParameters(statement, match);
			statement.setInt(7, match.getMatchId());
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
	
	public void updateMatches(DataSource dataSource, List<Match> matches) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();	
			
			// Step 2: Create a SQL statements string
			String query = "UPDATE football_prediction.match SET week_id = ? , home_team = ?, away_team = ?, "
					+ "competition = ?, match_time = ?, match_doublable = ?)  "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			connect.setAutoCommit(false);
			for (Match match : matches) {
				setParameters(statement, match);
				statement.setInt(7, match.getMatchId());
				statement.addBatch();
			}
			
			// Step 3: Execute SQL query
			int[] count = statement.executeBatch();	
			for (int i = 0; i < count.length; i++) {
				System.out.println(count[i]);
			}
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
	
	public void addResult(DataSource dataSource, Match match) {
		// Step 1: Initialize connection objects				
				Connection connect = null;
				PreparedStatement statement = null;
		        try {
					connect = dataSource.getConnection();			
					// Step 2: Create a SQL statements string
					String query = "UPDATE football_prediction.match SET home_score = ?, away_score = ? "
							+ "WHERE id = ?";
					statement = connect.prepareStatement(query);
					statement.setInt(1, match.getHomeScore());
					statement.setInt(2, match.getAwayScore());
					statement.setInt(3, match.getMatchId());
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
	
	public void addResults(DataSource dataSource, List<Match> matches) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();	
			
			// Step 2: Create a SQL statements string
			String query = "UPDATE football_prediction.match SET home_score = ?, away_score = ?"
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			connect.setAutoCommit(false);
			for (Match match : matches) {
				statement.setInt(1, match.getHomeScore());
				statement.setInt(2, match.getAwayScore());
				statement.setInt(3, match.getMatchId());
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
	
	private void setParameters(PreparedStatement statement, Match match) throws SQLException {
		statement.setInt(1, match.getMatchId());
		statement.setInt(2, match.getHomeTeam().getTeamId());
		statement.setInt(3, match.getAwayTeam().getTeamId());
		statement.setString(4,match.getCompetition());
		statement.setDate(5,match.getMatchTime());
		statement.setBoolean(6, match.getDoublable());
	}

	public Match getMatch(DataSource dataSource, int matchId){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;       
		Match match = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM football_prediction.match "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			statement.setInt(1, matchId);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				
				match = new Match(resultSet.getInt("id"),
						new WeekModel().getWeek(dataSource, resultSet.getInt("week_id")) , 
						new TeamModel().getTeam(dataSource, resultSet.getInt("home_team")),
						new TeamModel().getTeam(dataSource, resultSet.getInt("away_team")),
						resultSet.getInt("home_score"),resultSet.getInt("away_score"),
						resultSet.getString("competition"), resultSet.getDate("match_time"),
						resultSet.getBoolean("match_doublable"), null );
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
		
		return match;
	}
	
	public List<Match> getMatches(DataSource dataSource, int weekId){
		List<Match> matches = new LinkedList<Match>(getMatchesMap(dataSource, weekId).values());
		return matches;
	}
	
	public Map<Integer, Match> getMatchesMap(DataSource dataSource, int weekId){
		// Step 1: Initialize connection objects
		Map<Integer, Match> matches = new HashMap<Integer, Match>();
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;    
		Week week = new WeekModel().getWeek(dataSource, weekId);
		Map< Integer, Team > teams = new TeamModel().getTeamsMap(dataSource);
		
		Match match = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM football_prediction.match "
					+ "WHERE week_id = ? "
					+ "ORDER BY match_time;";
			statement = connect.prepareStatement(query);
			statement.setInt(1, weekId);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				match = new Match(resultSet.getInt("id"), week, 
						teams.get(resultSet.getInt("home_team")), teams.get(resultSet.getInt("away_team")),
						resultSet.getInt("home_score"),resultSet.getInt("away_score"),
						resultSet.getString("competition"), resultSet.getDate("match_time"),
						resultSet.getBoolean("match_doublable"), null );
				matches.put(match.getMatchId(), match);
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
		return matches;
	}
	
	public List<String> getCompetitions(DataSource dataSource){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;    
		List<String> competitions = new LinkedList<String>();
		Pattern p = Pattern.compile(Pattern.quote("'") + "(.*?)" + Pattern.quote("'"));
		Matcher m;
		
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SHOW COLUMNS FROM football_prediction.match LIKE 'competition'";
			statement = connect.prepareStatement(query);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				String values = resultSet.getString(2);
				m = p.matcher(values);
				while (m.find()) {
					competitions.add(m.group(1));
				}
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
			
		return competitions;
	}
	
}
