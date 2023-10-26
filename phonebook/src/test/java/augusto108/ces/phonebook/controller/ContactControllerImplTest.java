package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.TestContainersConfiguration;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class ContactControllerImplTest extends TestContainersConfiguration {

    @Autowired
    private MockMvc mockMvc;

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
    }
}