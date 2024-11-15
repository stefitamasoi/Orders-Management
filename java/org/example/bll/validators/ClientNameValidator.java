package org.example.bll.validators;

import org.example.model.Client;

import java.util.regex.Pattern;

public class ClientNameValidator implements Validator<Client>{

    @Override
    public int validate(Client client) {
        if (!Pattern.matches("[a-zA-Z]+", client.getName())) {
            throw new IllegalArgumentException("Invalid client name!");
        }
        return 0;
    }
}
