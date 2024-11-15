package org.example.bll.validators;

import org.example.model.Product;

public class ProductQuantityValidator implements Validator<Product> {
    @Override
    public int validate(Product product) {
        if(product.getQuantity() <= 0){
            throw new IllegalArgumentException("Invalid quantity for this product!");
        }
        return 0;
    }
}
