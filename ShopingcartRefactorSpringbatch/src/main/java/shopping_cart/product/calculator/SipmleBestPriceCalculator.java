package shopping_cart.product.calculator;

import shopping_cart.product.Product;
import shopping_cart.product.calculator.offers.Offer;

import java.util.List;

public class SipmleBestPriceCalculator implements BestPriceCalculator {
    private List<Offer> offers;
    private Product product;

    @Override
    public double getBestPrice(List<Offer> offers, Product product) {
        double originalPrice = product.getTotalPriece();
        double minPrice = originalPrice;
        for (Offer offer : offers) {
            product.setTotalPriece(originalPrice);
            offer.applyOffer(product);
            minPrice = Math.min(product.getTotalPriece(), minPrice);
        }
        return minPrice;
    }

}
