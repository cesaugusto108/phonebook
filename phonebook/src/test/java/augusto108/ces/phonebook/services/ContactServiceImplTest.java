package augusto108.ces.phonebook.services;

import augusto108.ces.phonebook.TestContainersConfiguration;
import augusto108.ces.phonebook.model.dto.ContactDto;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class ContactServiceImplTest extends TestContainersConfiguration {

    @Autowired
    private ContactService contactService;

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
    }
}