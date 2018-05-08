package shopping_cart.inporter;

import shopping_cart.product.Product;

import java.io.IOException;
import java.util.List;

public interface ProductsSource {
    List<Product> getProducts() throws IOException;
}
