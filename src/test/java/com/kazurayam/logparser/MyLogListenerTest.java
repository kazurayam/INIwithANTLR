package com.kazurayam.logparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.StringReader;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class MyLogListenerTest {

    @Test
    public void test_DEFAULT_DATETIME_FORMATTER() {
        String text = "2018-May-05 14:20:24";
        LocalDateTime ldt = LocalDateTime.parse(text, MyLogListener.DEFAULT_DATETIME_FORMATTER);
        assertThat(ldt, is(notNullValue()));
        assertThat(ldt, is(LocalDateTime.of(2018,5,5,14,20,24)));
    }

    @Test
    public void test_whenLogContainsOneErrorLogEntry_thenOneErrorIsReturned() throws Exception {

        String logLine = "2018-May-05 14:20:24 ERROR Bad thing happened\n";
        // setup:
        // we instantiate the lexer
        LogLexer lexer = new LogLexer(CharStreams.fromReader(new StringReader(logLine)));
        // we instantiate the parser
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LogParser logParser = new LogParser(tokens);
        //ParseTree tree = parser.compilationUnit();
        // we instantiate the walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // we instantiate the listener
        MyLogListener listener = new MyLogListener();
        // Lastly, we tell ANTLR to walk through the sample log line
        walker.walk(listener, logParser.log());
        //
        assertThat(listener.getEntries(), is(notNullValue()));
        assertThat(listener.getEntries().size(), is(1));
        //
        LogEntry entry = listener.getEntries().get(0);
        // then:
        assertThat(entry, is(notNullValue()));
        assertThat(entry.getLevel(), is(LogLevel.ERROR));
        assertThat(entry.getMessage(), is("Bad thing happened"));
        assertThat(entry.getTimestamp(), is(LocalDateTime.of(2018,5,5,14,20,24)));
    }
}
