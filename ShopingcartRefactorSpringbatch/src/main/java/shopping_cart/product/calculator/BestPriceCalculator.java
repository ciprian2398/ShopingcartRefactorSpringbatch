package shopping_cart.product.calculator;

import shopping_cart.product.Product;
import shopping_cart.product.calculator.offers.Offer;

import java.util.List;

public interface BestPriceCalculator {
    double getBestPrice(List<Offer> offers, Product product);
}
