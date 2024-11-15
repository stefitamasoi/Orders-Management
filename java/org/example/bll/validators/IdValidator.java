package org.example.bll.validators;

public class IdValidator implements Validator<Integer> {
    @Override
    public int validate(Integer integer) {
        if (integer <= 0) {
            throw new IllegalArgumentException("Invalid ID!");
        }
        return 0;
    }
}