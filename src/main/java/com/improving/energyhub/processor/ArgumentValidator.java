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
import com.sun.media.sound.InvalidDataException;
import org.jetbrains.annotations.Nullable;

public class ArgumentValidator {

    private String dateFormat = "yyyy-MM-dd'T'HH:mm:ss";
    private List<String> fileValidExtensions = Arrays.asList("gz", "jsonl","json");
    private List<String> firstAttributeValidFields = new ArrayList<>();

    private @Nullable Exception validateFirstArgument(String firstArgument) {
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

    private void validateSecondArgument(String secondArgument) throws FileNotFoundException, IllegalArgumentException{
        Optional<String> fileExtension = Optional.ofNullable(secondArgument)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(secondArgument.lastIndexOf(".") + 1));
        if (!fileExtension.isPresent()) {
            throw new IllegalArgumentException("There is not a file extension for " + secondArgument);
        }

        if (!fileValidExtensions.contains( fileExtension.get() )) {
            throw new IllegalArgumentException("There is not a valid file extension for " + secondArgument);
        }
        File file = new File(secondArgument);
        if (!file.exists()) {
            throw new FileNotFoundException("The file " + secondArgument + " doesn't exists");
        }
    }

    // I used this validation format because the defined in the data was generating problems to be parsed.
    // yyyy-MM-dd'T'HH:mm:ss
    // At the same time, I read multiple articles related with that but this one guide me to resolve the issue
    // https://stackoverflow.com/questions/15730298/java-format-yyyy-mm-ddthhmmss-sssz-to-yyyy-mm-dd-hhmmss
    private void validateThirdArgument(String thirdArgument) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(this.dateFormat);
        Date d = sdf.parse(thirdArgument);
    }

    public void validateArguments(String []args) throws Exception {
        if(args.length!= 3){
            throw new InvalidDataException("There are expected 3 parameters, and there arrived " + args.length);
        }

        Exception exception;
        exception = validateFirstArgument(args[0]);
        if(exception != null){
            throw exception;
        }
        validateSecondArgument(args[1]);
        validateThirdArgument(args[2]);
    }
}
