package com.example.phonebook.Validator;

public class EmailValidator {

    public static boolean isValid(String email) {
        return email.contains("@");
    }
}
