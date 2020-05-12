package com.kazurayam.iniparser;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Objects;

public class IniProcessor {

    private Reader reader;
    private INIListener listener;

    public IniProcessor() {
        this.reader = null;
        this.listener = null;
    }

    public void setInput(Reader reader) {
        Objects.requireNonNull(reader, "reader must not be null");
        this.reader = reader;
    }

    public void setINIListener(INIListener listener) {
        Objects.requireNonNull(listener, "listener must not be null");
        this.listener = listener;
    }

    private void validateParams() {
        if (reader == null)
            throw new IllegalArgumentException("setInput(Reader) is required");
        if (listener == null)
            throw new IllegalArgumentException("setINIListener(INIListener) is required");
    }


    public void process() throws IOException {
        // we instantiate the lexer
        INILexer lexer = new INILexer(CharStreams.fromReader(this.reader));
        // we instantiate the parser
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        INIParser iniParser = new INIParser(tokens);
        // we instantiate the walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // we already have the instance of INIListener injected as param
        // we invoke the parser to generate the tree, and walk around
        walker.walk(listener, iniParser.ini());
    }

}
