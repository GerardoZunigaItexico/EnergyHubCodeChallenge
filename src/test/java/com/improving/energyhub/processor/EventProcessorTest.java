package com.improving.energyhub.processor;

import com.improving.energyhub.bean.InputData;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class EventProcessorTest {

    public InputData inputJsonData, inputGZData;

    @Test(expected = Test.None.class)
    public void testReadJsonFileAmbientTemp() throws IOException, ParseException {
        inputJsonData = new InputData("ambientTemp", new File("src/test/resources/thermostat-data.jsonl"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-01-01T03:00:00.000000"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputJsonData);
        assertNotNull(object);
        assertEquals(82, ((Float) object).floatValue(), .02);
    }

    @Test(expected = Test.None.class)
    public void testReadGzFileAmbientTemp() throws IOException, ParseException {
        inputGZData = new InputData("ambientTemp", new File("src/test/resources/thermostat-data.jsonl.gz"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-01-01T03:00:00.000000"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputGZData);
        assertNotNull(object);
        assertEquals(82, ((Float) object).floatValue(), .02);
    }

    @Test(expected = Test.None.class)
    public void testReadJsonFileMode() throws IOException, ParseException {
        inputJsonData = new InputData("mode", new File("src/test/resources/thermostat-data.jsonl"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-07-16T00:20:00.016703"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputJsonData);
        assertNotNull(object);
        assertEquals("OFF", ((String) object));
    }

    @Test(expected = Test.None.class)
    public void testReadGzFileMode() throws IOException, ParseException {
        inputGZData = new InputData("mode", new File("src/test/resources/thermostat-data.jsonl.gz"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-07-16T00:20:00.016703"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputGZData);
        assertNotNull(object);
        assertEquals("OFF", ((String) object));
    }

    @Test(expected = Test.None.class)
    public void testReadJsonFileHeatTemp() throws IOException, ParseException {
        inputJsonData = new InputData("heatTemp", new File("src/test/resources/thermostat-data.jsonl"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-03-10T06:59:00.003313"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputJsonData);
        assertNotNull(object);
        assertEquals(73, ((Float) object).floatValue(), .02);
    }

    @Test(expected = Test.None.class)
    public void testReadGzFileHeatTemp() throws IOException, ParseException {
        inputGZData = new InputData("heatTemp", new File("src/test/resources/thermostat-data.jsonl.gz"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-03-10T06:59:00.003313"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputGZData);
        assertNotNull(object);
        assertEquals(73, ((Float) object).floatValue(), .02);
    }

    @Test(expected = Test.None.class)
    public void testReadJsonFileSchedule() throws IOException, ParseException {
        inputJsonData = new InputData("schedule", new File("src/test/resources/thermostat-data.jsonl"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-06-10T04:27:30.001272"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputJsonData);
        assertNotNull(object);
        assertEquals(false, ((Boolean) object).booleanValue());
    }

    @Test(expected = Test.None.class)
    public void testReadGzFileSchedule() throws IOException, ParseException {
        inputGZData = new InputData("schedule", new File("src/test/resources/thermostat-data.jsonl.gz"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-06-10T04:27:30.001272"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputGZData);
        assertNotNull(object);
        assertEquals(false, ((Boolean) object).booleanValue());
    }

    @Test(expected = Test.None.class)
    public void testReadJsonFileCoolTemp() throws IOException, ParseException {
        inputJsonData = new InputData("coolTemp", new File("src/test/resources/thermostat-data.jsonl"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-08-10T05:45:30.001255"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputJsonData);
        assertNotNull(object);
        assertEquals(71, ((Float) object).floatValue(), .02);
    }

    @Test(expected = Test.None.class)
    public void testReadGzFileCoolTemp() throws IOException, ParseException {
        inputGZData = new InputData("coolTemp", new File("src/test/resources/thermostat-data.jsonl.gz"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-09-02T00:40:30.009541"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputGZData);
        assertNotNull(object);
        assertEquals(81, ((Float) object).floatValue(), .02);
    }

    @Test(expected = Test.None.class)
    public void testReadJsonFileLastAlertTs() throws IOException, ParseException {
        inputJsonData = new InputData("lastAlertTs", new File("src/test/resources/thermostat-data.jsonl"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-08-30T04:06:30.004382"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputJsonData);
        assertNotNull(object);
        assertEquals("Sat Aug 27 20:20:00 CDT 2016", ((Date) object).toString());
    }

    @Test(expected = Test.None.class)
    public void testReadGzFileLastAlertTs() throws IOException, ParseException {
        inputGZData = new InputData("lastAlertTs", new File("src/test/resources/thermostat-data.jsonl.gz"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2016-08-30T04:06:30.004382"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputGZData);
        assertNotNull(object);
        assertEquals("Sat Aug 27 20:20:00 CDT 2016", ((Date) object).toString());
    }

    @Test(expected = Test.None.class)
    public void testNotFound() throws IOException, ParseException {
        inputJsonData = new InputData("ambientTemp", new File("src/test/resources/thermostat-data.jsonl"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2017-01-01T03:00:00.000000"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputJsonData);
        assertEquals(object, null);
    }

    @Test(expected = Test.None.class)
    public void testReadGzFileAmbientTempNotFound() throws IOException, ParseException {
        inputGZData = new InputData("ambientTemp", new File("src/test/resources/thermostat-data.jsonl.gz"), new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").parse("2017-01-01T03:00:00.000000"));
        EventProcessor eventProcessor = new EventProcessor();
        Object object = eventProcessor.fetchEvent(inputGZData);
        assertEquals(object, null);
    }
}
