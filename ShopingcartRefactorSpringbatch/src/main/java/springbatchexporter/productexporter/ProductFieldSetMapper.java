package springbatchexporter.productexporter;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import shopping_cart.product.Product;

public class ProductFieldSetMapper implements FieldSetMapper<Product> {

    public Product mapFieldSet(FieldSet fieldSet){
        Product result = new Product();
        result.setProductName(fieldSet.readString(0));
        result.setTotalPriece(fieldSet.readDouble(1));
        result.setQuantity(fieldSet.readInt(2));
        return result;
    }

}
