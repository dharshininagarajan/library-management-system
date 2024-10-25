package api;

import database.Database;
import model.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LogAPI {
    public void addLog(Log log) throws SQLException {
        String query = "INSERT INTO log (user_id, book_id, borrowed_date, returned_date, status) VALUES (?, ?, ?, ?, ?)";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, log.getUserId());
            ps.setInt(2, log.getBookId());
            ps.setDate(3, log.getBorrowedDate());
            ps.setDate(4, log.getReturnedDate());
            ps.setString(5, log.getStatus());
            ps.executeUpdate();
        }
    }

    public List<Log> getAllLogs() throws SQLException {
        List<Log> logs = new ArrayList<Log>();
        String query = "SELECT * FROM log";
        try (
                Connection conn = Database.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);
        ) {
            while (rs.next()) {
                logs.add(new Log(
                        rs.getInt("log_id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("borrowed_date"),
                        rs.getDate("returned_date"),
                        rs.getString("status")
                ));
            }
            return logs;
        }
    }

    public Log getLogById(int logId) throws SQLException {
        String query = "SELECT * FROM log WHERE log_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, logId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Log(
                        rs.getInt("log_id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("borrowed_date"),
                        rs.getDate("returned_date"),
                        rs.getString("status")
                );
            }
        }
        return null;
    }

    public Log getBorrowedLog(int userId, int bookId) throws SQLException {
        String query = "SELECT * FROM log WHERE user_id = ? AND book_id = ? AND status = 'borrowed'";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, userId);
            ps.setInt(2, bookId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Log(
                        rs.getInt("log_id"),
                        rs.getInt("user_id"),
                        rs.getInt("book_id"),
                        rs.getDate("borrowed_date"),
                        rs.getDate("returned_date"),
                        rs.getString("status")
                );
            }
        }
        return null;
    }

    public void updateLog(Log log) throws SQLException {
        String query = "UPDATE log SET user_id = ?, book_id = ?, borrowed_date = ?, returned_date = ?, status = ? WHERE log_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, log.getUserId());
            ps.setInt(2, log.getBookId());
            ps.setDate(3, log.getBorrowedDate());
            ps.setDate(4, log.getReturnedDate());
            ps.setString(5, log.getStatus());
            ps.setInt(6, log.getLogId());
            ps.executeUpdate();
        }
    }

    public void deleteLog(int logId) throws SQLException {
        String query = "DELETE FROM logs WHERE log_id = ?";
        try (
                Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(query);
        ) {
            ps.setInt(1, logId);
            ps.executeUpdate();
        }
    }
}
