package com.kazurayam.java8;

import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;
import java.util.ArrayList;

public class UpperCaseMethodListener extends Java8BaseListener {

    private List<String> errors = new ArrayList<>();

    // ... getter for errors
    public List<String> getErrors() {
        return new ArrayList<String>(errors);
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        TerminalNode node = ctx.Identifier();
        String methodName = node.getText();

        if (Character.isUpperCase(methodName.charAt(0))) {
            String error = String.format("Method %s is uppercased!", methodName);
            errors.add(error);
        }
    }

}
