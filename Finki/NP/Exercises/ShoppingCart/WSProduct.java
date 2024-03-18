package mk.ukim.finki.NP.ZadaciZaVezbanje.ShoppingCart;

public class WSProduct extends Product {
    private int quantity;

    public WSProduct(String type, String id, String name, double price, int quantity) {
        super(type, id, name, price);
        this.quantity = quantity;
    }

    public double getFullPrice() {
        return getPrice() * quantity;
    }

    public double getDiscountedFullPrice() {
        return getDiscountedPrice() * quantity;
    }

    @Override
    public String toString() {
        return String.format("%s - %.2f", getId(), getFullPrice());
    }

    public String toStringR() {
        return String.format("%s - %.2f", getId(), getFullPrice() - getDiscountedFullPrice());
    }
}
