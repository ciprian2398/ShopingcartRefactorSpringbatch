package shopping_cart.product.calculator.offers;

import shopping_cart.product.Product;
import shopping_cart.product.calculator.offers.person.Person;
import shopping_cart.product.calculator.offers.person.validator.PersonValidator;
import shopping_cart.product.calculator.offers.person.validator.SimplePersonValidator;

public class ByAgeOffer implements Offer {
    private final Person person;
    private final PersonValidator personValidator;

    public ByAgeOffer(Person person) {
        this.person = person;
        personValidator = new SimplePersonValidator();
    }

    @Override
    public void applyOffer(Product product) {
        if (personValidator.isValid(person)) {
            product.setTotalPriece(bestPrice(product.getTotalPriece()));
        }
    }

    private double bestPrice(double price) {
        return  ((100 - (youngDiscount() + oldDiscount())) / 100) * price;
    }

    private double youngDiscount() {
        double youngDiscount;

        if (person.getAge() <= 16)
            youngDiscount = 15;
        else
            youngDiscount = 0;

        return youngDiscount;
    }

    private double oldDiscount() {
        double oldDiscount;

        if (person.getAge() >= 60)
            oldDiscount = 20;
        else
            oldDiscount = 0;

        return oldDiscount;
    }

}
