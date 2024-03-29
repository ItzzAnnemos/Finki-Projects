package mk.ukim.finki.NP.ZadaciZaVezbanje.MojDDV;

public class Item {
    private int price;
    private char taxType;

    public Item(int price, char taxType) {
        this.price = price;
        this.taxType = taxType;
    }

    public int getPrice() {
        return price;
    }

    public char getTaxType() {
        return taxType;
    }

    public double getTaxReturn() {
        double tmp = price;
        if (taxType == 'A') {
            tmp = tmp * 0.18;
        } else if (taxType == 'B') {
            tmp = tmp * 0.05;
        } else {
            tmp = tmp * 0;
        }

        return tmp * 0.15;
    }
}
