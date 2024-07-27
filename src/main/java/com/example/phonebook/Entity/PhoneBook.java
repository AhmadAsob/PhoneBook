package com.example.phonebook.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity(name = "phonebook")
@Table(name = "phonebook")
public class PhoneBook {

    private Long id;
    private String name;
    private String phoneNo;
    private String address;
    private String email;
    private Date dateOfBirth;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId(){return id;}

    public void setId(Long id){this.id = id;}

    @Column(name = "name", nullable = false)
    public String getName(){return name;}

    public void setName(String name){this.name = name;}

    @Column(name = "phone_no", nullable = false)
    public String getPhoneNo(){return phoneNo;}

    public void setPhoneNo(String phoneNo) {this.phoneNo = phoneNo;}

    @Column(name = "address",nullable = false)
    public String getAddress(){return address;}

    public void setAddress(String address) {this.address = address;}

    @Column(name = "email", nullable = false)
    public String getEmail(){return email;}

    public void setEmail(String email) {this.email = email;}

    @Column(name = "date_of_birth", nullable = false)
    public Date getDateOfBirth(){return dateOfBirth;}

    public void setDateOfBirth(Date dateOfBirth) {this.dateOfBirth = dateOfBirth;}
}
