package com.example.phonebook.Controller;

import com.example.phonebook.Dto.insertPhoneBookDto;
import com.example.phonebook.Service.PhoneBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phoneBook")
public class PhoneBookController {
    @Autowired
    private PhoneBookService phoneBookService;

    @GetMapping("/getAllPhoneBook")
    public ResponseEntity getAllPhoneNo(){return phoneBookService.getAllPhoneNumber();}

    @GetMapping("/getPhoneBookById/{id}")
    public ResponseEntity getPhoneBookById(@PathVariable Long id){return phoneBookService.getPhoneBookById(id);}

    @PutMapping("/updatePhoneBook/{id}")
    public ResponseEntity updatePhoneBook(@PathVariable Long id, @RequestBody insertPhoneBookDto insertPhoneBookDto)
    {return phoneBookService.updatePhoneNumber(id, insertPhoneBookDto);}

    @DeleteMapping("/deletePhoneBook/{id}")
    public ResponseEntity deletePhoneBook(@PathVariable Long id){return phoneBookService.deletePhoneBook(id);}

    @PostMapping("/insertPhoneBook")
    public ResponseEntity insertPhoneBook(@RequestBody insertPhoneBookDto insertPhoneBook){return phoneBookService.insertPhoneNumber(insertPhoneBook);}
}
