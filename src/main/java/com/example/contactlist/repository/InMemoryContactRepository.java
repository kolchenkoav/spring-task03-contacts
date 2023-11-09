package com.example.contactlist.repository;

import com.example.contactlist.Contact;
import com.example.contactlist.listener.DatabaseContactCreator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
//@Primary
public class InMemoryContactRepository implements ContactRepository {

    private final List<Contact> contacts = new ArrayList<>();

    @Override
    public List<Contact> findAll() {
        log.debug("Call {} in InMemoryContactRepository", "findAll");
        return contacts;
    }

    @Override
    public Optional<Contact> findById(Long id) {
        log.debug("### Call {} in InMemoryContactRepository. id is: {}", "findById", id);
        return contacts.stream()
                .filter(contact -> contact.getId().equals(id))
                .findFirst();
    }

    @Override
    public Contact save(Contact contact) {
        log.debug("Call {} in InMemoryContactRepository. Contact is: {}", "save", contact);
        contact.setId(System.currentTimeMillis());
        contacts.add(contact);

        return contact;
    }

    @Override
    public void saveAll(List<Contact> newContacts) {
        log.debug("Call {} in InMemoryContactRepository. Count contacts: {}", "saveAll", newContacts.size());
        contacts.addAll(newContacts);
    }

    @Override
    public Contact update(Contact changeableContact) {
        log.debug("*** Call {} in InMemoryContactRepository. Contact is: {}", "update", changeableContact);
        Contact existedContact = findById(changeableContact.getId()).orElse(null);
        if (existedContact != null) {
            existedContact.setId(changeableContact.getId());
            existedContact.setFirstName(changeableContact.getFirstName());
            existedContact.setLastName(changeableContact.getLastName());
            existedContact.setEmail(changeableContact.getEmail());
            existedContact.setPhone(changeableContact.getPhone());
        }
        return existedContact;
    }

    @Override
    public void deleteById(Long id) {
        log.debug("Call {} in InMemoryContactRepository. id is: {}", "deleteById", id);

        findById(id).ifPresent(contacts::remove);
    }

    @Override
    public void batchInsert(List<Contact> list) {
        contacts.addAll(list);
    }

    @Override
    public void updateById(Long id) {
        log.debug("Call {} in InMemoryContactRepository. id is: {}", "updateById", id);

    }
}
