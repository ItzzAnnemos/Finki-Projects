package mk.ukim.finki.NP.ZadaciZaVezbanje.ShoppingCart;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ShoppingCart {
    private List<Product> products;

    public ShoppingCart() {
        this.products = new ArrayList<>();
    }

    public void addItem(String itemData) throws InvalidOperationException {
        String [] parts = itemData.split(";");
        if (Double.parseDouble(parts[4]) == 0) {
            throw new InvalidOperationException("The quantity of the product with id " + parts[1] + " can not be 0.");
        }
        if (parts[0].equals("WS")) {
            products.add(new WSProduct(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Integer.parseInt(parts[4])));
        } else {
            products.add(new PSProduct(parts[0], parts[1], parts[2], Integer.parseInt(parts[3]), Double.parseDouble(parts[4])));
        }
    }

    public void printShoppingCart(OutputStream os) {
        PrintWriter pw = new PrintWriter(os);

        products.sort(Comparator.comparingDouble(Product::getFullPrice).reversed());

        for (Product product : products) {
            pw.println(product.toString());
        }

        pw.flush();
    }

    public void blackFridayOffer(List<Integer> discountItems, OutputStream os) throws InvalidOperationException {
        if (discountItems.isEmpty()) {
            throw new InvalidOperationException("There are no products with discount.");
        } else {
            List<Product> list = new ArrayList<>();
            for (int i = 0;i < products.size();i++) {
                for (int j = 0;j < discountItems.size();j++) {
                    if (Integer.parseInt(products.get(i).getId()) == discountItems.get(j)) {
                        products.get(i).setPrice(products.get(i).getPrice() * 0.9);
                        list.add(products.get(i));
                    }
                }
            }

            PrintWriter pw = new PrintWriter(os);

            for (Product product : list) {
                pw.println(product.toStringR());
            }

            pw.flush();
        }
    }
}
