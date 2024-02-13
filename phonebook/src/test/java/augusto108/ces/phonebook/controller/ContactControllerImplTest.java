package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.TestContainersConfiguration;
import augusto108.ces.phonebook.model.datatypes.Name;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.Contact;
import augusto108.ces.phonebook.model.entities.Email;
import augusto108.ces.phonebook.model.entities.Telephone;
import augusto108.ces.phonebook.model.enums.Relationship;
import augusto108.ces.phonebook.model.mapper.DtoMapper;
import augusto108.ces.phonebook.repositories.ContactRepository;
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
import org.springframework.test.web.servlet.MvcResult;

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

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;
    private final ContactRepository contactRepository;

    @Autowired
    ContactControllerImplTest(MockMvc mockMvc, ObjectMapper objectMapper, ContactRepository contactRepository) {
        this.mockMvc = mockMvc;
        this.objectMapper = objectMapper;
        this.contactRepository = contactRepository;
    }

    @Test
    void findAllContacts() throws Exception {
        mockMvc.perform(get("/api/v1/contacts").param("page", "0").param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(header().string("Used-HTTP-Method", "GET"))
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
                .andExpect(header().string("Used-HTTP-Method", "GET"))
                .andExpect(jsonPath("$.id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1800")))
                .andExpect(jsonPath("$.firstName", is("Robson")))
                .andExpect(jsonPath("$.messengers[0].username", is("@rthurnham0")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1800")))
                .andExpect(jsonPath("$._links.contacts.href", is("http://localhost/api/v1/contacts")));

        mockMvc.perform(get("/api/v1/contacts/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1825"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("No result found for id: e8fd1a04-1c85-45e0-8f35-8ee8520e1825")))
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")))
                .andExpect(jsonPath("$.statusCode", is(404)));


        mockMvc.perform(get("/api/v1/contacts/id/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1800"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("No endpoint GET /api/v1/contacts/id/e8fd1a04-1c85-45e0-8f35-8ee8520e1800.")))
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")))
                .andExpect(jsonPath("$.statusCode", is(404)));

        mockMvc.perform(get("/api/v1/contacts/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e180z"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("Incorrect or malformed UUID string: Error at index 11 in: \"8ee8520e180z\"")))
                .andExpect(jsonPath("$.httpStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.statusCode", is(400)));
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

        MvcResult result = mockMvc.perform(post("/api/v1/contacts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(header().string("Used-HTTP-Method", "POST"))
                .andExpect(jsonPath("$.firstName", is("Samuel")))
                .andExpect(jsonPath("$.telephones[0].number", is("999606060")))
                .andExpect(jsonPath("$.emails[0].username", is("samuel")))
                .andExpect(jsonPath("$._links.contacts.href", is("http://localhost/api/v1/contacts")))
                .andReturn();

        final String id = result.getResponse().getContentAsString().substring(7, 43);
        assertEquals(result.getResponse().getHeader("Location"), "http://localhost/api/v1/contacts/" + id);
    }

    @Test
    void updateContact() throws Exception {
        final UUID id = UUID.fromString("e8fd1a04-1c85-45e0-8f35-8ee8520e1801");
        Name name = new Name("Rebecca", "", "Souza", "", "", "", "");
        Contact contact = contactRepository.findById(id).orElseThrow(NoResultException::new);
        contact.setName(name);
        contact.setRelationship(Relationship.FRIEND);

        mockMvc.perform(put("/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1801")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(DtoMapper.fromContactToContactDto(contact))))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(header().string("Used-HTTP-Method", "PUT"))
                .andExpect(jsonPath("$.firstName", is("Rebecca")))
                .andExpect(jsonPath("$.relationship", is("FRIEND")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1801")))
                .andExpect(jsonPath("$._links.contacts.href", is("http://localhost/api/v1/contacts")));

        name = new Name("Rebeca", "Alves", "Souza", "", "", "", "");
        contact.setName(name);
        contact.setRelationship(Relationship.PARTNER);

        mockMvc.perform(put("/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1801")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(DtoMapper.fromContactToContactDto(contact))))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(header().string("Used-HTTP-Method", "PUT"))
                .andExpect(jsonPath("$.firstName", is("Rebeca")))
                .andExpect(jsonPath("$.relationship", is("PARTNER")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1801")))
                .andExpect(jsonPath("$._links.contacts.href", is("http://localhost/api/v1/contacts")));

        mockMvc.perform(put("/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1802")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(DtoMapper.fromContactToContactDto(contact))))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("ID in body does not match ID in URL")))
                .andExpect(jsonPath("$.httpStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.statusCode", is(400)));

        mockMvc.perform(put("/api/v1/contacts/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e180z")
                        .contentType("application/json")
                        .content("{\"id\": \"e8fd1a04-1c85-45e0-8f35-8ee8520e180z\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("JSON parse error: Cannot deserialize value of type `java.util.UUID` from String \"e8fd1a04-1c85-45e0-8f35-8ee8520e180z\": Non-hex character 'z' (value 0x7a), not valid for UUID String")))
                .andExpect(jsonPath("$.httpStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.statusCode", is(400)));
    }

    @Test
    void deleteContact() throws Exception {
        final ContactDto contact = new ContactDto();
        contact.setFirstName("Samuel");
        contact.setLastName("Dantas");
        final String id = String.valueOf(contactRepository.save(DtoMapper.fromContactDtoToContact(contact)).getId());
        assertEquals(14, contactRepository.findAll().size());

        mockMvc.perform(delete("/api/v1/contacts/{id}", id))
                .andExpect(status().isNoContent())
                .andExpect(header().string("Used-HTTP-Method", "DELETE"));

        assertEquals(13, contactRepository.findAll().size());

        mockMvc.perform(delete("/api/v1/contacts/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1825"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("No result found for id: e8fd1a04-1c85-45e0-8f35-8ee8520e1825")))
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")))
                .andExpect(jsonPath("$.statusCode", is(404)));

        mockMvc.perform(delete("/api/v1/contacts/id/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e1800"))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("No endpoint DELETE /api/v1/contacts/id/e8fd1a04-1c85-45e0-8f35-8ee8520e1800.")))
                .andExpect(jsonPath("$.httpStatus", is("NOT_FOUND")))
                .andExpect(jsonPath("$.statusCode", is(404)));

        mockMvc.perform(delete("/api/v1/contacts/{id}", "e8fd1a04-1c85-45e0-8f35-8ee8520e180z"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.message", is("Incorrect or malformed UUID string: Error at index 11 in: \"8ee8520e180z\"")))
                .andExpect(jsonPath("$.httpStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.statusCode", is(400)));
    }

    @Test
    void findContactsByNameContainingIgnoreCase() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/name-search")
                        .param("search", "peR")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(header().string("Used-HTTP-Method", "GET"))
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
    void findContactsByWebsiteContainsIgnoreCase() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/website-search")
                        .param("search", "dev4")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(header().string("Used-HTTP-Method", "GET"))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1910")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].firstName", is("Charles")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0]._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1910")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/website-search?page=0&size=5")))
                .andExpect(jsonPath("$.page.size", is(5)))
                .andExpect(jsonPath("$.page.totalElements", is(1)))
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
                .andExpect(header().string("Used-HTTP-Method", "GET"))
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
    void findContactsByTelephone() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/telephone-search")
                        .param("search", "9833")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(header().string("Used-HTTP-Method", "GET"))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1807")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].firstName", is("Josefa")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].telephones.size()", is(2)))
                .andExpect(jsonPath("$._embedded.contactDtoList[0]._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1807")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/telephone-search?page=0&size=5")))
                .andExpect(jsonPath("$.page.size", is(5)))
                .andExpect(jsonPath("$.page.totalElements", is(1)))
                .andExpect(jsonPath("$.page.totalPages", is(1)))
                .andExpect(jsonPath("$.page.number", is(0)));
    }

    @Test
    void findContactsByAddress() throws Exception {
        mockMvc.perform(get("/api/v1/contacts/address-search")
                        .param("search", "sEnnA")
                        .param("page", "0")
                        .param("size", "5"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/hal+json"))
                .andExpect(header().string("Used-HTTP-Method", "GET"))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].id", is("e8fd1a04-1c85-45e0-8f35-8ee8520e1803")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].firstName", is("Alberto")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0].addresses[0].street", is("Ayrton Senna")))
                .andExpect(jsonPath("$._embedded.contactDtoList[0]._links.self.href", is("http://localhost/api/v1/contacts/e8fd1a04-1c85-45e0-8f35-8ee8520e1803")))
                .andExpect(jsonPath("$._links.self.href", is("http://localhost/api/v1/contacts/address-search?page=0&size=5")))
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
                .andExpect(header().string("Used-HTTP-Method", "GET"))
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