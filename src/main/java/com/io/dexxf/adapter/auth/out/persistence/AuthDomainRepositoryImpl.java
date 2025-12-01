package com.io.dexxf.adapter.auth.out.persistence;

import com.io.dexxf.domain.auth.repository.AuthDomainRepository;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class AuthDomainRepositoryImpl implements AuthDomainRepository {

    private final DataSource ds;

    public AuthDomainRepositoryImpl (DataSource ds) {
        this.ds = ds;
    }

    @Override
    public boolean authIdExist(String authId) {
        String query = "SELECT COUNT(*) FROM auth WHERE auth_id = ?;";
        try (Connection connection = ds.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, authId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                } else {
                    return false;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("failed to fetch authId " + authId, e);
        }
    }

    @Override
    public boolean userIdExist(String userId) {
        String query = "SELECT COUNT(*) FROM auth WHERE user_id = ?;";
        try (Connection connection = ds.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                } else {
                    return false;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("failed to fetch userId " + userId, e);
        }
    }

    @Override
    public boolean usernameTaken(String username) {
        String query = "SELECT COUNT(*) FROM auth WHERE username = ?;";
        try (Connection connection = ds.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)
        ) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                } else {
                    return false;
                }
            }
        }catch (SQLException e) {
            throw new RuntimeException("failed to fetch username " + username, e);
        }
    }

}
