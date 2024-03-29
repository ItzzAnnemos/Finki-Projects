package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem2;

public class BonusNotAllowedException extends Exception {
    public BonusNotAllowedException(String bonus) {
        super("Bonus of " + bonus + (!bonus.endsWith("%") ? "$" : "") + " is not allowed");
    }
}
