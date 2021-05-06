package com.mohamad.entity;

import java.util.List;

public class Country {
	private int countryId;
	private String countryName;
	private List<Team> teams;
	
	public Country(int countryId) {
		this.countryId = countryId;
		this.countryName = "";
		this.teams = null;
	}
	
	public Country(int countryId, String countryName, List<Team> teams) {
		this(countryName);
		this.countryId = countryId;
		this.teams = teams;
	}
	
	public Country(String countryName) {
		this.countryId = 0;
		this.countryName = countryName;
		this.teams = null;
	}
	public int getCountryId() {
		return countryId;
	}
	public void setCountryId(int countryId) {
		this.countryId = countryId;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public List<Team> getTeams() {
		return teams;
	}
	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	@Override
	public boolean equals(Object obj) {		
		Country country = null;
		if(!(obj instanceof Country))
			return false;
		else
			country = (Country) obj;
		return (this.getCountryId() == country.getCountryId());
	}
	
	
	
	
}
