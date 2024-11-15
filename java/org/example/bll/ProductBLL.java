package org.example.bll;

import org.example.bll.validators.*;
import org.example.dao.ProductDAO;
import org.example.model.Product;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductBLL {
    private final List<Validator<Product>> validators;
    private final IdValidator idValidator;
    private ProductDAO productDAO;

    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new PriceValidator());
        validators.add(new ProductNameValidator());
        validators.add(new ProductQuantityValidator());
        idValidator = new IdValidator();
        productDAO = new ProductDAO();
    }
    public void insertProduct(Product product) {
        for (Validator<Product> v : validators) {
            if (v.validate(product) != 0)
                return;
        }
        productDAO.insert(product);
    }

    public float findPriceById(int id) throws SQLException {
        if (idValidator.validate(id) != 0)
            return -1;

        return ProductDAO.findPriceById(id);
    }

    public Product findById(int id) throws SQLException {
        if (idValidator.validate(id) != 0)
            return null;

        return productDAO.findById(id);
    }

    public void deleteProduct(int productId) {
        if (idValidator.validate(productId) != 0)
            return;

        productDAO.deleteById(productId);
    }

    public void editProduct(Product product) {
        for (Validator<Product> v : validators) {
            if (v.validate(product) != 0)
                return;
        }
        if (idValidator.validate(product.getId()) != 0)
            return;

        productDAO.edit("name",product.getName(),product.getId());
        productDAO.edit("quantity",String.valueOf(product.getQuantity()),product.getId());
        productDAO.edit("price",String.valueOf(product.getPrice()), product.getId());

    }

    public ArrayList<Product> printAllProducts() {
        return (ArrayList<Product>) productDAO.showAll();
    }

}