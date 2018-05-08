package shopping_cart;

import shopping_cart.product.Product;
import shopping_cart.product.calculator.BestPriceCalculator;
import shopping_cart.product.calculator.offers.Offer;
import shopping_cart.product.validator.ProductValidator;
import shopping_cart.inporter.ProductsSource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartManager {
    private List<Product> products = new ArrayList<>();
    private List<Offer> offers = new ArrayList<>();

    private ProductValidator productValidator;
    private BestPriceCalculator bestPriceCalculator;

    public ShoppingCartManager(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }

    public void setBestPriceCalculator(BestPriceCalculator bestPriceCalculator) {
        this.bestPriceCalculator = bestPriceCalculator;
    }

    public int getProductCount() {
        return products.size();
    }

    public double getTotalCartValue() {
        return products.stream().mapToDouble(Product::getTotalPriece).sum();
    }

    public double getTotalQuantity() {
        return products.stream().mapToDouble(Product::getQuantity).sum();
    }

    private double getBestPriceForProduct(Product product) {
        return bestPriceCalculator.getBestPrice(offers, product);
    }

    public void getProductsFromSource(ProductsSource productsSource) throws IOException {
        if (productsSource != null)
            productsSource.getProducts().forEach(this::addProduct);
    }

    public void addOffer(Offer offer) {
        offers.add(offer);
    }

    public void addProduct(Product product) {
        if (isValidProduct(product))
            products.add(adaptProduct(product));
    }

    private Product adaptProduct(Product product) {
        if (bestPriceCalculator != null)
            product.setTotalPriece(getBestPriceForProduct(product));
        return product;
    }

    private boolean isValidProduct(Product product) {
        return productValidator.isValid(product);
    }

    public List<Product> getProducts() {
        return products;
    }
}
