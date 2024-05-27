package org.example.repository;

import org.example.model.client.Client;
import org.example.model.client.ClientType;
import org.example.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
}
