package shopping_cart.exporter;

import shopping_cart.product.Product;

import java.util.List;

public interface ProductExporter {
    void write(List<Product> products);
}
