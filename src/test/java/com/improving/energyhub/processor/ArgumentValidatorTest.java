package com.improving.energyhub.processor;

import com.sun.media.sound.InvalidDataException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class ArgumentValidatorTest {
    List<List> listArguments;

    /* This folder and the files created in it will be deleted after
     * tests are run, even in the event of failures or exceptions.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private File file1 = null, file2 = null;

    @Before
    public void createData(){
        try {
            file1 = folder.newFile( "thermostat-data.jsonl.gz" );
            file2 = folder.newFile( "thermostat-data.jsonl");

            listArguments = Arrays.asList(
                    Arrays.asList("ambientTemp", file1.getPath(),"2016-01-01T03:00:00.000000"),
                    Arrays.asList("ambientTemp", file2.getPath(),"2016-01-01T03:00:00.000000")
            );
        }
        catch( IOException ioe ) {
            System.err.println(
                    "error creating temporary test file in " +
                            this.getClass().getSimpleName() );
        }
    }

    @Test(expected = Test.None.class)
    public void testAllArgumentsAreValid() throws Exception {
        ArgumentValidator argumentValidator = new ArgumentValidator();
        for (List<String> arguments : listArguments){
            argumentValidator.validateArguments( arguments.stream().toArray(String[]::new) );
        }
    }

    @Test(expected = InvalidDataException.class)
    public void testInvalidFourArguments() throws Exception {
        ArgumentValidator argumentValidator = new ArgumentValidator();
        List<String> arguments = Arrays.asList("_Hello","tempetureAmbient", "thermostat-data.jsonl.gz","2016-01-01T03:00:00.000000");
        argumentValidator.validateArguments( arguments.stream().toArray(String[]::new) );
    }

    @Test(expected = InvalidDataException.class)
    public void testFirstArgumentInvalid() throws Exception {
        ArgumentValidator argumentValidator = new ArgumentValidator();
        List<String> arguments = Arrays.asList("tempetureAmbient", "thermostat-data.jsonl.gz","2016-01-01T03:00:00.000000");
        argumentValidator.validateArguments( arguments.stream().toArray(String[]::new) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondArgumentWithloutExtension() throws Exception {
        ArgumentValidator argumentValidator = new ArgumentValidator();
        List<String> arguments = Arrays.asList("ambientTemp", "thermostat-data","2016-01-01T03:00:00.000000");
        argumentValidator.validateArguments( arguments.stream().toArray(String[]::new) );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSecondArgumentInvalidExtension() throws Exception {
        ArgumentValidator argumentValidator = new ArgumentValidator();
        List<String> arguments = Arrays.asList("ambientTemp", "thermostat-data.xls","2016-01-01T03:00:00.000000");
        argumentValidator.validateArguments( arguments.stream().toArray(String[]::new) );
    }

    @Test(expected = FileNotFoundException.class)
    public void testSecondArgumentFileNotFound() throws Exception {
        ArgumentValidator argumentValidator = new ArgumentValidator();
        List<String> arguments = Arrays.asList("ambientTemp", "thermostat-data.jsonl.gz","2016-01-01T03:00:00.000000");
        argumentValidator.validateArguments( arguments.stream().toArray(String[]::new) );
    }

    @Test(expected = ParseException.class)
    public void testThirdArgumentDateParsingException() throws Exception {
        ArgumentValidator argumentValidator = new ArgumentValidator();
        List<String> arguments = Arrays.asList("ambientTemp", file1.getPath(),"2016-XY 03-00-00.000000");
        argumentValidator.validateArguments( arguments.stream().toArray(String[]::new) );
    }
}
