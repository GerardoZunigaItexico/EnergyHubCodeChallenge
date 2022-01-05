package com.improving.energyhub.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


public class EventTest {

    private List<String> dataToTest = Arrays.asList(
    "{\"updateTime\": \"2016-01-01T01:44:00.000000\", \"update\": {\"ambientTemp\": 81}}",
    "{\"updateTime\": \"2016-01-01T02:47:30.000000\", \"update\": {\"ambientTemp\": 82}}",
    "{\"updateTime\": \"2016-01-01T02:55:00.000000\", \"update\": {\"heatTemp\": 78}}",
    "{\"updateTime\": \"2016-01-01T03:02:30.000000\", \"update\": {\"ambientTemp\": 78}}",
    "{\"updateTime\": \"2016-01-01T03:18:30.000000\", \"update\": {\"schedule\": true}}",
    "{\"updateTime\": \"2016-01-01T03:24:30.000000\", \"update\": {\"ambientTemp\": 79}}");

    @Test(expected = Test.None.class)
    public void testJsonDataConvertion() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Event event;
        for (String data : dataToTest){
            event = objectMapper.readValue(data,Event.class);
            Assert.assertNotNull(event);
        }
    }

}
