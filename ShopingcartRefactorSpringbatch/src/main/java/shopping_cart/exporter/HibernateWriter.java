package shopping_cart.exporter;

import org.hibernate.Session;
import shopping_cart.product.Product;

import java.util.List;

class HibernateWriter {
    private Session session;

    public HibernateWriter() {
        init();
    }

    private void init() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public void write(List<Product> products) {
        session.beginTransaction();
        products.forEach(product -> session.save(product));
        session.getTransaction().commit();
    }

    public void close() {
        HibernateUtil.shutdown();
    }

}
