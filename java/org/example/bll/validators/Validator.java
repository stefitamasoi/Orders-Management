package org.example.bll.validators;

public interface Validator<T> {

    int validate(T t);
}