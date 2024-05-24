package org.example.model.client;

public record Client(String phoneNumber, String password, ClientType type) {
    public Client(String phoneNumber, ClientType type) {
        this(phoneNumber, "QWERTY", type);
    }
}
