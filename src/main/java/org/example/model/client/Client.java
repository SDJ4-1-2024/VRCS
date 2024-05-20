package org.example.model.client;

public record Client(String firstName, String lastName, String phoneNumber, String password, ClientType type) {
}
