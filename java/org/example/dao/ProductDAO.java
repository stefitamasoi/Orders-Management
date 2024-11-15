package org.example.dao;

import java.sql.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.example.connection.ConnectionFactory;
import org.example.model.Product;

public class ProductDAO extends AbstractDAO<Product> {
    protected static final Logger LOGGER = Logger.getLogger(ProductDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO product (id, name, quantity, price)"
            + " VALUES (?, ?, ?, ?)";
    private static final String findStatementString = "SELECT * FROM product WHERE id = ?";

    private static final String findPriceByIdString = "SELECT price FROM product WHERE id = ?";

    public static float findPriceById(int id) {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findPriceByIdStatement = null;
        ResultSet rs;
        float price = 0;

        try {
            findPriceByIdStatement = dbConnection.prepareStatement(findPriceByIdString);
            findPriceByIdStatement.setInt(1, id);
            rs = findPriceByIdStatement.executeQuery();
            rs.next();

            price = rs.getFloat("price");

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:findPriceById " + e.getMessage());
        } finally {
            ConnectionFactory.close(findPriceByIdStatement);
            ConnectionFactory.close(dbConnection);
        }
        return price;
    }
}
