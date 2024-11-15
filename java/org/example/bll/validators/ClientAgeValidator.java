package org.example.bll.validators;

import org.example.model.Client;

public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 7;
    private static final int MAX_AGE = 30;

    public int validate(Client t) {

        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The client Age limit is not respected!");
        }
        return 0;
    }

}
