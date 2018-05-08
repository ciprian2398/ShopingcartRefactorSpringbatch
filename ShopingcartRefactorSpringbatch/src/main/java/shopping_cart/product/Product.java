package shopping_cart.product;

import javax.persistence.*;

@Entity
@Table(name = "PRODUCTS_LIST")
public class Product {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_Sequence")
    @SequenceGenerator(name = "id_Sequence", sequenceName = "DEMO_CUST_SEQ")
    private long id;

    @Column(name = "PRODUCTNAME", nullable = false)
    private String productName;

    @Column(name = "TOTALPRICE", nullable = false)
    private double totalPriece;

    @Column(name = "QUANTITY", nullable = false)
    private int quantity;

    public Product() {
    }

    public Product(String productName, double totalPriece, int quantity) {
        this.productName = productName;
        this.totalPriece = totalPriece;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getTotalPriece() {
        return totalPriece;
    }

    public void setTotalPriece(double totalPriece) {
        this.totalPriece = totalPriece;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", totalPriece=" + totalPriece +
                ", quantity=" + quantity +
                '}';
    }
}
