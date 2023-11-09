package com.example.contactlist.services;

import com.example.contactlist.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();

    Contact findById(Long id);

    Contact save(Contact task);

    void saveAll(List<Contact> contacts);

    Contact update(Contact task);

    void deleteById(Long id);

    void batchInsert(List<Contact> tasks);

    void updateById(Long id);
}
