package mk.ukim.finki.NP.ZadaciZaVezbanje.Rule;

import java.util.List;
import java.util.Optional;

public class RuleProcessor {

    public static <T, U> void process(List<T> inputData, List<Rule<T, U>> rules) {
        for (T input : inputData) {
            System.out.println("Input: " + input);
            for (Rule<T, U> rule : rules) {
                Optional<U> result = rule.applyy(input);
                if (result.isPresent()) {
                    System.out.println("Result: " + result.get());
                } else {
                    System.out.println("Condition not met");
                }
            }
        }
    }

}
