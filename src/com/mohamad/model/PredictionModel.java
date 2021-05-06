package com.mohamad.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mohamad.entity.Match;
import com.mohamad.entity.Prediction;
import com.mohamad.entity.User;

public class PredictionModel {
	public void addPrediction(DataSource dataSource, Prediction prediction) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "INSERT INTO prediction (user_id, match_id, home_score, "
					+ "away_score, prediction_date, prediction_double) "
					+ "VALUES (?,?,?,?,?,?);";
			statement = connect.prepareStatement(query);
			setParameters(statement, prediction);
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
	
	public void addPredictions(DataSource dataSource, List<Prediction> predictions) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();	
			
			// Step 2: Create a SQL statements string
			String query = "INSERT INTO prediction (user_id, match_id, home_score, "
					+ "away_score, prediction_date, prediction_double) "
					+ "VALUES (?,?,?,?,?,?);";
			statement = connect.prepareStatement(query);
			connect.setAutoCommit(false);
			for (Prediction prediction : predictions) {
				setParameters(statement, prediction);
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
	
	public void updatePrediction(DataSource dataSource, Prediction prediction) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "UPDATE prediction SET user_id = ? , match_id = ?, home_score = ?,"
					+ "away_score = ?, prediction_date = ?,  prediction_double = ?)  "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			setParameters(statement, prediction);
			statement.setInt(9, prediction.getPredictionId());
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
	
	public void updatePredictions(DataSource dataSource, List<Prediction> predictions) {
		// Step 1: Initialize connection objects
		Connection connect = null;
		PreparedStatement statement = null;
        try {
			connect = dataSource.getConnection();	
			
			// Step 2: Create a SQL statements string
			String query = "UPDATE prediction SET user_id = ? , match_id = ?, home_score = ?,"
					+ "away_score = ?, prediction_date = ?,  prediction_double = ?)  "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			connect.setAutoCommit(false);
			for (Prediction prediction : predictions) {
				setParameters(statement, prediction);
				statement.setInt(9, prediction.getPredictionId());
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
	
	private void setParameters(PreparedStatement statement, Prediction prediction) throws SQLException {
		statement.setInt(1, prediction.getUser().getUser_id());
		statement.setInt(2, prediction.getMatch().getMatchId());
		statement.setInt(3, prediction.getHomePredictedScore());
		statement.setInt(4, prediction.getAwayPredictedScore());
		statement.setDate(5, prediction.getPredictionDate());
		statement.setBoolean(6,prediction.getPredictionDoublable());
	}

	public Prediction getPrediction(DataSource dataSource, int predictionId){
		// Step 1: Initialize connection objects
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;       
		Prediction prediction = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * "
					+ "FROM prediction "
					+ "WHERE id = ?";
			statement = connect.prepareStatement(query);
			statement.setInt(1, predictionId);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				
				prediction = new Prediction(resultSet.getInt("id"),
						new UserModel().getUser(dataSource, resultSet.getInt("user_id")) , 
						new MatchModel().getMatch(dataSource, resultSet.getInt("match_id")),
						resultSet.getInt("home_score"),resultSet.getInt("away_score"),
						resultSet.getDate("prediction_date"),
						resultSet.getBoolean("prediction_doublable"));
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
		
		return prediction;
	}
	
	public List<Prediction> getPredictions(DataSource dataSource, int weekId, int userId){
		// Step 1: Initialize connection objects
		List<Prediction> predictions = new LinkedList<Prediction>();
		Map<Integer,Match> matches = new MatchModel().getMatchesMap(dataSource, weekId);
		User user = new UserModel().getUser(dataSource, userId);
		Connection connect = null;
        PreparedStatement statement = null;
		ResultSet resultSet = null;       
		Prediction prediction = null;
		try {
			connect = dataSource.getConnection();			
			// Step 2: Create a SQL statements string
			String query = "SELECT * \n "
					+ "FROM prediction \n "
					+ "WHERE match_id IN (select football_prediction.match.id from football_prediction.match where week_id = ?) \n "
					+ "AND user_id = ? ";
			statement = connect.prepareStatement(query);
			statement.setInt(1, weekId);
			statement.setInt(2, userId);
			// Step 3: Execute SQL query
			resultSet = statement.executeQuery();
			// Step 4: Process the result set
			while(resultSet.next()){
				prediction = new Prediction(resultSet.getInt("id"), user, matches.get(resultSet.getInt("match_id")),
						resultSet.getInt("home_score"),resultSet.getInt("away_score"), resultSet.getDate("prediction_date"),
						resultSet.getBoolean("prediction_double"));
				predictions.add(prediction);
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
		
		if (predictions.size() == 0) {
			for (Match match : new LinkedList<Match>(matches.values())) {
				prediction = new Prediction(-1, user, match, -1,-1, null,false);
				predictions.add(prediction);
			}
		}
		
		return predictions;
	}
	
	public void addPoints(DataSource dataSource,int usersID) {
		String query = "SELECT * FROM football_prediction.prediction \n"
				+ "JOIN football_prediction.user a \n"
				+ " ON user_id = a.id \n"
				+ "where user_id=1 and prediction_date > a.points_date; ";
	}

	public List<Prediction> getPredictions(DataSource dataSource, Match match) {
		// Step 1: Initialize connection objects
				List<Prediction> predictions = new LinkedList<Prediction>();
				Connection connect = null;
		        PreparedStatement statement = null;
				ResultSet resultSet = null;       
				Prediction prediction = null;
				try {
					connect = dataSource.getConnection();			
					// Step 2: Create a SQL statements string
					String query = "SELECT * \n "
							+ "FROM prediction \n "
							+ "WHERE match_id = ? ";
					statement = connect.prepareStatement(query);
					statement.setInt(1, match.getMatchId());
					// Step 3: Execute SQL query
					resultSet = statement.executeQuery();
					// Step 4: Process the result set
					while(resultSet.next()){
						prediction = new Prediction(resultSet.getInt("id"), new User(resultSet.getInt("user_id")), match,
								resultSet.getInt("home_score"),resultSet.getInt("away_score"), resultSet.getDate("prediction_date"),
								resultSet.getBoolean("prediction_double"));
						predictions.add(prediction);
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
				return predictions;
	}

}
