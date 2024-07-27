package com.example.phonebook.Dto;

import lombok.Data;

@Data
public class insertPhoneBookDto {
    private String name;
    private String phoneNo;
    private String address;
    private String email;
    private String dateOfBirth;
}
