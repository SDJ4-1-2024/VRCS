package org.example.viewmodel.login;

import org.example.model.client.Client;
import org.example.model.client.ClientType;

import java.util.HashMap;
import java.util.Map;

public class LoginViewModel {
    private Map<String, Client> clients;

    public LoginViewModel() {
        clients = new HashMap<>();
        // Add example admin client
        clients.put("adminPhone", new Client("adminPhone", "adminPass", ClientType.ADMIN));
        clients.put("12345", new Client("12345", "qwerty", ClientType.PERSONAL));
    }

    public boolean authenticate(String phone, String password) {
        Client client = clients.get(phone);
        return client != null && client.password().equals(password);
    }

    public boolean isAdmin(String phone) {
        Client client = clients.get(phone);
        return client != null && client.type() == ClientType.ADMIN;
    }

    public void signUp(String phone, String password, String clientType) {
        ClientType type = ClientType.valueOf(clientType.toUpperCase());
        clients.put(phone, new Client(phone, password, type));
    }
}
