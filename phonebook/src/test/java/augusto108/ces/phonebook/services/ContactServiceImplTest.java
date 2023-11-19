package augusto108.ces.phonebook.services;

import augusto108.ces.phonebook.TestContainersConfiguration;
import augusto108.ces.phonebook.model.datatypes.City;
import augusto108.ces.phonebook.model.datatypes.Country;
import augusto108.ces.phonebook.model.datatypes.Name;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.*;
import augusto108.ces.phonebook.model.enums.InstantMessengerType;
import augusto108.ces.phonebook.model.mapper.DtoMapper;
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
import java.util.UUID;

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

        final Address address = new Address();
        address.setStreet("Ivo do Prado");
        address.setNumber("823");
        address.setCity(new City("Aracaju", "Sergipe", new Country("Brasil")));
        dto.getAddresses().add(address);

        final Telephone telephone = new Telephone();
        telephone.setNumber("998102510");
        dto.getTelephones().add(telephone);

        final Email email = new Email();
        email.setUsername("joaquim");
        email.setDomain("email.com");
        dto.getEmails().add(email);

        final InstantMessenger messenger = new InstantMessenger();
        messenger.setUsername("@joaquim");
        messenger.setImType(InstantMessengerType.INSTAGRAM);
        dto.getMessengers().add(messenger);

        final ContactDto contact = contactService.saveOrUpdateContact(dto);
        final List<Contact> contacts = contactRepository.findAll();
        assertEquals(14, contacts.size());
        contactRepository.deleteById(contact.getId());
    }

    @Test
    void updateContact() {
        Contact contact = contactRepository
                .findById(UUID.fromString("e8fd1a04-1c85-45e0-8f35-8ee8520e1800"))
                .orElseThrow(NoResultException::new);
        assertEquals("Robson", contact.getName().firstName()); // checks object in db is the one expected

        ContactDto dto = DtoMapper.fromContactToContactDto(contact);
        assertEquals("e8fd1a04-1c85-45e0-8f35-8ee8520e1800", dto.getId().toString()); // checks id is the same

        dto.setFirstName("Joana");
        dto = contactService.saveOrUpdateContact(dto);
        assertEquals("Joana", dto.getFirstName()); // checks the obj returned from the method has name set before

        contact = contactRepository
                .findById(UUID.fromString("e8fd1a04-1c85-45e0-8f35-8ee8520e1800"))
                .orElseThrow(NoResultException::new);
        assertEquals("Joana", contact.getName().firstName()); // checks object in db has updated name

        List<Contact> contacts = contactRepository.findAll();
        assertEquals(13, contacts.size()); // checks number of objs stays the same

        dto.setFirstName("Robson"); // sets objs name back to original
        dto = contactService.saveOrUpdateContact(dto);
        assertEquals("Robson", dto.getFirstName()); // checks the obj returned from the method has name set before

        contact = contactRepository
                .findById(UUID.fromString("e8fd1a04-1c85-45e0-8f35-8ee8520e1800"))
                .orElseThrow(NoResultException::new);
        assertEquals("Robson", contact.getName().firstName()); // checks object in db has updated name

        contacts = contactRepository.findAll();
        assertEquals(13, contacts.size()); // checks number of objs stays the same
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
        assertThrows(NoResultException.class, () -> contactService.deleteContact("e8fd1a04-1d85-35e0-8f25-7ee0520e1818"));
    }

    @Test
    void findContactsByNameContainingIgnoreCase() {
        final Page<ContactDto> contacts = contactService.findContactsByNameContainsIgnoreCase("peR", 0, 10);
        final ContactDto alberto = contacts.stream().toList().get(0);
        final ContactDto robson = contacts.stream().toList().get(1);
        assertEquals(2, contacts.getTotalElements());
        assertEquals(1, contacts.getTotalPages());
        assertEquals("e8fd1a04-1c85-45e0-8f35-8ee8520e1803", alberto.getId().toString());
        assertEquals("Alberto", alberto.getFirstName());
        assertNull(alberto.getMiddleName());
        assertEquals("Pereira", alberto.getLastName());
        assertEquals("e8fd1a04-1c85-45e0-8f35-8ee8520e1800", robson.getId().toString());
        assertEquals("Robson", robson.getFirstName());
        assertEquals("Santos", robson.getMiddleName());
        assertEquals("Pereira", robson.getLastName());
    }

    @Test
    void findContactsByNoteContainsIgnoreCase() {
        final Page<ContactDto> contacts = contactService.findContactsByNoteContainsIgnoreCase("sEmp", 0, 10);
        final ContactDto angela = contacts.stream().toList().get(0);
        assertEquals(1, contacts.getTotalElements());
        assertEquals(1, contacts.getTotalPages());
        assertEquals("e8fd1a04-1c85-45e0-8f35-8ee8520e1912", angela.getId().toString());
        assertEquals("Ângela", angela.getFirstName());
        assertEquals("Martins", angela.getMiddleName());
        assertEquals("Silva", angela.getLastName());
    }

    @Test
    void findContactsByEmailsContainsIgnoreCase() {
        final Page<ContactDto> contacts = contactService.findContactsByEmailsContainsIgnoreCase("tH", 0, 10);
        final ContactDto ana = contacts.stream().toList().get(0);
        assertEquals(4, contacts.getTotalElements());
        assertEquals(1, contacts.getTotalPages());
        assertEquals("e8fd1a04-1c85-45e0-8f35-8ee8520e1805", ana.getId().toString());
        assertEquals("Ana", ana.getFirstName());
        assertEquals("Santos", ana.getMiddleName());
        assertEquals("Silva", ana.getLastName());
    }

    private void findByInvalidId() {
        contactService.findContactById("e8fd1a04-1d85-35e0-8f25-7ee0520e1899");
    }
}