package com.mohamad.entity;

import java.util.List;

public class Season {
	private int seasonId;
	private String seasonName;
	private List<Week> weeks;
	
	public Season(int seasonId) {
		this.seasonId = seasonId;
		this.seasonName = "";
		this.weeks = null;
	}
	
	public Season(int seasonId, String seasonName, List<Week> weeks ) {
		this(seasonName);
		this.seasonId = seasonId;
		this.weeks = weeks;
	}
	
	public Season(String seasonName) {
		this.seasonId = 0;
		this.seasonName = seasonName;
		this.weeks = null;
	}
	
	public int getSeasonId() {
		return seasonId;
	}
	public void setSeasonId(int seasonId) {
		this.seasonId = seasonId;
	}
	public List<Week> getWeeks() {
		return weeks;
	}
	public void setWeeks(List<Week> weeks) {
		this.weeks = weeks;
	}

	public String getSeasonName() {
		return seasonName;
	}

	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	
	
}
