package com.minutemedia.model;

import java.util.List;

public class ListOfMatches {
	
	private List<Match> matches;

	public ListOfMatches(List<Match> matches) {
		super();
		this.matches = matches;
	}

	public List<Match> getMatches() {
		return matches;
	}

	public void setMatches(List<Match> matches) {
		this.matches = matches;
	}
}
