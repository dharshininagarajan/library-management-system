package controller;

import model.Book;
import model.Log;
import model.User;
import service.BookService;
import service.LogService;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BookController {
    private BookService bookService;
    private LogService logService;

    public BookController() {
        this.bookService = new BookService();
        this.logService = new LogService();
    }

    public void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book title:");
        String title = sc.nextLine();
        System.out.println("Enter book author:");
        String author = sc.nextLine();
        System.out.println("Enter total copies:");
        int totalCopies = sc.nextInt();
        System.out.println("Enter available copies:");
        int availableCopies = sc.nextInt();

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setTotalCopies(totalCopies);
        book.setAvailableCopies(availableCopies);

        try {
            bookService.addBook(book);
            System.out.println("Book added successfully!");
        } catch (SQLException e) {
            System.out.println("Error adding book: " + e.getMessage());
        }
    }

    public void listBooks() {
        try {
            List<Book> books = bookService.getAllBooks();
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("Error listing books: " + e.getMessage());
        }
    }

    public void getBookById() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book id:");
        int bookId = sc.nextInt();
        try {
            Book book = bookService.getBookById(bookId);
            if (book != null) {
                System.out.println(book);
            } else {
                System.out.println("Book not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error getting book: " + e.getMessage());
        }
    }

    public void getBookByTitle() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book title:");
        String title = sc.nextLine();
        try {
            List<Book> books = bookService.getBookByTitle(title);
            if (books != null) {
                for (Book book : books) {
                    System.out.println(book);
                }
            } else {
                System.out.println("No match found!");
            }
        } catch (Exception e) {
            System.out.println("Error getting book: " + e.getMessage());
        }
    }

    public void getBookByAuthor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book author:");
        String author = sc.nextLine();
        try {
            List<Book> books = bookService.getBookByAuthor(author);
            if (books != null) {
                for (Book book : books) {
                    System.out.println(book);
                }
            } else {
                System.out.println("No match found!");
            }
        } catch (Exception e) {
            System.out.println("Error getting book: " + e.getMessage());
        }
    }

    public void editBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book id:");
        int bookId = sc.nextInt();
        sc.nextLine();

        try {
            Book book = bookService.getBookById(bookId);
            if (book != null) {
                System.out.println("Current title: " + book.getTitle());
                System.out.println("Enter new title:");
                String newTitle = sc.nextLine();
                System.out.println("Current author: " + book.getAuthor());
                System.out.println("Enter new author:");
                String newAuthor = sc.nextLine();
                System.out.println("Current copies: " + book.getTotalCopies());
                System.out.println("Enter new copies:");
                int newCopies = sc.nextInt();
                sc.nextLine();
                System.out.println("Current available copies: " + book.getAvailableCopies());
                System.out.println("Enter new available copies:");
                int newAvailableCopies = sc.nextInt();
                sc.nextLine();

                book.setTitle(newTitle);
                book.setAuthor(newAuthor);
                book.setTotalCopies(newCopies);
                book.setAvailableCopies(newAvailableCopies);
                bookService.updateBook(book);
                System.out.println("Book updated successfully!");
            } else {
                System.out.println("Book not found!");
            }
        } catch (SQLException e) {
            System.out.println("Error editing book: " + e.getMessage());
        }
    }

    public void borrowBook(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book id:");
        int bookId = sc.nextInt();
        try {
            Book book = bookService.getBookById(bookId);
            if (book != null && book.getAvailableCopies() > 0) {
                book.setAvailableCopies(book.getAvailableCopies() - 1);
                bookService.updateBook(book);
                Log log = new Log();
                log.setUserId(user.getUserId());
                log.setBookId(bookId);
                LocalDate date = LocalDate.now();
                log.setBorrowedDate(Date.valueOf(date));
                log.setStatus("Borrowed");
                logService.addLog(log);
                System.out.println("Book borrowed successfully!");
            } else {
                System.out.println("Book not available!");
            }
        } catch (Exception e) {
            System.out.println("Error borrowing book: " + e.getMessage());
        }
    }

    public void returnBook(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book id:");
        int bookId = sc.nextInt();
        try {
            Book book = bookService.getBookById(bookId);
            if (book != null && book.getAvailableCopies() < book.getTotalCopies()) {
                book.setAvailableCopies(book.getAvailableCopies() + 1);
                bookService.updateBook(book);
                Log log = logService.getBorrowedLog(user.getUserId(), bookId);
                if (log != null) {
                    LocalDate date = LocalDate.now();
                    log.setReturnedDate(Date.valueOf(date));
                    log.setStatus("Returned");
                    logService.updateLog(log);
                    System.out.println("Book returned successfully!");
                } else {
                    System.out.println("No borrow record found!");
                }
            } else {
                System.out.println("Book not available!");
            }
        } catch (Exception e) {
            System.out.println("Error returning book: " + e.getMessage());
        }
    }

    public void deleteBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter book id:");
        int bookId = sc.nextInt();
        try {
            bookService.deleteBook(bookId);
            System.out.println("Book deleted successfully!");
        } catch (SQLException e) {
            System.out.println("Error deleting book: " + e.getMessage());
        }
    }
}
