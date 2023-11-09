package com.example.contactlist;

import com.example.contactlist.services.ContactService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ContactController {
    private Contact contact = new Contact();    // используется при добавлении нового контакта
    private boolean isCreateContact = false;    // отображение ДОБАВЛЕНИЯ
    private boolean isUpdateContact = false;    // отображение РЕДАКТИРОВАНИЯ
    private String name = "";                   // используется в fieldsContact.html
    private Long idForUpdate;                   // id для редактирования контакта

    private final ContactService contactService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contact", contact);
        model.addAttribute("contacts", contactService.findAll());
        model.addAttribute("isCreateContact", isCreateContact);
        model.addAttribute("isUpdateContact", isUpdateContact);
        model.addAttribute("name", name);
        return "index";
    }

    @GetMapping("/contact/create")
    public String createContact() {
        contact = new Contact();
        isCreateContact = true;
        isUpdateContact = false;
        name = "Add new contact";
        log.debug("Call ContactController {} ", "create new contact");
        return "redirect:/";
    }

    @PostMapping("/contact/add")
    public String addContact(@ModelAttribute Contact contact) {
        isCreateContact = false;
        name = "";
        log.debug("Call ContactController {} with ID: {}", "added contact", contact.getId());
        contactService.save(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/update/{id}")
    public String updateContact(@PathVariable Long id) {
        idForUpdate = id;
        isCreateContact = false;
        isUpdateContact = true;
        name = "Update contact";
        log.debug("Call ContactController {} with ID: {}", "for update contact", id);
        contact = contactService.findById(id);
        return "redirect:/";
    }

    @PostMapping("/contact/update")
    public String saveContact(@ModelAttribute Contact contact) {
        contact.setId(idForUpdate);
        isUpdateContact = false;
        name = "";
        log.debug("Call ContactController {} with ID: {}", "saved contact", contact.getId());
        contactService.update(contact);
        return "redirect:/";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(@PathVariable Long id) {
        log.debug("Call ContactController {} with ID: {}", "delete Contact", id);
        contactService.deleteById(id);
        return "redirect:/";
    }
}
