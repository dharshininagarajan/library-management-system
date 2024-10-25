package model;

import java.sql.Date;

public class Log {
    private int logId;
    private int userId;
    private int bookId;
    private Date borrowedDate;
    private Date returnedDate;
    private String status;

    public Log() {}

    public Log(int logId, int userId, int bookId, Date borrowedDate, Date returnedDate, String status) {
        this.logId = logId;
        this.userId = userId;
        this.bookId = bookId;
        this.borrowedDate = borrowedDate;
        this.returnedDate = returnedDate;
        this.status = status;
    }

    public int getLogId() {
        return logId;
    }

    public void setLogId(int logId) {
        this.logId = logId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public java.sql.Date getBorrowedDate() {
        return borrowedDate;
    }

    public void setBorrowedDate(Date borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public Date getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(Date returnedDate) {
        this.returnedDate = returnedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Log {logId = " + logId + ", userId = " + userId + ", bookId = " + bookId + ", borrowedDate = " + borrowedDate + ", returnedDate = " + returnedDate + ", status = " + status + "}";
    }
}

