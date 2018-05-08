package shopping_cart.product.comparator;

import shopping_cart.product.Product;

import java.util.Comparator;

public class QuantityComparator implements Comparator<Product> {
    @Override
    public int compare(Product o1, Product o2) {
        return (int) (o1.getQuantity() - o2.getQuantity());
    }
}
