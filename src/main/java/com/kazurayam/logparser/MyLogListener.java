package com.kazurayam.logparser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyLogListener extends LogBaseListener {

    // e.g, "2018-May-05 14:20:18"
    public static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER
            = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm:ss")
                .withLocale(Locale.US);

    private List<LogEntry> entries = new ArrayList<LogEntry>();
    private LogEntry current;

    List<LogEntry> getEntries() {
        return new ArrayList<LogEntry>(this.entries);
    }

    @Override
    public void enterEntry(LogParser.EntryContext ctx) {
        this.current = new LogEntry();
    }

    @Override
    public void enterTimestamp(LogParser.TimestampContext ctx) {
        this.current.setTimestamp(
                LocalDateTime.parse(ctx.getText(), DEFAULT_DATETIME_FORMATTER));
    }

    @Override
    public void enterLevel(LogParser.LevelContext ctx) {
        this.current.setLevel(
                LogLevel.valueOf(ctx.getText()));
    }

    @Override
    public void enterMessage(LogParser.MessageContext ctx) {
        this.current.setMessage(
                ctx.getText());
    }

    @Override
    public void exitEntry(LogParser.EntryContext ctx) {
        this.entries.add(this.current);
    }
}
