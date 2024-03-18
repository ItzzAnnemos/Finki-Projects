package mk.ukim.finki.NP.ZadaciZaVezbanje2.Discounts;

public class Item {
    private int priceWithDiscount;
    private int price;

    public Item(int priceWithDiscount, int price) {
        this.priceWithDiscount = priceWithDiscount;
        this.price = price;
    }

    public int getDiscount() {
        return (int) (((double) (price - priceWithDiscount) / price) * 100);
    }

    public int absoluteDiscount() {
        return price - priceWithDiscount;
    }

    @Override
    public String toString() {
        return String.format("%2d%% %d/%d", (int)getDiscount(), priceWithDiscount, price);
    }
}
