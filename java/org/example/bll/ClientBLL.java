
package org.example.bll;

import java.util.ArrayList;
import java.util.List;


import org.example.bll.validators.*;
import org.example.dao.ClientDAO;
import org.example.model.Client;

public class ClientBLL {
    private final List<Validator<Client>> validators;
    private final IdValidator idValidator;
    private ClientDAO clientDAO;


    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());
        validators.add(new ClientAgeValidator());
        validators.add(new ClientNameValidator());
        idValidator = new IdValidator();
        clientDAO = new ClientDAO();
    }


    public void insertClient(Client client) {
        for (Validator<Client> v : validators) {
            if (v.validate(client) != 0)
                return;
        }
        clientDAO.insert(client);
    }

    public void deleteClient(int clientId) {
        if (idValidator.validate(clientId) != 0)
            return;
        clientDAO.deleteById(clientId);
    }


    public void editClient(Client client) {
        for (Validator<Client> v : validators) {
            if (v.validate(client) != 0)
                return;
        }
        if (idValidator.validate(client.getId()) != 0)
            return;
        clientDAO.edit("name",client.getName(),client.getId());
        clientDAO.edit("email", client.getEmail(),client.getId());
        clientDAO.edit("age",String.valueOf(client.getAge()), client.getId());
    }

    public ArrayList<Client> printAllClients() {
        return (ArrayList<Client>) clientDAO.showAll();
    }

    public Client findById(int id)
    {
        if(idValidator.validate(id)!=0)
            return null;

        return clientDAO.findById(id);
    }
}
