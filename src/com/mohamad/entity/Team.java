package com.mohamad.entity;

public class Team {
	private int teamId;
	private String teamName;
	private Country country;
	
	public Team(int teamId) {
		this.teamId = teamId;
		this.teamName = "";
		this.country = null;
	}
	
	public Team(String teamName, Country country) {
		teamId = 0;
		this.teamName = teamName;
		this.country = null;
	}
	
	public Team(int teamId, String teamName, Country country) {
		this(teamName, country);		
		this.teamId = teamId;
		this.country = country;
	}
	
	
	
	public int getTeamId() {
		return teamId;
	}
	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
