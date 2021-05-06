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
import javax.sql.DataSource;

import com.mohamad.entity.Match;
import com.mohamad.entity.Prediction;
import com.mohamad.entity.User;
import com.mohamad.entity.Week;
import com.mohamad.model.MatchModel;
import com.mohamad.model.PredictionModel;
import com.mohamad.model.TeamModel;
import com.mohamad.model.UserModel;
import com.mohamad.model.WeekModel;

/**
 * Servlet implementation class UserSpace
 */
@WebServlet("/UserSpace")
public class UserSpace extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@Resource(name = "jdbc/project")
    private DataSource dataSource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserSpace() {
        super(); 
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();
		
		if(request.getSession().getAttribute("userId") == null){
			response.sendRedirect( request.getContextPath() +"/login.jsp");
			return;
	    }
		
		switch (page) {
		case "updatepredictions":
			updatePredictionsFormLoader(request, response);
			break;
		case "userresult":
			showUserResults(request, response);
			break;
		case "browsematches" :
			browseMatchesFormLoader(request, response);
			break;
		case "addleague":
			addLeague(request, response);
			break;
		case "joinleague":
			joinLeague(request, response);
			break;
		case "leaguestanding":
			showLeagueStanding(request, response);
			break;
		case "standings":	
		     showDefaultStanding(request, response);
		     break;
		case "compareresults":	
		     compareUserResults(request, response);
		     break;
		default:
			errorPage(request, response);	
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("page");
		page = page.toLowerCase();
		switch (page) {
		case "updatepredictions":
			updatePredictions(request, response);
			break;
		case "userresult":
			showUserResults(request, response);
			break;
		case "browsematches" :
			browseMatchesFormLoader(request, response);
			break;
		case "addleague":
			addLeague(request, response);
			break;
		case "joinleague":
			joinLeague(request, response);
			break;
		case "leaguestanding":
			showLeagueStanding(request, response);
			break;
		case "standings":	
		     showDefaultStanding(request, response);
		     break;
		case "compareresults":	
		     compareUserResults(request, response);
		     break;
		default:
			errorPage(request, response);	
		}
	}

	
	private void updatePredictionsFormLoader(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int weekId = Integer.valueOf(request.getParameter("week"));
		List<Prediction> predictions = new PredictionModel().getPredictions(dataSource, weekId, 1);
		List<Match> matches = new MatchModel().getMatches(dataSource, weekId);		
		request.setAttribute("matches", matches ); 
		request.setAttribute("predictions", predictions );
		request.getRequestDispatcher("updatePredictions.jsp").forward(request, response);
	}
	
	private void updatePredictions(HttpServletRequest request, HttpServletResponse response) {
		List<Prediction> predictions = new LinkedList<Prediction>();
		int userId = (int) request.getSession().getAttribute("userId");
		User user = new User(userId);		
		Date date = new Date(System.currentTimeMillis());
		int doubleMatch = Integer.valueOf(request.getParameter("doubleMatch"));
		for (int i = 0; i < 10; i++) {
			Boolean doublable = false;
			int predictionId = Integer.valueOf(request.getParameter("prediction"+i));
			Match match = new Match(Integer.valueOf(request.getParameter("matchId"+i)));
			int homeResult = Integer.valueOf(request.getParameter("homeTeamResult"+i));
			int awayResult = Integer.valueOf(request.getParameter("awayTeamResult"+i));	
			if (i == doubleMatch) {
				doublable = true;
			}
			Prediction prediction = new Prediction(predictionId, user, match, homeResult, awayResult, date, doublable);
			predictions.add(prediction);
		}
		if (Integer.valueOf(request.getParameter("prediction0")) == -1) {
			new PredictionModel().addPredictions(dataSource, predictions);
		} else {
			new PredictionModel().updatePredictions(dataSource, predictions);
		}
		
	}

	private void browseMatchesFormLoader(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		request.setAttribute("matches", new MatchModel().getMatches(dataSource,Integer.valueOf(request.getParameter("week")))); 
		request.setAttribute("teams", new TeamModel().getTeams(dataSource));
		request.getRequestDispatcher("weekResults.jsp").forward(request, response);
	}

	private void errorPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void compareUserResults(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void showDefaultStanding(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		List<User> users = new UserModel().getUsers(dataSource);
		request.setAttribute("users",users);
		request.getRequestDispatcher("showStanding.jsp").forward(request, response);
	}

	private void showLeagueStanding(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		showDefaultStanding(request, response);
	}

	private void joinLeague(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void addLeague(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
	}

	private void showUserResults(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int weekId = Integer.valueOf(request.getParameter("week"));
		int userId = Integer.valueOf(request.getParameter("user"));
		List<Prediction> predictions = new PredictionModel().getPredictions(dataSource, weekId, userId);
		request.setAttribute("predictions", predictions );
		request.setAttribute("points", Calculation.calculatePoints(predictions));
		request.getRequestDispatcher("showUserResults.jsp").forward(request, response);
	}

}


