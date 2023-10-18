package augusto108.ces.phonebook.services;

import augusto108.ces.phonebook.TestContainersConfiguration;
import augusto108.ces.phonebook.model.datatypes.Name;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.Contact;
import augusto108.ces.phonebook.repository.ContactRepository;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class ContactServiceImplTest extends TestContainersConfiguration {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    void findAllContacts() {
        final Page<ContactDto> contacts = contactService.findAllContacts(0, 10);
        final ContactDto contact = contacts.stream().toList().get(0);
        assertEquals(13, contacts.getTotalElements());
        assertEquals(2, contacts.getTotalPages());
        assertEquals("e8fd1a04-1c85-45e0-8f35-8ee8520e1800", contact.getId().toString());
        assertEquals("Robson", contact.getFirstName());
        assertEquals("Santos", contact.getMiddleName());
        assertEquals("Pereira", contact.getLastName());
        assertNull(contact.getNickname());
        assertNull(contact.getPhoneticFirstName());
        assertNull(contact.getPhoneticMiddleName());
        assertNull(contact.getPhoneticLastName());
    }

    @Test
    void findContactById() {
        final ContactDto contact = contactService.findContactById("e8fd1a04-1c85-45e0-8f35-8ee8520e1800");
        assertEquals("ASSISTANT", contact.getRelationship().toString());
        assertNull(contact.getCompany());
        assertNull(contact.getDate());
        assertNull(contact.getDateType());
        assertNull(contact.getCompany());
        assertNull(contact.getTitle());
        assertNull(contact.getWebsite());
        assertNull(contact.getNote());
        assertThrows(NoResultException.class, this::findByInvalidId);
    }

    @Test
    void saveContact() {
        final ContactDto dto = new ContactDto();
        dto.setFirstName("Joaquim");
        final ContactDto contact = contactService.saveContact(dto);
        final List<Contact> contacts = contactRepository.findAll();
        assertEquals(14, contacts.size());
        contactRepository.deleteById(contact.getId());
    }

    @Test
    void deleteContact() {
        final Name name = new Name("Marina", "", "", "", "", "", "");
        Contact contact = new Contact();
        contact.setName(name);
        List<Contact> contacts = contactRepository.findAll();
        assertEquals(13, contacts.size());
        contact = contactRepository.save(contact);
        contacts = contactRepository.findAll();
        assertEquals(14, contacts.size());
        contactService.deleteContact(contact.getId().toString());
        contacts = contactRepository.findAll();
        assertEquals(13, contacts.size());
    }

    private void findByInvalidId() {
        contactService.findContactById("e8fd1a04-1d85-35e0-8f25-7ee0520e1899");
    }
}