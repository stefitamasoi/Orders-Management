package org.example.start;

import java.io.IOException;
import java.util.logging.Logger;

import org.example.presentation.Controller;
import org.example.presentation.View;

public class Start {
    protected static final Logger LOGGER = Logger.getLogger(Start.class.getName());

    public static void main(String[] args) throws IOException {

        View view = new View();
        new Controller(view);

    }

}