package com.mohamad.entity;

import java.util.List;

public class Week {
	private int weekId;
	private Season season;
	private int weekNumber;
	private List<Match> matches;
	
	public Week(int weekId) {
		this.weekId = weekId;
		this.weekNumber = 0;
		this.season = null;
		this.matches = null;
	}
	
	public Week(Season season, int weekNumber) {
		this.season = season;
		this.weekNumber = weekNumber;
		this.matches = null;
	}
	
	public Week(int weekId, Season season, int weekNumber) {
		this(season, weekNumber);
		this.weekId = weekId;
	}
	
	public Week(int weekId, Season season, int weekNumber, List<Match> matches) {
		this(weekId, season, weekNumber);
		this.matches = matches;
	}
	
	public int getWeekId() {
		return weekId;
	}
	public void setWeekId(int weekId) {
		this.weekId = weekId;
	}
	public Season getSeason() {
		return season;
	}
	public void setSeasonId(Season season) {
		this.season = season;
	}
	public int getWeekNumber() {
		return weekNumber;
	}
	public void setWeekNumber(int weekNumber) {
		this.weekNumber = weekNumber;
	}
	public List<Match> getMatches() {
		return matches;
	}
	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
}
