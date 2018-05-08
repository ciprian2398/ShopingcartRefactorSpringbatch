package shopping_cart.product.caster;

import shopping_cart.product.Product;

public class SimpleProductParser implements ProductParser {

    @Override
    public Product castToProduct(String productName, String totalPriece, String quantity) {
        double totalPriece1 = Double.parseDouble(totalPriece);
        int quantity1 = Integer.parseInt(quantity);

        return new Product(productName, totalPriece1, quantity1);
    }
}
