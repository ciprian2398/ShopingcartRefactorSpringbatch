package shopping_cart.exporter;

import shopping_cart.product.Product;

import java.util.List;

public class DBProductExporter implements ProductExporter {
    private HibernateWriter hibernateInsert;
    private List<Product> products;

    @Override
    public void write(List<Product> products) {
        this.products = products;
        init();
        insert();
        close();
    }

    private void init() {
        hibernateInsert = new HibernateWriter();
    }

    private void insert() {
        products.forEach(product -> hibernateInsert.write(products));
    }

    private void close() {
        hibernateInsert.close();
    }

}
