package org.example.bll.validators;
import org.example.model.Product;

import java.util.regex.Pattern;

public class ProductNameValidator implements Validator<Product>{
    @Override
    public int validate(Product product) {
        if (!Pattern.matches("[a-zA-Z]+", product.getName())) {
            throw new IllegalArgumentException("Invalid product name!");
        }
        return 0;
    }
}
