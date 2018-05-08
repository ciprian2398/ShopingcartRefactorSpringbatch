package shopping_cart.inporter;

import shopping_cart.product.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVProductsSource implements ProductsSource {
    public static final String FILE = "C:\\Users\\crm0100\\Desktop\\weather-data.csv";
    private CSVSource csvSource;
    private List<Product> products;

    public CSVProductsSource() {
        init();
    }

    private void init() {
        csvSource = new CSVSource(FILE);
        products = new ArrayList<>();
    }

    @Override
    public List<Product> getProducts() throws IOException {
        Product product;
        while ((product = csvSource.nextProduct()) != null) {
            products.add(product);
        }
        return products;
    }
}
