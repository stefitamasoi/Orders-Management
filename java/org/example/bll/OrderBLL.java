package org.example.bll;

import org.example.bll.validators.*;
import org.example.dao.OrderDAO;
import org.example.model.Orders;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderBLL {
    private final List<Validator<Orders>> validators;
    private final IdValidator idValidator;
    private OrderDAO orderDAO;

    public OrderBLL() {
        validators = new ArrayList<>();
        validators.add(new OrderQuantityValidator());
        idValidator = new IdValidator();
        orderDAO = new OrderDAO();
    }

    public void insertOrder(Orders order) {
        for (Validator<Orders> v : validators) {
            if (v.validate(order) != 0)
                return;
        }
        if (idValidator.validate(order.getClientId()) != 0)
            return;
        if (idValidator.validate(order.getProductId()) != 0)
            return;

        orderDAO.insert(order);
    }

    public void deleteOrder(int orderId) {
        if (idValidator.validate(orderId) != 0)
            return;

        orderDAO.deleteById(orderId);
    }

    public void editOrder(Orders order) {
        for (Validator<Orders> v : validators) {
            if (v.validate(order) != 0)
                return;
        }
        if (idValidator.validate(order.getId()) != 0)
            return;
        if (idValidator.validate(order.getClientId()) != 0)
            return;
        if (idValidator.validate(order.getProductId()) != 0)
            return;


        orderDAO.edit("clientId",String.valueOf(order.getClientId()),order.getId());
        orderDAO.edit("productId",String.valueOf(order.getProductId()),order.getId());
        orderDAO.edit("quantity",String.valueOf(order.getQuantity()),order.getId());
        orderDAO.edit("price",String.valueOf(order.getPrice()),order.getId());

    }

    public Orders findById(int id) throws SQLException {
        if (idValidator.validate(id) != 0)
            return null;

        return orderDAO.findById(id);
    }


    public ArrayList<Orders> printAllOrders() {
        return (ArrayList<Orders>) orderDAO.showAll();
    }
}