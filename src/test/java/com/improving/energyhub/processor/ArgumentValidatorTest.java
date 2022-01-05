package com.improving.energyhub.processor;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class ArgumentValidatorTest {
    List<List> listArguments;

    /* This folder and the files created in it will be deleted after
     * tests are run, even in the event of failures or exceptions.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void createData(){
        File file1, file2;

        try {
            file1 = folder.newFile( "thermostat-data.jsonl.gz" );
            file2 = folder.newFile( "thermostat-data.jsonl" );

            listArguments = Arrays.asList(
                    Arrays.asList("ambientTemp", file1.getPath(),"2016-01-01T03:00"),
                    Arrays.asList("ambientTemp", file2.getPath(),"2016-01-01T03:00")
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
}
