package com.improving.energyhub.processor;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.improving.energyhub.SpringBootConsoleApplication;
import com.improving.energyhub.bean.Event;
import com.improving.energyhub.bean.InputData;
import com.improving.energyhub.bean.Update;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.Objects;
import java.util.zip.GZIPInputStream;
import java.lang.reflect.Field;

public class EventProcessor {
    private static Logger LOG = LoggerFactory
            .getLogger(SpringBootConsoleApplication.class);

    //TODO: This method should to return a value with the respective type instead an object
    public Object fetchEvent(InputData inputData) throws IOException {

        if (inputData.getJsonFile().getName().contains("gz")) {
            inputData.setJsonFile(decompressGzip(inputData.getJsonFile()));
        }
        Object object = this.readJsonFile(inputData);

        return object;
    }

    private Object readJsonFile(InputData inputData) {
        BufferedReader br = null;
        JSONParser parser = new JSONParser();

        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(inputData.getJsonFile().getAbsolutePath()));
            ObjectMapper objectMapper = new ObjectMapper();
            Event currentEvent = null;
            while ((sCurrentLine = br.readLine()) != null) {
                //LOG.info("Record:\t" + sCurrentLine);
                Event newEvent = objectMapper.readValue(sCurrentLine, Event.class);
                currentEvent = getDifference(currentEvent, newEvent);
                Object object = fetchEventFromData(currentEvent,inputData.getEventName(),inputData.getDateFilter());
                if (object != null){
                    return object;
                }
            }

        } catch (IOException | IllegalAccessException | NoSuchFieldException e) {
            LOG.error(e.toString());
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                LOG.error(ex.toString());
            }
        }
        return null;
    }

    private File decompressGzip(File jsonFile) throws IOException {
        Path source = jsonFile.toPath();
        String tempDir = Files.createTempDirectory("energyHubCodeChallenge").toFile().getAbsolutePath();
        Path target = new File(tempDir + jsonFile.getName() + ".json").toPath();

        try (GZIPInputStream gis = new GZIPInputStream(
                new FileInputStream(source.toFile()));
             FileOutputStream fos = new FileOutputStream(target.toFile())) {

            // copy GZIPInputStream to FileOutputStream
            byte[] buffer = new byte[1024];
            int len;
            while ((len = gis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }

        }
        return target.toFile();
    }

    //Note: I did this method manually because the guava and apache comparator methods were more difficult to implement for me.
    // I don't have a fresh use of them, so this version is more complex and difficult to support but I guess that it is working.
    //TODO: this part should to be refactorized because each new attributer to the class requires a new if. I need to implement refelction here too
    private Event getDifference(Event currentEvent, Event newEvent) throws IllegalAccessException {
        if (currentEvent == null) {
            return (Event) newEvent;
        }
        currentEvent.setUpdateTime(newEvent.getUpdateTime());
        if (newEvent.getUpdate().getAmbientTemp() != null && currentEvent.getUpdate().getAmbientTemp() != newEvent.getUpdate().getAmbientTemp()) {
            currentEvent.getUpdate().setAmbientTemp(newEvent.getUpdate().getAmbientTemp());
        }
        if (newEvent.getUpdate().getMode() != null && currentEvent.getUpdate().getMode() != newEvent.getUpdate().getMode()) {
            currentEvent.getUpdate().setMode(newEvent.getUpdate().getMode());
        }
        if (newEvent.getUpdate().getHeatTemp() != null && currentEvent.getUpdate().getHeatTemp() != newEvent.getUpdate().getHeatTemp()) {
            currentEvent.getUpdate().setHeatTemp(newEvent.getUpdate().getHeatTemp());
        }
        if (newEvent.getUpdate().getSchedule() != null && currentEvent.getUpdate().getSchedule() != newEvent.getUpdate().getSchedule()) {
            currentEvent.getUpdate().setSchedule(newEvent.getUpdate().getSchedule());
        }
        if (newEvent.getUpdate().getCoolTemp() != null && currentEvent.getUpdate().getCoolTemp() != newEvent.getUpdate().getCoolTemp()) {
            currentEvent.getUpdate().setCoolTemp(newEvent.getUpdate().getCoolTemp());
        }
        if (newEvent.getUpdate().getLastAlertTs() != null && currentEvent.getUpdate().getLastAlertTs() != newEvent.getUpdate().getLastAlertTs()) {
            currentEvent.getUpdate().setLastAlertTs(newEvent.getUpdate().getLastAlertTs());
        }
        return currentEvent;
    }

    //TODO: This method should to return a value with the respective type instead an object
    // but I didn't have the enough time to do it
    private Object fetchEventFromData(Event currentEvent, String eventName, Date rangeDate) throws IllegalAccessException, NoSuchFieldException {
        if( rangeDate.before(currentEvent.getUpdateTime() ) ){
            Field field = Update.class.getDeclaredField(eventName);
            field.setAccessible(true);
            Object value = field.get( currentEvent.getUpdate() );
            if( value != null ){
                return value;
            }
        }
        return null;
    }
}

