package service;

import api.UserAPI;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private UserAPI userAPI;

    public UserService() {
        this.userAPI = new UserAPI();
    }

    public void addUser(User user) throws SQLException {
        userAPI.addUser(user);
    }

    public User getUserById(int userId) throws SQLException {
        return userAPI.getUserById(userId);
    }

    public User getUserByUserName(String username) throws SQLException {
        return userAPI.getUserByUserName(username);
    }

    public List<User> getAllUsers() throws SQLException {
        return userAPI.getAllUsers();
    }

    public void updateUser(User user) throws SQLException {
        userAPI.updateUser(user);
    }

    public void deleteUser(int userId) throws SQLException {
        userAPI.deleteUser(userId);
    }
}
