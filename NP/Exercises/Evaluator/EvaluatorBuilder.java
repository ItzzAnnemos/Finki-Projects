package mk.ukim.finki.NP.ZadaciZaVezbanje.Evaluator;

public class EvaluatorBuilder <T extends Comparable<T>> {
    public static <T extends Comparable<T>> IEvaluator<T> build (String operator) {
        IEvaluator<T> rez = null;

        switch (operator) {
            case ">":
                rez = (a, b) -> a.compareTo(b) > 0;
                return rez;
            case "==":
                rez = (a, b) -> a.compareTo(b) == 0;
                return rez;
            case "!=":
                rez = (a, b) -> a.compareTo(b) != 0;
                return rez;
            case "<":
                rez = (a, b) -> a.compareTo(b) < 0;
                return rez;
            default:
                return rez;
        }
    }
}
