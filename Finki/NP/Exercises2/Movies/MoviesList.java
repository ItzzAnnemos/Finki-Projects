package mk.ukim.finki.NP.ZadaciZaVezbanje2.Movies;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MoviesList {
    private List<Movie> movies;
    private static int maxNumRatings = 0;

    public MoviesList() {
        this.movies = new ArrayList<>();
    }

    public static int getMaxNumRatings() {
        return maxNumRatings;
    }

    public void addMovie(String title, int[] ratings) {
        List<Integer> list = new ArrayList<>();
        for (int rating : ratings) {
            list.add(rating);
        }
        movies.add(new Movie(title, list));
        if (maxNumRatings < list.size())
            maxNumRatings = list.size();
    }

    public List<Movie> top10ByAvgRating() {
        Comparator<Movie> comparator = Comparator.comparing(Movie::getAverageRating).reversed()
                .thenComparing(Movie::getName);

        return movies.stream()
                .sorted(comparator)
                .limit(10)
                .collect(Collectors.toList());
    }

    public List<Movie> top10ByRatingCoef() {
        Comparator<Movie> comparator = Comparator.comparing(Movie::getRatingCoef).reversed()
                .thenComparing(Movie::getName);

        return movies.stream()
                .sorted(comparator)
                .limit(10)
                .collect(Collectors.toList());
    }
}
