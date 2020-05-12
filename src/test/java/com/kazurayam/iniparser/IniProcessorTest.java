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

}
