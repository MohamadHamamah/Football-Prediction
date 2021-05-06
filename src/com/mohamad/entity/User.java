package com.mohamad.entity;
import java.sql.Date;
import java.util.List;

public class User {
	private int userId;
	private String userName;
	private String email;
	private String password;
	private char gender;
	private Date birthDate;
	private String mobileNo;
	private int points;
	private Date datePoints;
	private List<League> leagues; 
	private List<Prediction> predictions;
	
	
	
	public User(int user_id) {
		this.userId = user_id;
		this.userName = "";
		this.email = "";
		this.password = "";
		this.gender = ' ';
		this.birthDate = null;
		this.mobileNo = "";
		this.points = 0 ;
		this.leagues = null;
		this.predictions = null;
		this.datePoints = null;
	}
	
	public User(String username, String email, String password, char gender, Date birthDate, String mobileNo) {
		userId = 0;
		this.userName = username;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.birthDate = birthDate;
		this.mobileNo = mobileNo;
		this.points = 0 ;
		this.leagues = null;
		this.predictions = null;
		this.datePoints = null;
	}
	
	public User(int user_id, String username, String email, String password, char gender, Date birthDate, String mobileNo) {
		this(username, email, password, gender, birthDate, mobileNo);
		this.userId = user_id;
	}

	public User(int user_id, String username, String email, String password, char gender, Date birthDate, String mobileNo, int points) {
		this(user_id, username, email, password, gender, birthDate, mobileNo);
		this.points = points;
	}
	
	public int getUser_id() {
		return userId;
	}
	public void setUser_id(int users_id) {
		this.userId = users_id;
	}
	public String getUsername() {
		return userName;
	}
	public void setUsername(String username) {
		this.userName = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<League> getLeagues() {
		return leagues;
	}

	public void setLeagues(List<League> leagues) {
		this.leagues = leagues;
	}

	public List<Prediction> getPredictions() {
		return predictions;
	}

	public void setPredictions(List<Prediction> predictions) {
		this.predictions = predictions;
	}
	
	public Date getDatePoints() {
		return datePoints;
	}

	public void setDatePoints(Date datePoints) {
		this.datePoints = datePoints;
	}

	
}
