package com.kazurayam.iniparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

public class MyIniListenerTest {

    @Test
    public void test_smoke() throws Exception {
        String content = this.createFixture();
        // setup:
        // we instantiate the lexer
        INILexer lexer = new INILexer(CharStreams.fromReader(new StringReader(content)));
        // we instantiate the parser
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        INIParser iniParser = new INIParser(tokens);
        // we instantiate the walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // we instantiate the listener
        StringWriter buffer = new StringWriter();
        MyIniListener listener = new MyIniListener(buffer);
        walker.walk(listener, iniParser.ini());
        // print the buffer
        System.out.print(String.format("%s", buffer.toString()));
    }

    protected static String createFixture() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("[DATABASE]\n");
        sb.append("SERVER_NAME=(localdb)\\v11.0\n");
        sb.append("DATABASE_NAME=hogehoge\n");
        sb.append("LOGIN_ID=myname\n");
        sb.append("LOGIN_PWD=mypwd\n");
        sb.append("CONNECT_TIMEOUT=30");
        return sb.toString();
    }
}
