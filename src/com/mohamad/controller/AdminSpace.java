package com.mohamad.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.mohamad.entity.Country;
import com.mohamad.entity.Match;
import com.mohamad.entity.Season;
import com.mohamad.entity.Team;
import com.mohamad.entity.User;
import com.mohamad.entity.Week;
import com.mohamad.model.CountryModel;
import com.mohamad.model.MatchModel;
import com.mohamad.model.PredictionModel;
import com.mohamad.model.SeasonModel;
import com.mohamad.model.TeamModel;
import com.mohamad.model.UserModel;
import com.mohamad.model.WeekModel;


@WebServlet("/AdminSpace")
public class AdminSpace extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name = "jdbc/project")
    private DataSource dataSource;
	
    public AdminSpace() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		if(page != null) {
			page = page.toLowerCase();
		}
		else {
			page = "controlpanel";
		}
		page = manageSession(request, response, page);
		System.out.println("Admin Id" +request.getSession().getAttribute("adminId"));
		
		switch (page) {
		case "login":		
			loginLoadForm(request, response);
			break;
		case "controlpanel":
			controlPanel(request, response);
			break;
		case "addseason" :
			addSeasonFormLoader(request, response);
			break;
		case "addweek" :
			addWeekFormLoader(request, response);
			break;
		case "updateweek":
			updateWeekFormLoader(request, response);
			break;
		case "addcountry":
			addCountryFormLoader(request, response);
			break;
		case "updatecountry":
			updateCountryFormLoader(request, response);
			break;
		case "addteam":	
		     addTeamFormLoader(request, response);
		     break;
		case "updateteam":	
		     updateTeamFormLoader(request, response);
		     break;
		case "addmatch":	
		     addMatchFormLoader(request, response);
		     break;
		case "updatematch":	
		     updateMatchFormLoader(request, response);
		     break;
		case "updateresults":	
		     updateResultsFormLoader(request, response);
		     break; 
		case "updatepoints":
			updatePoints(request, response);
		default:
			errorPage(request, response);	
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("form");
		page = page.toLowerCase();
		switch (page) {
		case "login":
			login(request, response);
			break;
		case "addseason" :
			addSeason(request, response);
			break;
		case "addweek" :
			addWeek(request, response);
			break;
		case "updateweek":
			updateWeek(request, response);
			break;
		case "addcountry":
			addCountry(request, response);
			break;
		case "updatecountry":
			updateCountry(request, response);
			break;
		case "addteam":	
		     addTeam(request, response);
		     break;
		case "updateteam":	
		     updateTeam(request, response);
		     break;
		case "addmatch":	
		     addMatch(request, response);
		     break;
		case "updatematch":	
		     updateMatch(request, response);
		     break;
		case "updateresults":	
		     updateResults(request, response);
		     break; 
		default:
			 errorPage(request, response);	
		}
	} 


	private String manageSession(HttpServletRequest request, HttpServletResponse response, String page) 
		throws ServletException, IOException {
		if(request.getSession().getAttribute("adminId") == null && !page.equals("login")){
	    	return "login";
	    }
		return page;
	}

	private void updatePoints(HttpServletRequest request, HttpServletResponse response) {
		//
	}

	private void loginLoadForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("title", "Admin Login");
		request.getRequestDispatcher("adminLogin.jsp").forward(request, response);
	}
	
	private void login(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String email = request.getParameter("email"); 
		String password = request.getParameter("password");
		User user = new UserModel().getAdminUser(dataSource, email);
		
		if(user != null && user.getPassword().equals(password)) {
			//Invalidating session if any
			request.getSession().invalidate();
			HttpSession newSession = request.getSession(true);
		    newSession.setMaxInactiveInterval(300);
		    newSession.setAttribute("adminId", user.getUser_id());
		    newSession.setAttribute("userId", user.getUser_id());
		    response.sendRedirect("controlPanel.jsp");
		}else {
			String message = "";
			if(user == null) {
				message = "No such email";
			}
			else {
				message = "Incorrect Password";
			}
			response.sendRedirect(request.getContextPath() + "/AdminSpace?page=login&message=" + message );
		}
	}

	
	private void controlPanel(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void updateResultsFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("matches", new MatchModel().getMatches(dataSource,Integer.valueOf(request.getParameter("week")))); 
		request.setAttribute("teams", new TeamModel().getTeams(dataSource));
		request.getRequestDispatcher("updateResults.jsp").forward(request, response);
	}
	
	private void updateResults(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// updating only one match
		String resultUpdated = request.getParameter("updateResult");
		int id = Integer.valueOf(request.getParameter("matchId"+resultUpdated));
		int homeResult = Integer.valueOf(request.getParameter("homeTeamResult"+resultUpdated));
		int awayResult = Integer.valueOf(request.getParameter("awayTeamResult"+resultUpdated));
		Match oldResultMatch = new MatchModel().getMatch(dataSource, id);
		Match match = new Match(id, null, null, null, homeResult, awayResult, null, null, false, null);
		oldResultMatch.setPredictions(new PredictionModel().getPredictions(dataSource, match));
		new MatchModel().addResult(dataSource, match);
		
		List<Integer> oldPoints = null;
		
		if (oldResultMatch.getHomeScore() != -1) {
			oldPoints = Calculation.calculatePoints(oldResultMatch.getPredictions());
		}
		
		oldResultMatch.setHomeScore(match.getHomeScore());
		oldResultMatch.setAwayScore(match.getAwayScore());
		List<Integer> points = Calculation.calculatePoints(oldResultMatch.getPredictions()); 
		
		if (oldPoints != null) {
			points = Calculation.calculatePointsDifferenc(oldPoints, points);
		}
		// update the points for each user accordingly
		new UserModel().updatePoints(dataSource, oldResultMatch.getPredictions(), points);
		
		
		response.sendRedirect(request.getContextPath()+"/AdminSpace?page=updateResults&week="+ request.getParameter("week"));
//		request.getRequestDispatcher("/AdminSpace?page=updateResults&week="+ request.getParameter("week")).forward(request, response);
		
		
		
		// updating the whole week results
		/* List<Match> matches = new LinkedList<Match>();
		
		for (int i = 0; i < 10; i++) {
			int id = Integer.valueOf(request.getParameter("matchId"+i));
			int homeResult = Integer.valueOf(request.getParameter("homeTeamResult"+i));
			int awayResult = Integer.valueOf(request.getParameter("awayTeamResult"+i));
			Match match = new Match(id, null, null, null, homeResult, awayResult, null, null, false, null);
			matches.add(match);
		}
		new MatchModel().addResults(dataSource, matches);*/
	}

	private void updateMatchFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Match match = new MatchModel().getMatch(dataSource, Integer.valueOf(request.getParameter("matchId")));
		request.setAttribute("match", match);
		request.getRequestDispatcher("updateMatch.jsp").forward(request, response);
	}
	

	private void updateMatch(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.valueOf(request.getParameter("matchId"));
		Team homeTeam = new Team(Integer.valueOf(request.getParameter("homeTeam")));
		Team awayTeam = new Team(Integer.valueOf(request.getParameter("awayTeam")));
		String competition = request.getParameter("competition");
		Boolean doublable = Boolean.valueOf(request.getParameter("doublable"));
		Date matchDate = Date.valueOf("matchDate");
		Week week = new Week(Integer.valueOf(request.getParameter("week")));
		Match match = new Match(id, week, homeTeam, awayTeam, -1, -1, competition, matchDate, doublable, null);
		new MatchModel().updateMatch(dataSource, match);
	}

	private void addMatchFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("countries", new CountryModel().getCountries(dataSource));
		request.setAttribute("teams", new TeamModel().getTeams(dataSource));
		request.getRequestDispatcher("addMatch.jsp").forward(request, response);
		
	}
	
	private void addMatch(HttpServletRequest request, HttpServletResponse response) {
		Team homeTeam = new Team(Integer.valueOf(request.getParameter("homeTeam")));
		Team awayTeam = new Team(Integer.valueOf(request.getParameter("awayTeam")));
		String competition = request.getParameter("competition");
		Boolean doublable = Boolean.valueOf(request.getParameter("doublable"));
		Date matchDate = Date.valueOf("matchDate");
		Match match = new Match( (Week) getServletContext().getAttribute("lastWeek"), homeTeam, awayTeam, competition, matchDate, doublable);
		new MatchModel().addMatch(dataSource, match);
	}

	private void updateTeamFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Team team = new TeamModel().getTeam(dataSource, 21);
		request.setAttribute("team", team);
		request.setAttribute("countries", new CountryModel().getCountries(dataSource));
		request.getRequestDispatcher("updateTeam.jsp").forward(request, response);
	}
	
	private void updateTeam(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		Country country = new Country(Integer.valueOf(request.getParameter("country")));
		Team team = new Team(request.getParameter("teamName"), country);
		new TeamModel().updateTeam(dataSource, team);
	}

	private void addTeamFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("addTeam.jsp").forward(request, response);
	}
	
	private void addTeam(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("teams", new TeamModel().getTeams(dataSource));
		request.setAttribute("countries", new CountryModel().getCountries(dataSource));
		request.getRequestDispatcher("addMatch.jsp").forward(request, response);
	}
	
	private void updateCountryFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("country", new CountryModel().getCountry(dataSource, Integer.valueOf(request.getParameter("country_id"))));
		request.getRequestDispatcher("updateCountry.jsp").forward(request, response);
	}
	
	private void updateCountry(HttpServletRequest request, HttpServletResponse response) {
		Country country = new Country(Integer.valueOf(request.getParameter("country_id")), request.getParameter("country_name"), null);
		new CountryModel().updateCountry(dataSource, country);
	}

	private void addCountryFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("addCountry.jsp").forward(request, response);
	}
	
	private void addCountry(HttpServletRequest request, HttpServletResponse response) {
		Country country = new Country(request.getParameter("countryName"));
		new CountryModel().addCountry(dataSource, country);
	}

	private void updateWeekFormLoader(HttpServletRequest request, HttpServletResponse response)  
			throws ServletException, IOException {
		request.setAttribute("teams", new TeamModel().getTeams(dataSource));
		request.setAttribute("countries", new CountryModel().getCountries(dataSource));
		request.setAttribute("matches", new MatchModel().getMatches(dataSource,(int) request.getAttribute("week"))); 
		request.getRequestDispatcher("updateWeek.jsp").forward(request, response);
	}
	

	private void updateWeek(HttpServletRequest request, HttpServletResponse response) {
		List<Match> matches = new LinkedList<Match>();
		Week week = new Week(Integer.valueOf(request.getParameter("lastWeek")));
		
		for (int i = 0; i < 10; i++) {
			int id = Integer.valueOf(request.getParameter("matchId"+i));
			Team homeTeam = new Team(Integer.valueOf(request.getParameter("homeTeam"+i)));
			Team awayTeam = new Team(Integer.valueOf(request.getParameter("awayTeam"+i)));
			String competition = request.getParameter("competition"+i);
			Boolean doublable = Boolean.valueOf(request.getParameter("doublable"+i));
			Date matchDate = Date.valueOf("matchDate"+i);
			Match match = new Match(id, week, homeTeam, awayTeam, -1, -1, competition, matchDate, doublable, null);
			matches.add(match);
		}
		new MatchModel().updateMatches(dataSource, matches);
	}

	private void addWeekFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("title", "New Week");
		request.setAttribute("teams", new TeamModel().getTeams(dataSource));
		request.setAttribute("countries", new CountryModel().getCountries(dataSource));
		request.getRequestDispatcher("addWeek.jsp").forward(request, response);
		
	}
	
	private void addWeek(HttpServletRequest request, HttpServletResponse response) {
		List<Match> matches = new LinkedList<Match>();
		Week week = (Week) getServletContext().getAttribute("lastWeek");
		week.setWeekId(week.getWeekId()+1);
		week.setWeekNumber(week.getWeekNumber()+1);
		for (int i = 0; i < 10; i++) {
			Team homeTeam = new Team(Integer.valueOf(request.getParameter("homeTeam"+i)));
			Team awayTeam = new Team(Integer.valueOf(request.getParameter("awayTeam"+i)));
			String competition = request.getParameter("competition"+i);
			Boolean doublable = Boolean.valueOf(request.getParameter("doublable"+i));
			Date matchDate = Date.valueOf("matchDate"+i);
			Match match = new Match(week, homeTeam, awayTeam, competition, matchDate, doublable);
			matches.add(match);
		}
		week.setMatches(matches);
		new WeekModel().addWeek(dataSource, week);
		getServletContext().setAttribute("lastWeek", week);
		
	}
	private void addSeasonFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setAttribute("title", "Add Season");
		request.getRequestDispatcher("addSeason.jsp").forward(request, response);
		
	}
	
	private void addSeason(HttpServletRequest request, HttpServletResponse response) {
		Season season = new Season(request.getParameter("season_start"));
		new SeasonModel().addSeason(dataSource, season);
	}
	
	private void errorPage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.getRequestDispatcher("error.jsp").forward(request, response);
		
	}
}
