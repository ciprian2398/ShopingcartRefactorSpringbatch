package shopping_cart.product.calculator.offers.person.validator;

import shopping_cart.product.calculator.offers.person.Person;

public class SimplePersonValidator implements PersonValidator {
    @Override
    public boolean isValid(Person person) {
        return (isNameValid(person.getName()) &&
                isAgeValid(person.getAge()));
    }

    private boolean isNameValid(String name) {
        if (name != null)
            return name.length() > 2;
        return false;
    }

    private boolean isAgeValid(int age) {
        return (age >= 0 && age < 200);
    }
}