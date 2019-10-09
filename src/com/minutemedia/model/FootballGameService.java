package com.minutemedia.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.google.gson.Gson;

@Path("/")
public class FootballGameService {

	private static String RESULT_FINISHED_FILE = "result_played.csv";
	private static String RESULT_UPCOMING_FILE = "result_upcoming.csv";

	private List<String[]> upcomingMatches = CSVReader.read(RESULT_UPCOMING_FILE);
	private List<String[]> finishedMatches = CSVReader.read(RESULT_FINISHED_FILE);
	private List<Match> listOfUpcomingMatches = new ArrayList<>();
	private List<Match> listOfFinishedMatches = new ArrayList<>();;

	public FootballGameService() {
		loadCSVFiles();
	}

	private void loadCSVFiles() {
		IDSetter teamIdSetter = new IDSetter();
		IDSetter tournamentIdSetter = new IDSetter();
		
		for (String[] match : upcomingMatches) {
			Team home_team = new Team(match[0], teamIdSetter.setAnId(match[0]));
			Team away_team = new Team(match[1], teamIdSetter.setAnId(match[1]));
			Tournament tournament = new Tournament(match[2], tournamentIdSetter.setAnId(match[2]));
			String start_time = match[3];
			DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:mm");
			LocalTime kickoff = LocalTime.parse(match[4], timeFormatter);
			
			listOfUpcomingMatches
					.add(new UpcomingMatch(home_team, away_team, tournament, start_time, kickoff, Status.upcoming));
		}

		for (String[] match : finishedMatches) {
			Team home_team = new Team(match[0], teamIdSetter.setAnId(match[0]));
			int home_score = Integer.parseInt(match[1]);
			Team away_team = new Team(match[2], teamIdSetter.setAnId(match[2]));
			int away_score = Integer.parseInt(match[3]);
			Tournament tournament = new Tournament(match[4], tournamentIdSetter.setAnId(match[4]));
			String start_time = match[3];
			
			listOfFinishedMatches.add(new FinishedMatch(home_team, home_score, away_team, away_score, tournament,
					start_time, Status.played));
		}
	}

	@GET
	@Path("/matches-by-team")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfMatchesByTeam(@QueryParam("name") String name) {
		List<Match> upcomingMatches = getListOfMatchesByTeamName(listOfUpcomingMatches, name);
		List<Match> finishedMatches = getListOfMatchesByTeamName(listOfFinishedMatches, name);

		String gsonUpcomingMatches = upcomingMatches.isEmpty()? "" : new Gson().toJson(upcomingMatches);
		String gsonFinishedMatches = finishedMatches.isEmpty()? "" : new Gson().toJson(finishedMatches);
		String result = gsonUpcomingMatches + gsonFinishedMatches;

		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/matches-by-team-status")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfMatchesByTeamAndStatus(@QueryParam("name") String name,
			@QueryParam("status") String status) {

		List<Match> matchesByName = null;
		List<Match> matchesByStatusAndName = new ArrayList<>();

		if (status.equals(Status.upcoming.toString())) {
			matchesByName = getListOfMatchesByTeamName(listOfUpcomingMatches, name);
		} else {
			matchesByName = getListOfMatchesByTeamName(listOfFinishedMatches, name);
		}

		for (Match match : matchesByName) {
			if (status.equals(match.getStatus().toString())) {
				matchesByStatusAndName.add(match);
			}
		}

		String result = new Gson().toJson(matchesByStatusAndName);

		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/matches-by-tournament")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfMatchesByTournament(@QueryParam("name") String name) {
		List<Match> upcomingMatches = getListOfMatchesByTournamentName(listOfUpcomingMatches, name);
		List<Match> finishedMatches = getListOfMatchesByTournamentName(listOfFinishedMatches, name);

		String gsonUpcomingMatches = upcomingMatches.isEmpty()? "" : new Gson().toJson(upcomingMatches);
		String gsonFinishedMatches = finishedMatches.isEmpty()? "" : new Gson().toJson(finishedMatches);
		String result = gsonUpcomingMatches + gsonFinishedMatches;

		return Response.status(200).entity(result).build();
	}

	@GET
	@Path("/matches-by-tournament-status")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getListOfMatchesByTournamentAndStatus(@QueryParam("name") String name,
			@QueryParam("status") String status) {
		List<Match> matchesByName = null;
		List<Match> matchesByStatusAndName = new ArrayList<>();

		if (status.equals(Status.upcoming.toString())) {
			matchesByName = getListOfMatchesByTournamentName(listOfUpcomingMatches, name);
		} else if (status.equals(Status.played.toString())) {
			matchesByName = getListOfMatchesByTournamentName(listOfFinishedMatches, name);
		}

		for (Match match : matchesByName) {
			if (status.equals(match.getStatus().toString())) {
				matchesByStatusAndName.add(match);
			}
		}

		String result = new Gson().toJson(matchesByStatusAndName);

		return Response.status(200).entity(result).build();
	}

	private List<Match> getListOfMatchesByTeamName(List<Match> listOfMatches, String name) {

		List<Match> newListOfmatches = new ArrayList<>();

		for (Match match : listOfMatches) {
			if (match.getHome_team().getName().equals(name) || match.getAway_team().getName().equals(name)) {
				newListOfmatches.add(match);
			}
		}

		return newListOfmatches;
	}

	private List<Match> getListOfMatchesByTournamentName(List<Match> listOfMatches, String name) {

		List<Match> newListOfmatches = new ArrayList<>();

		for (Match match : listOfUpcomingMatches) {
			if (match.tournament.getName().equals(name)) {
				newListOfmatches.add(match);
			}
		}

		return newListOfmatches;
	}
}
