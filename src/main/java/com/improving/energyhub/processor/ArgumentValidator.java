package com.improving.energyhub.processor;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.improving.energyhub.bean.Update;

public class ArgumentValidator {

    private String dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSz";
    private List<String> fileValidExtensions = Arrays.asList("gz", "jsonl","json");
    private List<String> firstAttributeValidFields = new ArrayList<>();

    private Exception validateFirstArgument(String firstArgument) {
        getFieldList();
        if (!firstAttributeValidFields.contains(firstArgument)){
            return new Exception("The input Argument " + firstArgument  + " is not a valid Event Name");
        }
        return null;
    }

    private void getFieldList() {
        Field[] allFields = Update.class.getDeclaredFields();
        for (Field field : allFields) {
            if (Modifier.isPrivate(field.getModifiers())) {
                firstAttributeValidFields.add(field.getName());
            }
        }
    }

    private void validateSecondArgument(String secondArgument) throws FileNotFoundException {
        Optional<String> fileExtension = Optional.ofNullable(secondArgument)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(secondArgument.lastIndexOf(".") + 1));
        if (fileExtension.toString().isEmpty()) {
            throw new FileNotFoundException("There is not a file extension for " + secondArgument);
        }

        if (!fileValidExtensions.contains( fileExtension.get() )) {
            throw new FileNotFoundException("There is not a valid file extension for " + secondArgument);
        }
        File file = new File(secondArgument);
        if (!file.exists()) {
            throw new FileNotFoundException("The file " + secondArgument + " doesn't exists");
        }
    }

    private void validateThirdArgument(String thirdArgument) throws ParseException {
        ZonedDateTime zdt = ZonedDateTime.parse(thirdArgument,
                DateTimeFormatter.ofPattern(this.dateFormat, Locale.ENGLISH));
    }

    public void validateArguments(String []args) throws Exception {
        Exception exception;
        exception = validateFirstArgument(args[0]);
        if(exception != null){
            throw exception;
        }
        validateSecondArgument(args[1]);
        validateThirdArgument(args[2]);
    }
}
