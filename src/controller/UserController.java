package controller;

import model.User;
import service.UserService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserController {
    private final UserService userService;

    public UserController() {
        this.userService = new UserService();
    }

    public void listUsers() {
        try {
            List<User> users = userService.getAllUsers();
            for (User user : users) {
                System.out.println(user);
            }
        } catch (SQLException e) {
            System.out.println("Error listing users: " + e.getMessage());
        }
    }

    public User signup() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        User user = new User();
        user.setUserName(username);
        user.setPassword(password);

        try {
            userService.addUser(user);
            System.out.println("User successfully signed up!");
            return user;
        } catch (SQLException e) {
            System.out.println("Error signing up: " + e.getMessage());
        }
        return null;
    }

    public User login() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = sc.nextLine();
        System.out.println("Enter password: ");
        String password = sc.nextLine();

        try {
            User user = userService.getUserByUserName(username);
            if (user != null && user.getPassword().equals(password)) {
                System.out.println("Successfully logged in!");
                return user;
            } else {
                System.out.println("Invalid username or password!");
            }
        } catch (Exception e) {
            System.out.println("Error logging in: " + e.getMessage());
        }
        return null;
    }
}
