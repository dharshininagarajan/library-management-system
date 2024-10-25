package api;

import database.Database;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserAPI {
    public void addUser(User user) throws SQLException {
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.executeUpdate();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM users";
        try (
                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        }
        return users;
    }

    public User getUserById(int user_id) throws SQLException {
        String query = "SELECT * FROM users WHERE user_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, user_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        }
        return null;
    }

    public User getUserByUserName(String username) throws SQLException {
        String query = "SELECT * FROM users WHERE username = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password")
                );
            }
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        String query = "UPDATE users SET username = ?, password = ? WHERE user_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setInt(3, user.getUserId());
            ps.executeUpdate();
        }
    }

    public void deleteUser(int user_id) throws SQLException {
        String query = "DELETE FROM users WHERE user_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, user_id);
            ps.executeUpdate();
        }
    }
}
