package com.improving.energyhub;

import com.improving.energyhub.bean.InputData;
import com.improving.energyhub.processor.ArgumentValidator;
import com.improving.energyhub.processor.EventProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringBootConsoleApplication
        implements CommandLineRunner {

    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootConsoleApplication.class);

    public static void main(String[] args) {
        LOG.info("STARTING THE APPLICATION");
        SpringApplication.run(SpringBootConsoleApplication.class, args);
        LOG.info("APPLICATION FINISHED");
    }

    @Override
    public void run(String... args) throws Exception {
        InputData inputData = new ArgumentValidator().validateArguments(args);
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputData);
        String value = object.toString();
        if (object == null ){
            LOG.info("There was not found a Entry with the requested Data", inputData.toString());
        } else{
            LOG.info("The current value is: ", value);
        }

    }
}