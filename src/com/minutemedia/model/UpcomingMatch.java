package com.minutemedia.model;

import java.time.LocalTime;

public class UpcomingMatch extends Match {

	private LocalTime kickoff;

	public UpcomingMatch(Team home_team, Team away_team, Tournament tournament, String start_time, LocalTime kickoff,
			Status status) {
		super(home_team, away_team, tournament, start_time, status);
		this.kickoff = kickoff;
	}

	public LocalTime getKickoff() {
		return kickoff;
	}

	public void setKickoff(LocalTime kickoff) {
		this.kickoff = kickoff;
	}
}
