package org.example.bll.validators;
import org.example.model.Orders;
public class OrderQuantityValidator implements Validator<Orders>{
    @Override
    public int validate(Orders order) {
        if(order.getQuantity() <= 0){
            throw new IllegalArgumentException("Invalid quantity for this order!");
        }
        return 0;
    }
}
