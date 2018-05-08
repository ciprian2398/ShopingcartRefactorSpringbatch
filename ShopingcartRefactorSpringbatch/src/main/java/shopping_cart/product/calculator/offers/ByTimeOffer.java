package shopping_cart.product.calculator.offers;

import shopping_cart.product.Product;

public class ByTimeOffer implements Offer {

    private final int hourOfDay;

    public ByTimeOffer(int hourOfDay) {
        this.hourOfDay = hourOfDay;
    }

    @Override
    public void applyOffer(Product product) {
        product.setTotalPriece((100 - getDiscount()) / 100 * product.getTotalPriece());
    }

    private double getDiscount() {
        double discount;

        if (hourOfDay >= 18)
            discount = 20;
        else
            discount = 0;

        return discount;
    }
}
