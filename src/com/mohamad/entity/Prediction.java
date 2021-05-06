package com.mohamad.entity;

import java.sql.Date;

public class Prediction {
	private int predictionId;
	private User user;
	private Match match;
	private int homePredictedScore;
	private int awayPredictedScore;
	private Date predictionDate;
	private Boolean predictionDoublable;
	
	
	public Prediction(User user, Match match, int homePredictedScore, int awayPredictedScore, Date predictionDate,
			Boolean predictionDoublable) {
		this.predictionId = 0;
		this.user = user;
		this.match = match;
		this.homePredictedScore = homePredictedScore;
		this.awayPredictedScore = awayPredictedScore;
		this.predictionDate = predictionDate;
		this.predictionDoublable = predictionDoublable;
	}

	public Prediction(int predictionId, User user, Match match, int homePredictedScore, int awayPredictedScore,
			Date predictionDate, Boolean predictionDoublable) {
		this(user, match, homePredictedScore, awayPredictedScore, predictionDate,predictionDoublable);
		this.predictionId = predictionId;
		
	}
	
	public int getPredictionId() {
		return predictionId;
	}
	public void setPredictionId(int predictionId) {
		this.predictionId = predictionId;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatchId(Match match) {
		this.match = match;
	}
	public int getHomePredictedScore() {
		return homePredictedScore;
	}
	public void setHomePredictedScore(int homePredictedScore) {
		this.homePredictedScore = homePredictedScore;
	}
	public int getAwayPredictedScore() {
		return awayPredictedScore;
	}
	public void setAwayPredictedScore(int awayPredictedScore) {
		this.awayPredictedScore = awayPredictedScore;
	}
	public Date getPredictionDate() {
		return predictionDate;
	}
	public void setPredictionDate(Date predictionDate) {
		this.predictionDate = predictionDate;
	}
	public Boolean getPredictionDoublable() {
		return predictionDoublable;
	}
	public void setPredictionDoublable(Boolean predictionDoublable) {
		this.predictionDoublable = predictionDoublable;
	}
	
	
	
}
