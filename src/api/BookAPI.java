package api;

import model.Book;
import database.Database;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class BookAPI {
    public void addBook(Book book) throws SQLException {
        String query = "INSERT INTO books (title, author, total_copies, available_copies) VALUES (?, ?, ?, ?)";
        try(
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getTotalCopies());
            ps.setInt(4, book.getAvailableCopies());
            ps.executeUpdate();
        }
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        try (
                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                ));
            }
        }
        return books;
    }

    public Book getBookById(int bookId) throws SQLException {
        String query = "SELECT * FROM books WHERE book_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)
        ) {
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                );
            }
        }
        return null;
    }

    public List<Book> getBookByTitle(String title) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE title LIKE ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, "%" + title + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                ));
            }
        }
        return books;
    }

    public List<Book> getBookByAuthor(String author) throws SQLException {
        List<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books WHERE author LIKE ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, "%" + author + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("total_copies"),
                        rs.getInt("available_copies")
                ));
            }
        }
        return books;
    }

    public void updateBook(Book book) throws SQLException {
        String query = "UPDATE books SET title = ?, author = ?, total_copies = ?, available_copies = ? WHERE book_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setInt(3, book.getTotalCopies());
            ps.setInt(4, book.getAvailableCopies());
            ps.setInt(5, book.getBookId());
            ps.executeUpdate();
        }
    }

    public void deleteBook(int bookId) throws SQLException {
        String query = "DELETE FROM books WHERE book_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, bookId);
            ps.executeUpdate();
        }
    }
}
