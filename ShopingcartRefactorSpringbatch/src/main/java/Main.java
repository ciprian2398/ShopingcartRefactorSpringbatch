import shopping_cart.ShoppingCartManager;
import shopping_cart.exporter.DBProductExporter;
import shopping_cart.exporter.ProductExporter;
import shopping_cart.inporter.CSVProductsSource;
import shopping_cart.inporter.ProductsSource;
import shopping_cart.product.Product;
import shopping_cart.product.calculator.BestPriceCalculator;
import shopping_cart.product.calculator.SipmleBestPriceCalculator;
import shopping_cart.product.calculator.offers.ByTimeOffer;
import shopping_cart.product.calculator.offers.Offer;
import shopping_cart.product.comparator.NameComparator;
import shopping_cart.product.validator.ProductValidator;
import shopping_cart.product.validator.SipmleProductValidator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ProductValidator productValidator = new SipmleProductValidator();

        ShoppingCartManager shoppingCart = new ShoppingCartManager(productValidator);
        Offer offer = new ByTimeOffer(20);
        shoppingCart.addOffer(offer);

        BestPriceCalculator bestPriceCalculator = new SipmleBestPriceCalculator();
        shoppingCart.setBestPriceCalculator(bestPriceCalculator);

        shoppingCart.addProduct(new Product("Inca ceva", 100.23, 2));
        shoppingCart.addProduct(new Product("Inca ceva invalid", 100.23, -1));

        ProductsSource productsSource = new CSVProductsSource();
        shoppingCart.getProductsFromSource(productsSource);

        ProductExporter productExporter = new DBProductExporter();
        productExporter.write(shoppingCart.getProducts());

        List<Product> products = new ArrayList<>(shoppingCart.getProducts());

        Collections.sort(products, new NameComparator());
        products.forEach(System.out::println);

    }
}
