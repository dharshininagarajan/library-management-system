package service;

import api.BookAPI;
import model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
    private BookAPI bookAPI;

    public BookService() {
        this.bookAPI = new BookAPI();
    }

    public void addBook(Book book) throws SQLException {
        bookAPI.addBook(book);
    }

    public List<Book> getAllBooks() throws SQLException {
        return bookAPI.getAllBooks();
    }

    public Book getBookById(int bookId) throws SQLException {
        return bookAPI.getBookById(bookId);
    }

    public List<Book> getBookByTitle(String title) throws SQLException {
        return bookAPI.getBookByTitle(title);
    }

    public List<Book> getBookByAuthor(String author) throws SQLException {
        return bookAPI.getBookByAuthor(author);
    }

    public void updateBook(Book book) throws SQLException {
        bookAPI.updateBook(book);
    }

    public void deleteBook(int bookId) throws SQLException {
        bookAPI.deleteBook(bookId);
    }
}
