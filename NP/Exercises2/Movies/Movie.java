package mk.ukim.finki.NP.ZadaciZaVezbanje2.Movies;

import java.util.List;

public class Movie {
    private String name;
    private List<Integer> ratings;

    public Movie(String name, List<Integer> ratings) {
        this.name = name;
        this.ratings = ratings;
    }

    public String getName() {
        return name;
    }

    public List<Integer> getRatings() {
        return ratings;
    }
     public double getAverageRating() {
        return ratings.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
     }

     public double getRatingCoef() {
        return getAverageRating() * ratings.size() / MoviesList.getMaxNumRatings();
     }

    @Override
    public String toString() {
        return String.format("%s (%.2f) of %d ratings", name, getAverageRating(), ratings.size());
    }
}
