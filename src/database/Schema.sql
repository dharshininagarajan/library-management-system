CREATE DATABASE library;

USE library;

CREATE TABLE books
(
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    total_copies INT NOT NULL,
    available_copies INT NOT NULL
);

INSERT INTO books (title, author, total_copies, available_copies)
VALUES
    ('To Kill a Mockingbird', 'Harper Lee', 5, 5),
    ('1984', 'George Orwell', 7, 7),
    ('Pride and Prejudice', 'Jane Austen', 3, 3),
    ('The Great Gatsby', 'F. Scott Fitzgerald', 4, 4),
    ('The Catcher in the Rye', 'J.D. Salinger', 6, 6),
    ('Animal Farm', 'George Orwell', 8, 8),
    ('Brave New World', 'Aldous Huxley', 5, 5),
    ('Lord of the Flies', 'William Golding', 4, 4),
    ('Frankenstein', 'Mary Shelley', 6, 6),
    ('Wuthering Heights', 'Emily Brontë', 3, 3),
    ('Jane Eyre', 'Charlotte Brontë', 5, 5),
    ('Moby-Dick', 'Herman Melville', 7, 7),
    ('The Hobbit', 'J.R.R. Tolkien', 6, 6),
    ('Harry Potter', 'J.K. Rowling', 10, 10),
    ('The Da Vinci Code', 'Dan Brown', 8, 8),
    ('The Dragon Tattoo', 'Stieg Larsson', 5, 5),
    ('Gone with the Wind', 'Margaret Mitchell', 4, 4),
    ('Guide to the Galaxy', 'Douglas Adams', 6, 6),
    ('The Hunger Games', 'Suzanne Collins', 7, 7),
    ('The Road', 'Cormac McCarthy', 4, 4),
    ('The Shining', 'Stephen King', 5, 5),
    ('A Game of Thrones', 'George R.R. Martin', 8, 8),
    ('The Name of the Wind', 'Patrick Rothfuss', 5, 5),
    ('The Alchemist', 'Paulo Coelho', 6, 6),
    ('Crime and Punishment', 'Fyodor Dostoevsky', 3, 3),
    ('The Picture of Dorian Gray', 'Oscar Wilde', 4, 4),
    ('Anna Karenina', 'Leo Tolstoy', 6, 6),
    ('The Brothers Karamazov', 'Fyodor Dostoevsky', 5, 5),
    ('The Count of Monte Cristo', 'Alexandre Dumas', 7, 7),
    ('Don Quixote', 'Miguel de Cervantes', 6, 6);

CREATE TABLE users
(
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password) VALUES ('admin', 'admin');

CREATE TABLE log
(
    log_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    borrowed_date DATE,
    returned_date DATE,
    status ENUM('Borrowed', 'Returned') NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);
