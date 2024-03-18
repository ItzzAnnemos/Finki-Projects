package mk.ukim.finki.NP.ZadaciZaVezbanje.Equation;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class Equation<T, U> implements Supplier<T>, Function<T, U> {
    private Supplier<T> supplier;
    private Function<T, U> function;

    public Equation(Supplier<T> supplier, Function<T, U> function) {
        this.supplier = supplier;
        this.function = function;
    }

    public Optional<U> calculate() {
        return Optional.of(function.apply(supplier.get()));
    }

    @Override
    public U apply(T t) {
        return null;
    }

    @Override
    public T get() {
        return supplier.get();
    }
}
