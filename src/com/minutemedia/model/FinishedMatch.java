package com.minutemedia.model;

public class FinishedMatch extends Match {

	private int home_score;
	private int away_score;

	public FinishedMatch(Team home_team, int home_score, Team away_team, int away_score, Tournament tournament,
			String start_time, Status status) {
		super(home_team, away_team, tournament, start_time, status);
		this.home_score = home_score;
		this.away_score = away_score;
	}

	public int getHome_score() {
		return home_score;
	}

	public void setHome_score(byte home_score) {
		this.home_score = home_score;
	}

	public int getAway_score() {
		return away_score;
	}

	public void setAway_score(byte away_score) {
		this.away_score = away_score;
	}
}
