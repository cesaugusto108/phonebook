package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.TestContainersConfiguration;
import augusto108.ces.phonebook.model.datatypes.Name;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.Contact;
import augusto108.ces.phonebook.model.entities.Email;
import augusto108.ces.phonebook.model.entities.Telephone;
import augusto108.ces.phonebook.model.enums.Relationship;
import augusto108.ces.phonebook.model.mapper.DtoMapper;
import augusto108.ces.phonebook.repository.ContactRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.NoResultException;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class ContactControllerImplTest extends TestContainersConfiguration {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ContactRepository contactRepository;

    @Test
    void findAllContacts() throws Exception {
        mockMvc.perform(get("/api/v1/contacts").param("page", "0").param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1800")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].firstName", is("Robson")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0]._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1800")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts?page=0&size=5")))
                .andExpect(jsonPath("$._links.next.href", is("http://localhost/api/v1/contacts?page=1&size=5")))
                .andExpect(jsonPath("$.page.size", is(5)))
                .andExpect(jsonPath("$.page.totalElements", is(13)))
                .andExpect(jsonPath("$.page.totalPages", is(3)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    void findContactById() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1800"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1800")))
                .andExpect(jsonPath("$.firstName", is("Robson")))
                .andExpect(jsonPath("$.messengers[0].username", is("@rthurnham0")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1800")))
                .andExpect(jsonPath("$._links.contacts.href", is("http://localhost/api/v1/contacts")));

        mockMvc.perform(get("/api/v1/contacts/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1825"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("No result found for id: e8fd1a04-1c85-45e0-8f35-8ee8520e1825")))
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")));

        mockMvc.perform(get("/api/v1/contacts/id/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1800"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("No endpoint GET /api/v1/contacts/id/e8fd1a04-1c85-45e0-8f35-8ee8520e1800.")))
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")));
    }

    @Test
    void saveContact() throws Exception {
        final ContactDto contact = new ContactDto();
        contact.setFirstName("Samuel");
        contact.setLastName("Dantas");
        final Telephone telephone = new Telephone();
        telephone.setNumber("999606060");
        contact.getTelephones().add(telephone);
        final Email email = new Email();
        email.setUsername("samuel");
        email.setDomain("email.com");
        contact.getEmails().add(email);

        mockMvc.perform(post("/api/v1/contacts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.firstName", is("Samuel")))
                .andExpect(jsonPath("$.telephones[0].number", is("999606060")))
                .andExpect(jsonPath("$.emails[0].username", is("samuel")))
                .andExpect(jsonPath("$._links.contacts.href", is("http://localhost/api/v1/contacts")));
    }

    @Test
    void updateContact() throws Exception {
        final UUID id = UUID.fromString("e8fd1a04-1c85-45e0-8f35-8ee8520e1801");
        Name name = new Name("Rebecca", "", "Souza", "", "", "", "");
        Contact contact = contactRepository.findById(id).orElseThrow(NoResultException::new);
        contact.setName(name);
        contact.setRelationship(Relationship.FRIEND);

        mockMvc.perform(put("/api/v1/contacts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(DtoMapper.fromContactToContactDto(contact))))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.firstName", is("Rebecca")))
                .andExpect(jsonPath("$.relationship", is("FRIEND")))
                .andExpect(jsonPath("$._links.contacts.href", is("http://localhost/api/v1/contacts")));

        name = new Name("Rebeca", "Alves", "Souza", "", "", "", "");
        contact.setName(name);
        contact.setRelationship(Relationship.PARTNER);

        mockMvc.perform(put("/api/v1/contacts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(DtoMapper.fromContactToContactDto(contact))))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$.firstName", is("Rebeca")))
                .andExpect(jsonPath("$.relationship", is("PARTNER")))
                .andExpect(jsonPath("$._links.contacts.href", is("http://localhost/api/v1/contacts")));
    }

    @Test
    void deleteContact() throws Exception {
        final ContactDto contact = new ContactDto();
        contact.setFirstName("Samuel");
        contact.setLastName("Dantas");
        final String id = String.valueOf(contactRepository.save(DtoMapper.fromContactDtoToContact(contact)).getId());
        assertEquals(14, contactRepository.findAll().size());

        mockMvc.perform(delete("/api/v1/contacts/{id}", id))
                .andExpect(status().isNoContent());

        assertEquals(13, contactRepository.findAll().size());

        mockMvc.perform(delete("/api/v1/contacts/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1825"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("No result found for id: e8fd1a04-1c85-45e0-8f35-8ee8520e1825")))
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")));

        mockMvc.perform(delete("/api/v1/contacts/id/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1800"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("No endpoint DELETE /api/v1/contacts/id/e8fd1a04-1c85-45e0-8f35-8ee8520e1800.")))
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")));
    }

    @Test
    void findContactsByNameContainingIgnoreCase() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/name-search")
                        .param("search", "peR")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$._embedded.contactDtoList[1].id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1800")))
                .andExpect(jsonPath("$._embedded.contactDtoList[1].firstName", is("Robson")))
                .andExpect(jsonPath("$._embedded.contactDtoList[1]._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1800")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/name-search?page=0&size=5")))
                .andExpect(jsonPath("$.page.size", is(5)))
                .andExpect(jsonPath("$.page.totalElements", is(2)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    void findContactsByNoteContainsIgnoreCase() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/note-search")
                        .param("search", "sEmp")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1912")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].firstName", is("Ângela")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].note", is("Tortor posuere ac ut consequat semper")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0]._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1912")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/note-search?page=0&size=5")))
                .andExpect(jsonPath("$.page.size", is(5)))
                .andExpect(jsonPath("$.page.totalElements", is(1)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    void findContactsByEmailContainsIgnoreCase() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/email-search")
                        .param("search", "tH")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1805")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].firstName", is("Ana")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0]._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1805")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].emails[0].username", is("dwealthall5")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].emails[0].domain", is("yahoo.com")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/email-search?page=0&size=5")))
                .andExpect(jsonPath("$.page.size", is(5)))
                .andExpect(jsonPath("$.page.totalElements", is(4)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }
}