package com.mohamad.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.mohamad.entity.Week;
import com.mohamad.model.MatchModel;
import com.mohamad.model.WeekModel;

@WebListener
public class Configuration implements ServletContextListener {

	@Resource(name = "jdbc/project")
    private DataSource dataSource;
	

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Hello From Listener ");
		Week lastWeek = new WeekModel().getLastWeek(dataSource);
		lastWeek.setMatches(new MatchModel().getMatches(dataSource, lastWeek.getWeekId()));
		sce.getServletContext().setAttribute("lastWeek", lastWeek );
		sce.getServletContext().setAttribute("competitions", new MatchModel().getCompetitions(dataSource));
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Hello From Destroyer ");
	}
	
}
