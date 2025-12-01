package com.io.dexxf.adapter.auth.out.persistence;

import com.io.dexxf.application.auth.port.out.AuthAppRepository;
import com.io.dexxf.domain.auth.entity.Auth;
import com.io.dexxf.domain.auth.valueobject.Email;
import com.io.dexxf.domain.auth.valueobject.HashedPassword;
import com.io.dexxf.domain.auth.valueobject.Roles;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public class AuthAppRepositoryImpl implements AuthAppRepository {

    private final DataSource ds;

    public AuthAppRepositoryImpl (DataSource ds) {
        this.ds = ds;
    }

    @Override
    public Optional<Auth> findByUsername(String username) {
        String query = "SELECT * FROM auth WHERE username = ?;";

        try (Connection connection = ds.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {

                if (!rs.next()) {
                    return Optional.empty();
                }

                String authId = rs.getString("auth_id");
                String userId = rs.getString("user_id");
                String un = rs.getString("username");
                Email email = Email.of(rs.getString("email")).data();
                HashedPassword password = HashedPassword.of(rs.getString("password")).data();
                Roles role = Roles.valueOf(rs.getString("roles").toUpperCase());
                LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();

                Auth auth = new Auth(authId, userId, un, email, password, createdAt, role);

                return Optional.of(auth);
            }

        } catch (SQLException e) {
            throw new RuntimeException("failed to fetch the user" + username, e);
        }
    }

    @Override
    public void save(Auth auth) {
        String query = "INSERT INTO auth (auth_id,user_id,username,email,password,roles,created_at) VALUES(?,?,?,?,?,?,?);";
        try(Connection connection = ds.getConnection();
            PreparedStatement stmt = connection.prepareStatement(query);
        ) {
            stmt.setString(1, auth.getAuthId());
            stmt.setString(2, auth.getUserId());
            stmt.setString(3, auth.getUsername());
            stmt.setString(4, auth.getEmail().getEmail());
            stmt.setString(5, auth.getPassword().get());
            stmt.setString(6, auth.getRoles().name());
            stmt.setTimestamp(7, auth.getCreatedAt() != null ? Timestamp.valueOf(auth.getCreatedAt()) : Timestamp.valueOf(LocalDateTime.now()));
            stmt.executeUpdate();

        }catch(SQLException e) {
            throw new RuntimeException("Failed to save auth: " + auth.getAuthId(), e);
        }
    }
}
