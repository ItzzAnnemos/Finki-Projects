package mk.ukim.finki.NP.ZadaciZaVezbanje.Rule;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Rule<T, U> implements Predicate<T>, Function<T, U> {
    private Predicate<T> predicate;
    private Function<T, U> function;

    public Rule(Predicate<T> predicate, Function<T, U> function) {
        this.predicate = predicate;
        this.function = function;
    }

    @Override
    public boolean test(T t) {
        return false;
    }

    @Override
    public U apply(T input) {
        if (test(input)) {
            return function.apply(input);
        } else {
            return null;
        }
    }

    public Optional<U> applyy(T input) {
        if (predicate.test(input)) {
            return Optional.of(function.apply(input));
        } else {
            return Optional.empty();
        }
    }
}
