package mk.ukim.finki.NP.ZadaciZaVezbanje.MojDDV;

public class AmountNotAllowedException extends Exception {

    public AmountNotAllowedException(int sum) {
        super("Receipt with amount " + sum + " is not allowed to be scanned");
    }
}
