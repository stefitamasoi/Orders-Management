package org.example.bll.validators;
import org.example.model.Product;
public class PriceValidator implements Validator<Product>{
    @Override
    public int validate(Product product) {
        if(product.getPrice() <= 0){
            throw new IllegalArgumentException("Invalid price!");
        }
        return 0;
    }
}
