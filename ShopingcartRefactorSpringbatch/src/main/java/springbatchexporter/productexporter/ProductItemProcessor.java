package springbatchexporter.productexporter;

import org.springframework.batch.item.ItemProcessor;
import shopping_cart.product.Product;
import shopping_cart.product.validator.ProductValidator;
import shopping_cart.product.validator.SipmleProductValidator;

public class ProductItemProcessor implements ItemProcessor<Product, Product> {
    private ProductValidator productValidator;

    public void setProductValidator(ProductValidator productValidator) {
        this.productValidator = productValidator;
    }

    @Override
    public Product process(Product result) throws Exception {
        System.out.println("Processing result :" + result);
        if (productValidator.isValid(result)) {
            return result;
        }
        return null;
    }
}
