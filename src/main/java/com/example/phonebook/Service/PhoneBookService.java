package com.example.phonebook.Service;

import com.example.phonebook.Dto.insertPhoneBookDto;
import com.example.phonebook.Dto.responseDataObj;
import com.example.phonebook.Dto.responseOutput;
import com.example.phonebook.Entity.PhoneBook;
import com.example.phonebook.Repository.PhoneBookRepository;
import com.example.phonebook.Validator.DateValidator;
import com.example.phonebook.Validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Service
public class PhoneBookService {

    @Autowired
    private PhoneBookRepository phoneBookRepository;

    private HttpStatus StatusResponse;

    public ResponseEntity getAllPhoneNumber() {

        responseDataObj response = new responseDataObj();

        try {
            response.setRespHttpCode("200");
            response.setRespHttpMessage("Sucessfully");
            response.setData(phoneBookRepository.findAll());
            StatusResponse = HttpStatus.OK;

        } catch (Exception e){

            response.setRespHttpCode("400");
            response.setRespHttpMessage(e.getMessage());
            StatusResponse = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(response, StatusResponse);
    }

    public ResponseEntity getPhoneBookById(Long id) {

        responseDataObj response = new responseDataObj();

        try {
            if(phoneBookRepository.getId(id) == null){
                response.setRespHttpCode("400");
                response.setRespHttpMessage("Id Yang Dimasukkan Salah");
                StatusResponse = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(response, StatusResponse);
            } else {
                response.setRespHttpCode("200");
                response.setRespHttpMessage("Sucessfully");
                response.setData(phoneBookRepository.findById(id));
                StatusResponse = HttpStatus.OK;
            }

        } catch (Exception e){

            response.setRespHttpCode("400");
            response.setRespHttpMessage(e.getMessage());
            StatusResponse = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(response, StatusResponse);
    }

    public ResponseEntity insertPhoneNumber(insertPhoneBookDto insertPhoneBook){

        responseOutput response = new responseOutput();

        try {

            PhoneBook phoneBook = new PhoneBook();
            phoneBook.setId(phoneBookRepository.getMaxId() + 1);
            phoneBook.setName(insertPhoneBook.getName());
            phoneBook.setAddress(insertPhoneBook.getAddress());
            phoneBook.setEmail(insertPhoneBook.getEmail());
            if (!EmailValidator.isValid(insertPhoneBook.getEmail())) {
                response.setRespHttpCode("400");
                response.setRespHttpMessage("Format Email Tidak Sesuai");
                StatusResponse = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(response, StatusResponse);
            }
            phoneBook.setPhoneNo(insertPhoneBook.getPhoneNo());
            if (DateValidator.isValid(insertPhoneBook.getDateOfBirth())) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                    java.util.Date parsedDate = sdf.parse(insertPhoneBook.getDateOfBirth());
                    phoneBook.setDateOfBirth(new java.sql.Date(parsedDate.getTime()));
                    phoneBookRepository.save(phoneBook);
                } catch (ParseException e) {
                    response.setRespHttpCode("400");
                    response.setRespHttpMessage("Format Tanggal harus YYYY/MM/DD");
                    StatusResponse = HttpStatus.BAD_REQUEST;
                }
            } else {
                response.setRespHttpCode("400");
                response.setRespHttpMessage("Format Tanggal harus YYYY/MM/DD");
                StatusResponse = HttpStatus.BAD_REQUEST;
            }
            response.setRespHttpCode("200");
            response.setRespHttpMessage("Successfully");
            StatusResponse = HttpStatus.OK;
        } catch (Exception e){

            response.setRespHttpCode("400");
            response.setRespHttpMessage(e.getMessage());
            StatusResponse = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, StatusResponse);
    }

    public ResponseEntity updatePhoneNumber(Long id, insertPhoneBookDto insertPhoneBook){

        responseOutput response = new responseOutput();

        try {

            System.out.println(phoneBookRepository.getId(id));

            if(phoneBookRepository.getId(id) == null){
                response.setRespHttpCode("400");
                response.setRespHttpMessage("Id Yang Dimasukkan Salah");
                StatusResponse = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(response, StatusResponse);
            } else {
                PhoneBook phoneBook = phoneBookRepository.getById(id);
                if (insertPhoneBook.getName() == null || insertPhoneBook.getName().isEmpty()){
                    phoneBook.setName(phoneBook.getName());
                } else {
                    phoneBook.setName(insertPhoneBook.getName());
                }
                if (insertPhoneBook.getAddress() == null || insertPhoneBook.getAddress().isEmpty()){
                    phoneBook.setAddress(phoneBook.getAddress());
                } else {
                    phoneBook.setAddress(insertPhoneBook.getAddress());
                }
                if (insertPhoneBook.getEmail() == null || insertPhoneBook.getEmail().isEmpty()){
                    phoneBook.setEmail(phoneBook.getEmail());
                } else {
                    phoneBook.setEmail(insertPhoneBook.getEmail());
                    if (!EmailValidator.isValid(insertPhoneBook.getEmail())) {
                        response.setRespHttpCode("400");
                        response.setRespHttpMessage("Format Email Tidak Sesuai");
                        StatusResponse = HttpStatus.BAD_REQUEST;
                        return new ResponseEntity<>(response, StatusResponse);
                    }
                }
                if (insertPhoneBook.getPhoneNo() == null || insertPhoneBook.getPhoneNo().isEmpty()){
                    phoneBook.setPhoneNo(phoneBook.getPhoneNo());
                } else {
                    phoneBook.setPhoneNo(insertPhoneBook.getPhoneNo());
                }
                if (insertPhoneBook.getDateOfBirth() == null || insertPhoneBook.getDateOfBirth().isEmpty()){
                    phoneBook.setDateOfBirth(phoneBook.getDateOfBirth());
                } else {
                    if (DateValidator.isValid(insertPhoneBook.getDateOfBirth())) {
                        try {
                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                            java.util.Date parsedDate = sdf.parse(insertPhoneBook.getDateOfBirth());
                            phoneBook.setDateOfBirth(new java.sql.Date(parsedDate.getTime()));

                        } catch (ParseException e) {
                            response.setRespHttpCode("400");
                            response.setRespHttpMessage("Format Tanggal harus YYYY/MM/DD");
                            StatusResponse = HttpStatus.BAD_REQUEST;
                        }
                    } else {
                        response.setRespHttpCode("400");
                        response.setRespHttpMessage("Format Tanggal harus YYYY/MM/DD");
                        StatusResponse = HttpStatus.BAD_REQUEST;
                    }
                }
                phoneBookRepository.save(phoneBook);
            }
            response.setRespHttpCode("200");
            response.setRespHttpMessage("Successfully");
            StatusResponse = HttpStatus.OK;
        } catch (Exception e){
            response.setRespHttpCode("400");
            response.setRespHttpMessage(e.getMessage());
            StatusResponse = HttpStatus.BAD_REQUEST;
        }
        return new ResponseEntity<>(response, StatusResponse);
    }

    public ResponseEntity deletePhoneBook(Long id){

        responseOutput response = new responseOutput();

        try {

            System.out.println(phoneBookRepository.getId(id));

            if(phoneBookRepository.getId(id) == null){
                response.setRespHttpCode("400");
                response.setRespHttpMessage("Id Yang Dimasukkan Salah");
                StatusResponse = HttpStatus.BAD_REQUEST;
                return new ResponseEntity<>(response, StatusResponse);
            } else {
                phoneBookRepository.deleteById(id);
                response.setRespHttpCode("200");
                response.setRespHttpMessage("Successfully");
                StatusResponse = HttpStatus.OK;
            }

        } catch (Exception e){
            response.setRespHttpCode("400");
            response.setRespHttpMessage(e.getMessage());
            StatusResponse = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity(response,  StatusResponse);
    }

}
