package com.example.phonebook.Validator;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateValidator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

    static {
        dateFormat.setLenient(false);
    }

    public static boolean isValid(String dateStr) {
        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
