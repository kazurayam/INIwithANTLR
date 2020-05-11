package com.kazurayam.logparser;

import java.time.LocalDateTime;

public class LogEntry {

    private LogLevel level;
    private String message;
    private LocalDateTime timestamp;

    // getters and setters
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    public LogLevel getLevel() {
        return this.level;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDateTime getTimestamp() {
        return this.timestamp;
    }
}
