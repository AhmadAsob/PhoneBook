package com.example.phonebook.Repository;

import com.example.phonebook.Entity.PhoneBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhoneBookRepository extends JpaRepository<PhoneBook, Long> {

    @Query(value = """
            select max(id) from PhoneBook;
            """, nativeQuery = true)
    Long getMaxId();

    @Query(value = "select id from phonebook where id = :id", nativeQuery = true)
    Long getId(@Param("id") Long id);
}