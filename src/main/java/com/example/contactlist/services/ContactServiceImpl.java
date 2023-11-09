package com.example.contactlist.services;

import com.example.contactlist.Contact;
import com.example.contactlist.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Override
    public List<Contact> findAll() {
        log.debug("Call {} in ContactServiceImpl", "findAll");
        return contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        log.debug("** Call {} in ContactServiceImpl ID: {}", "findById", id);
        return contactRepository.findById(id).orElse(null);
    }

    @Override
    public Contact save(Contact task) {
        log.debug("Call {} in ContactServiceImpl", "save");
        return contactRepository.save(task);
    }

    @Override
    public void saveAll(List<Contact> contacts) {
        log.debug("Call {} in ContactServiceImpl", "saveAll");
    }

    @Override
    public Contact update(Contact task) {
        log.debug("Call {} in ContactServiceImpl", "update");
        return contactRepository.update(task);
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call {} in ContactServiceImpl", "deleteById");
        contactRepository.deleteById(id);
    }

    @Override
    public void batchInsert(List<Contact> tasks) {
        log.debug("Call {} in ContactServiceImpl", "batchInsert");
        contactRepository.batchInsert(tasks);
    }

    @Override
    public void updateById(Long id) {
        log.debug("Call {} in ContactServiceImpl", "updateById");
        contactRepository.updateById(id);
    }
}
