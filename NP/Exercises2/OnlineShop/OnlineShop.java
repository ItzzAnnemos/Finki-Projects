package mk.ukim.finki.NP.ZadaciZaVezbanje2.OnlineShop;

import java.time.LocalDateTime;
import java.util.*;

public class OnlineShop {
    private List<Product> products;
    private Map<String, List<Product>> mapByCategory;
    private Map<String, Product> mapById;

    public OnlineShop() {
        this.products = new ArrayList<>();
        this.mapByCategory = new HashMap<>();
        this.mapById = new HashMap<>();
    }

    public void addProduct(String category, String id, String name, LocalDateTime createdAt, double price) {
        mapByCategory.putIfAbsent(category, new ArrayList<>());
        mapById.putIfAbsent(id, new Product(id, name, createdAt, price));
        mapByCategory.get(category).add(mapById.get(id));
        products.add(mapById.get(id));
    }

    public double buyProduct(String id, int quantity) throws ProductNotFoundException {
        if (mapById.containsKey(id))
            return mapById.get(id).increaseQuantity(quantity);
        else
            throw new ProductNotFoundException("Product with id " + id + " does not exist in the online shop!");
    }

    public List<List<Product>> listProducts(String category, COMPARATOR_TYPE comparatorType, int pageSize) {
        List<List<Product>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        Comparator<Product> comparator = null;

        switch (comparatorType) {
            case NEWEST_FIRST:
                comparator = Comparator.comparing(Product::getCreatedAt).reversed();
                break;
            case OLDEST_FIRST:
                comparator = Comparator.comparing(Product::getCreatedAt);
                break;
            case MOST_SOLD_FIRST:
                comparator = Comparator.comparing(Product::getQuantitySold).reversed().
                        thenComparingInt(products::indexOf);
                break;
            case LEAST_SOLD_FIRST:
                comparator = Comparator.comparing(Product::getQuantitySold).thenComparing(Product::getCreatedAt);
                break;
            case LOWEST_PRICE_FIRST:
                comparator = Comparator.comparing(Product::getPrice);
                break;
            case HIGHEST_PRICE_FIRST:
                comparator = Comparator.comparing(Product::getPrice).reversed();
                break;
        }

        if (category == null) {
            products.sort(comparator);
            int i = 0;
            while (!products.isEmpty()) {
                for (int j = 0;j < pageSize && !products.isEmpty();j++) {
                    result.get(i).add(products.remove(0));
                }
                if (products.isEmpty()) {
                    break;
                }
                result.add(new ArrayList<>());
                i++;
            }
        } else {
            List<Product> tmp = mapByCategory.get(category);
            tmp.sort(comparator);
            int i = 0;
            while (!tmp.isEmpty()) {
                for (int j = 0; j < pageSize && !tmp.isEmpty(); j++) {
                    result.get(i).add(tmp.remove(0));
                }
                if (tmp.isEmpty()) {
                    break;
                }
                result.add(new ArrayList<>());
                i++;
            }
        }

        return result;
    }
}
