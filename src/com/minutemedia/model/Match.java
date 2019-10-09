package com.minutemedia.model;

public abstract class Match {

	protected Team home_team;
	protected Team away_team;
	protected Tournament tournament;
	protected String start_time;
	protected Status status;

	public Match(Team home_team, Team away_team, Tournament tournament, String start_time, Status status) {
		super();
		this.home_team = home_team;
		this.away_team = away_team;
		this.tournament = tournament;
		this.start_time = start_time;
		this.status = status;
	}

	public Team getHome_team() {
		return home_team;
	}

	public void setHome_team(Team home_team) {
		this.home_team = home_team;
	}

	public Team getAway_team() {
		return away_team;
	}

	public void setAway_team(Team away_team) {
		this.away_team = away_team;
	}

	public Tournament getTournament() {
		return tournament;
	}

	public void setTournament(Tournament tournament) {
		this.tournament = tournament;
	}

	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
