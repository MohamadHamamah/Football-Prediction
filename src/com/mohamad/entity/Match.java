package com.mohamad.entity;

import java.sql.Date;
import java.util.List;

public class Match  {
	private int matchId;
	private Week week;
	private Team homeTeam;
	private Team awayTeam;
	private int homeScore;
	private int awayScore;
	private String competition;
	private Date matchTime;
	private Boolean doublable;
	private List<Prediction> predictions;
	
	public Match(int matchId) {
		this.matchId = matchId;
		this.week = null;
		this.homeTeam = null;
		this.awayTeam = null;
		this.homeScore = -1;
		this.awayScore = -1;
		this.competition = "";
		this.matchTime = null;
		this.doublable = false;
		this.predictions = null;
	}
	
	public Match(int matchId, Week week, Team homeTeam, Team awayTeam, int homeScore, int awayScore, String competition,
			Date matchTime, Boolean doublable, List<Prediction> predictions) {
		this(week, homeTeam, awayTeam, competition, matchTime, doublable);
		this.matchId = matchId;
		this.homeScore = homeScore;
		this.awayScore = awayScore;
		this.predictions = predictions;
	}
	public Match(Week week, Team homeTeam, Team awayTeam, String competition, Date matchTime, Boolean doublable) {
		this.matchId = 0;
		this.week = week;
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;
		this.homeScore = -1 ;
		this.awayScore = -1 ;
		this.competition = competition;
		this.matchTime = matchTime;
		this.doublable = doublable;
		predictions = null;
	}
	public int getMatchId() {
		return matchId;
	}
	public void setMatchId(int matchId) {
		this.matchId = matchId;
	}
	public Week getWeek() {
		return week;
	}
	public void setWeek(Week week) {
		this.week = week;
	}
	public Team getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Team getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}
	public int getHomeScore() {
		return homeScore;
	}
	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}
	public int getAwayScore() {
		return awayScore;
	}
	public void setAwayScore(int awayScore) {
		this.awayScore = awayScore;
	}
	public String getCompetition() {
		return competition;
	}
	public void setCompetition(String competition) {
		this.competition = competition;
	}
	public Date getMatchTime() {
		return matchTime;
	}
	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
	public Boolean getDoublable() {
		return doublable;
	}
	public void setDoublable(Boolean doublable) {
		this.doublable = doublable;
	}
	public List<Prediction> getPredictions() {
		return predictions;
	}
	public void setPredictions(List<Prediction> predictions) {
		this.predictions = predictions;
	}
	
	
}
