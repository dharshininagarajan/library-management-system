package service;

import api.LogAPI;
import model.Log;

import java.sql.SQLException;
import java.util.List;

public class LogService {
    private LogAPI logAPI;

    public LogService() {
        this.logAPI = new LogAPI();
    }

    public void addLog(Log log) throws SQLException {
        logAPI.addLog(log);
    }

    public List<Log> getAllLogs() throws SQLException {
        return logAPI.getAllLogs();
    }

    public Log getLogById(int logId) throws SQLException {
        return logAPI.getLogById(logId);
    }

    public Log getBorrowedLog(int userId, int bookId) throws SQLException {
        return logAPI.getBorrowedLog(userId, bookId);
    }

    public void updateLog(Log log) throws SQLException {
        logAPI.updateLog(log);
    }

    public void deleteLog(int logId) throws SQLException {
        logAPI.deleteLog(logId);
    }
}
