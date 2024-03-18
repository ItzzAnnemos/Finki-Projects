package mk.ukim.finki.NP.ZadaciZaVezbanje2.OnlineShop;

import java.time.LocalDateTime;

public class Product {
    private String id;
    private String name;
    private LocalDateTime createdAt;
    private double price;
    private int quantitySold;

    public Product(String id, String name, LocalDateTime createdAt, double price) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
        this.price = price;
        this.quantitySold = 0;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public double increaseQuantity(int x) {
        this.quantitySold += x;

        return x * price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdAt=" + createdAt +
                ", price=" + price +
                ", quantitySold=" + quantitySold +
                '}';
    }
}
