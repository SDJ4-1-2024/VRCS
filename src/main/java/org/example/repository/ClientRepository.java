package org.example.repository;

import org.example.model.client.Client;
import org.example.model.client.ClientType;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ClientRepository {
    public Optional<Client> prepareClientById(int id) {
        String query = "SELECT * FROM clients WHERE id=?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String phoneNumber = rs.getString("phone_number");
                    String type = rs.getString("type");
                    if (type.equals(ClientType.PERSONAL.name())) {
                        return Optional.of(new Client(phoneNumber, ClientType.PERSONAL));
                    }
                    if (type.equals(ClientType.COMPANY.name())) {
                        return Optional.of(new Client(phoneNumber, ClientType.COMPANY));
                    }
                    if (type.equals(ClientType.ADMIN.name())) {
                        return Optional.of(new Client(phoneNumber, ClientType.ADMIN));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public HashMap<String, Client> prepareClients() {
        String query = "SELECT * FROM clients";
        HashMap<String, Client> clients = new HashMap<>(Map.of());
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String phoneNumber = rs.getString("phone_number");
                    String password = rs.getString("password");
                    String type = rs.getString("type");
                    clients.put(phoneNumber, new Client(phoneNumber, password, ClientType.valueOf(type)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clients;
    }

    public void saveClient(Client client) {
        String query = "INSERT INTO clients (phone_number, password, type) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, client.phoneNumber());
            stmt.setString(2, client.password());
            stmt.setString(3, client.type().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Integer> prepareClientIdByPhoneNumber(String phoneNumber) {
        String query = "SELECT id FROM clients WHERE phone_number = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, phoneNumber);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
