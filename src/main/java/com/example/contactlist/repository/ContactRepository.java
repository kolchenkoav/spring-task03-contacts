package com.example.contactlist.repository;

import com.example.contactlist.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    List<Contact> findAll();

    Optional<Contact> findById(Long id);

    Contact save(Contact task);

    void saveAll(List<Contact> contacts);

    Contact update(Contact task);

    void deleteById(Long id);

    void batchInsert(List<Contact> tasks);

    void updateById(Long id);
}
