package com.example.contactlist.listener;

import com.example.contactlist.Contact;
import com.example.contactlist.services.ContactService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DatabaseContactCreator {
    @Value("${app.init-contacts}")
    private boolean isInitContacts;
    private final ContactService contactService;

    @EventListener(ApplicationStartedEvent.class)
    public void createContactData() {
        if (!isInitContacts) {
            return;
        }
        log.info("Calling DatabaseContactCreator -> createContactData() -> getMockList(1)");
        List<Contact> contacts = getMockList(1);
        contactService.batchInsert(contacts);
    }

    private List<Contact> getMockList(int delay) {
        List<Contact> contacts = new ArrayList<>();
        try {
            Contact contact = new Contact(System.currentTimeMillis(), "Idaline", "MacLaughlin", "maclaughlin0@ucoz.com", "+7 (509) 421-6993");
            contacts.add(contact);
            Thread.sleep(delay);
            contact = new Contact(System.currentTimeMillis(), "Mireielle", "Burberow", "mburberow1@bloglines.com", "+7 (693) 955-6988");
            contacts.add(contact);
            Thread.sleep(delay);
            contact = new Contact(System.currentTimeMillis(), "Sergent", "Ondrus", "sondrus2@mlb.com", "+7 (747) 358-7533");
            contacts.add(contact);
            Thread.sleep(delay);
            contact = new Contact(System.currentTimeMillis(), "Mahmoud", "Wincott", "movesen3@de.vu", "+7 (538) 848-8856");
            contacts.add(contact);
            Thread.sleep(delay);
            contact = new Contact(System.currentTimeMillis(), "Иван", "Иванов", "ivaIvanoff@mail.ru", "+7(909) 999-99-99");
            contacts.add(contact);
            Thread.sleep(delay);
            contact = new Contact(System.currentTimeMillis(), "Петр", "Петров", "petyaCool@coolmail.com", "+7(907) 777-77-77");
            contacts.add(contact);
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return contacts;
    }

    public List<Contact> getInitContacts() {
        return getMockList(1);
    }
}
