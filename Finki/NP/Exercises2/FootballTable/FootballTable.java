package mk.ukim.finki.NP.ZadaciZaVezbanje2.FootballTable;

import java.util.*;

public class FootballTable {
    private List<Game> games;
    private Map<String, Team> teams;

    public FootballTable() {
        this.games = new ArrayList<>();
        this.teams = new HashMap<>();
    }

    public void addGame(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        games.add(new Game(homeTeam, awayTeam, homeGoals, awayGoals));
        teams.putIfAbsent(homeTeam, new Team(homeTeam));
        teams.putIfAbsent(awayTeam, new Team(awayTeam));
        teams.get(homeTeam).addGame(games.get(games.size() - 1));
        teams.get(awayTeam).addGame(games.get(games.size() - 1));
    }

    public void printTable() {
        Comparator<Team> comparator = Comparator.comparing(Team::getPoints)
                .thenComparing(Team::getGoalDifference).reversed().thenComparing(Team::getName);

        List<Team> list = new ArrayList<>(teams.values());
        list.sort(comparator);

        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%02d. %s%n", i + 1, list.get(i).toString());
        }
    }
}
