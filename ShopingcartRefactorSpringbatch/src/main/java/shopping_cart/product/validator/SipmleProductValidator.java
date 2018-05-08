package shopping_cart.product.validator;

import shopping_cart.product.Product;

public class SipmleProductValidator implements ProductValidator {
    @Override
    public boolean isValid(Product product) {
        return (isNameValid(product.getProductName()) &&
                isPriceValid(product.getTotalPriece()) &&
                isQuantityValid(product.getQuantity()));
    }

    private boolean isNameValid(String name) {
        if (name != null)
            return name.length() > 2;
        return false;
    }

    private boolean isPriceValid(double price) {
        return price > 0;
    }

    private boolean isQuantityValid(double quantity) {
        return quantity > 0;
    }
}
