package mk.ukim.finki.NP.ZadaciZaVezbanje.LoadedCoin;

import java.util.Random;

public class Coin {
    SIDE side;

    public SIDE flip() {
        Random random = new Random();
        boolean isHead = random.nextBoolean();
        if (isHead) {
            return SIDE.HEAD;
        } else {
            return SIDE.TAIL;
        }
    }
}
