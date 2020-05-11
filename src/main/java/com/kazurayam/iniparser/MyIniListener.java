package com.kazurayam.iniparser;

import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.Objects;

public class MyIniListener extends INIBaseListener {

    private PrintWriter pw;

    MyIniListener(Writer writer) {
        Objects.requireNonNull(writer, "writer must not be null");
        pw = new PrintWriter(new BufferedWriter(writer));
    }

    @Override
    public void enterIni(INIParser.IniContext ctx) {
        pw.println(String.format("enterIni %s", ctx.getText()));
    }

    @Override
    public void exitIni(INIParser.IniContext ctx) {
        pw.println(String.format("exitIni %s", ctx.getText()));
        pw.flush();
    }

    @Override
    public void enterSection(INIParser.SectionContext ctx) {
        pw.println(String.format("enterSection %s", ctx.getText()));
    }

    @Override
    public void exitSection(INIParser.SectionContext ctx) {
        pw.println(String.format("exitSection %s", ctx.getText()));
    }

    @Override
    public void enterSection_header(INIParser.Section_headerContext ctx) {
        pw.println(String.format("enterSection_header %s", ctx.getText()));
    }

    @Override
    public void exitSection_header(INIParser.Section_headerContext ctx) {
        pw.println(String.format("exitSection_header %s", ctx.getText()));
    }

    @Override
    public void enterSection_header_title(INIParser.Section_header_titleContext ctx) {
        pw.println(String.format("enterSection_header_title %s", ctx.getText()));
    }

    @Override
    public void exitSection_header_title(INIParser.Section_header_titleContext ctx) {
        pw.println(String.format("exitSection_header_titile %s", ctx.getText()));
    }

    @Override
    public void enterKey_values(INIParser.Key_valuesContext ctx) {
        pw.println(String.format("enterKey_values %s", ctx.getText()));
    }

    @Override
    public void exitKey_values(INIParser.Key_valuesContext ctx) {
        pw.println(String.format("exitKey_values %s", ctx.getText()));
    }

    @Override
    public void enterKey_value(INIParser.Key_valueContext ctx) {
        pw.println(String.format("enterKey_value %s", ctx.getText()));
    }

    @Override
    public void exitKey_value(INIParser.Key_valueContext ctx) {
        pw.println(String.format("exitKey_value %s", ctx.getText()));
    }

    @Override
    public void enterKey(INIParser.KeyContext ctx) {
        pw.println(String.format("enterKey %s", ctx.getText()));
    }

    @Override
    public void exitKey(INIParser.KeyContext ctx) {
        pw.println(String.format("exitKey %s", ctx.getText()));
    }

    @Override
    public void enterValue(INIParser.ValueContext ctx) {
        pw.println(String.format("enterValue %s", ctx.getText()));
    }

    @Override
    public void exitValue(INIParser.ValueContext ctx) {
        pw.println(String.format("exitValue %s", ctx.getText()));
    }

    @Override
    public void enterText(INIParser.TextContext ctx) {
        pw.println(String.format("enterText %s", ctx.getText()));
    }

    @Override
    public void exitText(INIParser.TextContext ctx) {
        pw.println(String.format("exitText %s", ctx.getText()));
    }

}
