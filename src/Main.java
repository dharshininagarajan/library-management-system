import controller.BookController;
import controller.LogController;
import controller.UserController;
import model.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        welcome();
    }

    private static void welcome() {
        System.out.println("Welcome to Library!");
        mainMenu();
    }

    private static void mainMenu() {
        UserController userController = new UserController();
        BookController bookController = new BookController();
        LogController logController = new LogController();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Menu: ");
            System.out.println("1. Sign Up");
            System.out.println("2. Login");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");

            String tempChoice = sc.next();
            int choice = Integer.parseInt(tempChoice);

            switch (choice) {
                case 1:
                    User userSignup = userController.signup();
                    if (userSignup != null) {
                        userMenu(bookController, logController, userSignup);
                    }
                    break;
                case 2:
                    User userLogin = userController.login();
                    if (userLogin != null) {
                        userMenu(bookController, logController, userLogin);
                    }
                    break;
                case 3:
                    User adminLogin = userController.login();
                    if (adminLogin != null) {
                        adminMenu(userController, bookController, logController);
                    }
                    break;
                case 4:
                    System.out.println("Exiting Library...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void userMenu(BookController bookController, LogController logController, User user) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("User Menu: ");
            System.out.println("1. List All Books");
            System.out.println("2. Search Book by ID");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Borrow Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");
            System.out.println("Enter your choice:");

            String tempChoice = sc.next();
            int choice = Integer.parseInt(tempChoice);

            switch (choice) {
                case 1:
                    bookController.listBooks();
                    break;
                case 2:
                    bookController.getBookById();
                    break;
                case 3:
                    bookController.getBookByTitle();
                    break;
                case 4:
                    bookController.getBookByAuthor();
                    break;
                case 5:
                    bookController.borrowBook(user);
                    break;
                case 6:
                    bookController.returnBook(user);
                    break;
                case 7:
                    System.out.println("User logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void adminMenu(UserController userController, BookController bookController, LogController logController) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Admin Menu: ");
            System.out.println("1. List All Books");
            System.out.println("2. Search Book by ID");
            System.out.println("3. Search Book by Title");
            System.out.println("4. Search Book by Author");
            System.out.println("5. Display Log");
            System.out.println("6. Add Book");
            System.out.println("7. Edit Book");
            System.out.println("8. Delete Book");
            System.out.println("9. List All Users");
            System.out.println("10. Exit");
            System.out.println("Enter your choice:");

            String tempChoice = sc.next();
            int choice = Integer.parseInt(tempChoice);

            switch (choice) {
                case 1:
                    bookController.listBooks();
                    break;
                case 2:
                    bookController.getBookById();
                    break;
                case 3:
                    bookController.getBookByTitle();
                    break;
                case 4:
                    bookController.getBookByAuthor();
                    break;
                case 5:
                    logController.listLogs();
                    break;
                case 6:
                    bookController.addBook();
                    break;
                case 7:
                    bookController.editBook();
                    break;
                case 8:
                    bookController.deleteBook();
                    break;
                case 9:
                    userController.listUsers();
                    break;
                case 10:
                    System.out.println("Admin logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
