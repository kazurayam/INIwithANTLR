package com.kazurayam.iniparser;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import static com.google.common.collect.Range.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class IniProcessorTest {

    @Test
    public void test_process_simpleFixture_withMyIniListener() throws IOException {
        // setup:
        String content = MyIniListenerTest.createFixture();
        IniProcessor processor = new IniProcessor();
        processor.setInput(new StringReader(content));
        StringWriter sw = new StringWriter();
        MyIniListener listener = new MyIniListener(sw);
        processor.setINIListener(listener);
        // when:
        processor.process();
        String result = sw.toString();
        // then:
        assertTrue(result.length() > 0);
        assertTrue(result, result.contains("DATABASE"));
    }

    @Test
    public void test_process_richFixture_withMyIniListener() throws IOException {
        // setup input:
        File richFixture = new File("./src/test/fixture/iniparser/sample.ctl");
        Reader reader = new InputStreamReader(
                new FileInputStream(richFixture), IniApp.ENCODING);
        // setup output:
        StringWriter sw = new StringWriter();
        // setup processor:
        IniProcessor processor = new IniProcessor();
        processor.setInput(reader);
        processor.setINIListener(new MyIniListener(sw));
        // when:
        processor.process();
        String result = sw.toString();
        // then:
        assertTrue(result.length() > 0);
        assertTrue(result, result.contains("開発環境"));
    }

}
