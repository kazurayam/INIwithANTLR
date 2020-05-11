package com.kazurayam.java8;

import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class UpperCaseMethodListenerTest {

    @Test
    public void test_smoke() {
        // setup:
        // we have a Java code as a fixture
        String javaClassContent = "public class SampleClass { void DoSomething(){} }";
        // we construct the lexer
        Java8Lexer java8Lexer = new Java8Lexer(CharStreams.fromString(javaClassContent));
        // we instantiate the parser
        CommonTokenStream tokens = new CommonTokenStream(java8Lexer);
        Java8Parser parser = new Java8Parser(tokens);
        ParseTree tree = parser.compilationUnit();
        // we instantiate the walker
        ParseTreeWalker walker = new ParseTreeWalker();
        // we instantiate the listener
        UpperCaseMethodListener listener = new UpperCaseMethodListener();
        // when:
        // Lastly, we tell ANTLR to walk through our sample class
        walker.walk(listener, tree);
        // then:
        assertThat(listener.getErrors().size(), is(1));
        assertThat(listener.getErrors().get(0), is("Method DoSomething is uppercased!"));
    }
}
