package mk.ukim.finki.NP.ZadaciZaVezbanje.ShoppingCart;

public class PSProduct extends Product {
    private double quantity;

    public PSProduct(String type, String id, String name, double price, double quantity) {
        super(type, id, name, price);
        this.quantity = quantity;
    }

    public double getFullPrice() {
        return getPrice() * (quantity / 1000.0);
    }

    public double getDiscountedFullPrice() {
        return getDiscountedPrice() * (quantity / 1000.0);
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", getId(), getFullPrice());
    }

    public String toStringR() {
        return String.format("%s - %.2f", getId(), getFullPrice() - getDiscountedFullPrice());
    }
}
