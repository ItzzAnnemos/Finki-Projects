package mk.ukim.finki.NP.ZadaciZaVezbanje.ShoppingCart;

public abstract class Product {
    private String type;
    private String id;
    private String name;
    private double price;
    private double discountedPrice;

    public Product(String type, String id, String name, double price) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setPrice(double price) {
        this.discountedPrice = price;
    }

    public abstract double getFullPrice();

    public abstract String toStringR();
}
