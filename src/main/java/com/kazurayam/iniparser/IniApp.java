package com.kazurayam.iniparser;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class IniApp {

    public static final String ENCODING = "utf-8";

    /**
     * Usage:
     *
     * $ java -jar xxxx -i <input> -o <output>
     * @param args
     */
    public static void main(String[] args) throws IOException {
        // コマンドライン引数を前処理する
        CommandLine cmd = parseCommandLineArguments(args);
        // inputを構築する
        Reader reader;
        if (cmd.hasOption("i")) {
            File f = new File(String.valueOf(cmd.getOptionValues("i")));
            InputStream is = new FileInputStream(f);
            reader = new InputStreamReader(is, ENCODING);
        } else {
            throw new IllegalArgumentException("-i <file> option is required");
        }
        // outputを構築し、INIListenerを生成する
        Writer writer;
        if (cmd.hasOption("o")) {
            File f = new File(String.valueOf(cmd.getOptionValues("o")));
            OutputStream os = new FileOutputStream(f);
            writer = new OutputStreamWriter(os, ENCODING);
        } else {
            throw new IllegalArgumentException("-o <file> option is required");
        }
        INIListener listener = new MyIniListener(writer);
        // IniProcessorを構築して
        IniProcessor processor = new IniProcessor();
        processor.setInput(reader);
        processor.setINIListener(listener);
        // IniProcessorを動かす
        processor.process();
    }

    public static CommandLine parseCommandLineArguments(String[] args) {
        // コマンドラインオプションを設定する
        Options options = new Options();
        options.addOption(Option.builder("i")
                .argName("input")
                .required()
                .hasArg()
                .desc("入力となるINIファイルのパス")
                .build());
        options.addOption(Option.builder("o")
                .argName("output")
                .required()
                .hasArg()
                .desc("出力となるテキストファイルのパス")
                .build());

        // コマンドラインを解析する
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            // ヘルプを表示する
            HelpFormatter hf = new HelpFormatter();
            hf.printHelp("[opts]", options);

            throw new IllegalArgumentException("CommandLineParser failed. "
                    + e.getMessage());
        }
        return cmd;
    }
}
