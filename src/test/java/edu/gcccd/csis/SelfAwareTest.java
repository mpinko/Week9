package edu.gcccd.csis;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.nio.file.Paths;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

public class SelfAwareTest {

    private String code;

    @Before
    public void setUp()
    {
        final String address = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "main" + File.separator + "java" + File.separator +
                HelloWorld.class.getName().replace(".", File.separator) + ".java";
        code = address;
    }

    @Test
    public void testOccurrences() throws Exception {
        assertNotNull(code);
        SelfAware sa = new SelfAware();
        int numOccurrences = sa.occurrences(code);
        assertEquals(numOccurrences, 6);
    }

    @Test
    public void testAppend() throws Exception {
        assertNotNull(code);
        assertNotNull(Paths.get(code));
        File source = new File(code);
        assertNotNull(source);
        long initialSize = source.length();
        SelfAware sa = new SelfAware();
        sa.append(code, "//Hello There!\n");
        long afterSize = source.length();
        assertTrue(afterSize > initialSize);

    }
}