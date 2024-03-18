package mk.ukim.finki.NP.ZadaciZaVezbanje.Equation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquationProcessor {
    public EquationProcessor() {

    }

    public static <T, U> void process(List<T> inputs, List<Equation<T, U>> equations) {
        List<U> list = new ArrayList<>();
        for (T input : inputs) {
            System.out.println("Input: " + input);
            for (Equation<T, U> equation : equations) {
                Optional<U> tmp = equation.calculate();
                if (tmp.isPresent()) {
                    list.add(tmp.get());
                } else {
                    System.out.println("Can not evaluate");
                }
            }
        }
        System.out.println("Result: " + list.get(0));
        System.out.println("Result: " + list.get(1));
    }
}
