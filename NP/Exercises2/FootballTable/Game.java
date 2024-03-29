package mk.ukim.finki.NP.ZadaciZaVezbanje2.FootballTable;

public class Game {
    private String homeTeam;
    private String awayTeam;
    private int homeGoals;
    private int awayGoals;

    public Game(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeGoals = homeGoals;
        this.awayGoals = awayGoals;
    }

    public int getWinner() {
        if (homeGoals > awayGoals) {
            return 1;
        } else if (awayGoals > homeGoals) {
            return 2;
        } else {
            return 0;
        }
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getGoalDifference(String name) {
        if (homeTeam.equals(name)) {
            return homeGoals - awayGoals;
        } else {
            return awayGoals - homeGoals;
        }
    }
}
