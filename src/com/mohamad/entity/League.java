package com.mohamad.entity;
import java.util.List;

public class League {
	private int leagueId;
	private String leagueName;
	private String leagueCode;
	private Boolean isClose;
	private List<User> leagueUsers;
	
	public League(int leagueId) {
		this.leagueId = leagueId;
		this.leagueName = "";
		this.leagueCode = "";
		this.isClose = false;
		this.leagueUsers = null;
	}
	
	public League(int leagueId, String leagueName, String leagueCode, Boolean isClose, List<User> leagueUsers) {
		this(leagueName, leagueCode, isClose);
		this.leagueId = leagueId;
		this.leagueUsers = leagueUsers;
	}
	public League(String leagueName, String leagueCode, Boolean isClose) {
		this.leagueId = 0 ;
		this.leagueName = leagueName;
		this.leagueCode = leagueCode;
		this.isClose = isClose;
		this.leagueUsers = null ;
	}
	public int getLeagueId() {
		return leagueId;
	}
	public void setLeagueId(int leagueId) {
		this.leagueId = leagueId;
	}
	public String getLeagueName() {
		return leagueName;
	}
	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}
	public String getLeagueCode() {
		return leagueCode;
	}
	public void setLeagueCode(String leagueCode) {
		this.leagueCode = leagueCode;
	}
	public Boolean getIsClose() {
		return isClose;
	}
	public void setIsClose(Boolean isClose) {
		this.isClose = isClose;
	}
	public List<User> getLeagueUsers() {
		return leagueUsers;
	}
	public void setLeagueUsers(List<User> leagueUsers) {
		this.leagueUsers = leagueUsers;
	}
	
	
}
