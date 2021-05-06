package com.mohamad.controller;

import java.util.LinkedList;
import java.util.List;

import com.mohamad.entity.Prediction;

public class Calculation {
	public static List<Integer> calculatePoints(List<Prediction> predictions) {
		List<Integer> points = new LinkedList<Integer>();
		for (Prediction prediction : predictions) {
			int matchGoalDifference = prediction.getMatch().getHomeScore() - prediction.getMatch().getAwayScore();
			int predictGoadDifference = prediction.getHomePredictedScore() - prediction.getAwayPredictedScore();
			if (matchGoalDifference*predictGoadDifference >= 0){
				if (matchGoalDifference*predictGoadDifference == 0 && matchGoalDifference+predictGoadDifference != 0) {
					points.add(0);
				}
				else{
					if (matchGoalDifference != predictGoadDifference) {
						points.add(1);
					}
					else if (prediction.getMatch().getHomeScore() != prediction.getHomePredictedScore()) {
						points.add(2);
					}
					else {
						points.add(3);
					}
				}
			}
			else {
				points.add(0);
			}
			if (prediction.getPredictionDoublable() == true) {
				int doublePoint = points.remove(points.size()-1);
				if ( doublePoint == 0) {
					points.add(-3);
				}
				else {
					points.add(doublePoint*2);
				}
			}
		}
		return points;
	}

	public static List<Integer> calculatePointsDifferenc(List<Integer> oldPoints, List<Integer> points) {
		// calculate difference between the old and new points and return the difference array
		List <Integer> difference = new LinkedList<Integer>();
		for (int i = 0; i < points.size(); i++) {
			difference.add(points.get(i) - oldPoints.get(i));
		}
		return difference;
	}
	

}
