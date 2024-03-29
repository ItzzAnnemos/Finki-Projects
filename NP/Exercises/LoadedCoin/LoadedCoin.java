package mk.ukim.finki.NP.ZadaciZaVezbanje.LoadedCoin;

public class LoadedCoin extends Coin {
    private int probability;

    public LoadedCoin(int probability) {
        this.probability = probability;
    }

    @Override
    public SIDE flip() {
        double P = (double) probability / 100;
        if (Math.random() < P) {
            return SIDE.HEAD;
        } else {
            return SIDE.TAIL;
        }
    }
}
