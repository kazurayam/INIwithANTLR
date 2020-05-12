package com.kazurayam.iniparser;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import static com.google.common.collect.Range.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class IniProcessorTest {

    @Test
    public void test_process_withMyIniListener() throws IOException {
        // setup:
        IniProcessor processor = new IniProcessor();
        String content = MyIniListenerTest.createFixture();
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
