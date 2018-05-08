package shopping_cart.product.caster;

import shopping_cart.product.Product;

public interface ProductParser {
    Product castToProduct(String productName, String totalPriece, String quantity);
}
