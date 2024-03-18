package mk.ukim.finki.NP.ZadaciZaVezbanje2.FootballTable;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private List<Game> games;

    public Team(String name) {
        this.name = name;
        this.games = new ArrayList<>();
    }

    public void addGame(Game game) {
        games.add(game);
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return (int) games.stream()
                .filter(game -> game.getAwayTeam().equals(name) && game.getWinner() == 2 ||
                game.getHomeTeam().equals(name) && game.getWinner() == 1)
                .count();
    }

    public int getDraws() {
        return (int) games.stream()
                .filter(game -> game.getWinner() == 0)
                .count();
    }

    public int getLoses() {
        return (int) games.stream()
                .filter(game -> game.getAwayTeam().equals(name) && game.getWinner() == 1 ||
                        game.getHomeTeam().equals(name) && game.getWinner() == 2)
                .count();
    }

    public int getPoints() {
        return getWins() * 3 + getDraws();
    }

    public int getGoalDifference() {
        return games.stream()
                .mapToInt(game -> game.getGoalDifference(name))
                .sum();
    }

    @Override
    public String toString() {
        return String.format("%-15s%5d%5d%5d%5d%5d",
                name, games.size(), getWins(), getDraws(), getLoses(), getPoints());
    }
}
