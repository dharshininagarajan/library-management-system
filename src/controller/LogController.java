package controller;

import model.Log;
import service.LogService;

import java.sql.SQLException;
import java.util.List;

public class LogController {
    private LogService logService;

    public LogController() {
        this.logService = new LogService();
    }

    public void listLogs() {
        try {
            List<Log> logs = logService.getAllLogs();
            for (Log log : logs) {
                System.out.println(log);
            }
        } catch (SQLException e) {
            System.out.println("Error listing logs: " + e.getMessage());
        }
    }
}
