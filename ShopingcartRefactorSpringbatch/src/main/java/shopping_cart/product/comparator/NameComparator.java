package shopping_cart.product.comparator;

import shopping_cart.product.Product;

import java.util.Comparator;

public class NameComparator implements Comparator<Product>{
    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getProductName() == null) return -1;
        if (o2.getProductName() == null) return 1;
        return o1.getProductName().compareToIgnoreCase(o2.getProductName());
    }
}

