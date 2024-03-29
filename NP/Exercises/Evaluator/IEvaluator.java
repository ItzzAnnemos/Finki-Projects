package mk.ukim.finki.NP.ZadaciZaVezbanje.Evaluator;

public interface IEvaluator <T extends Comparable<T>> {
    public boolean evaluate (T a, T b);
}
