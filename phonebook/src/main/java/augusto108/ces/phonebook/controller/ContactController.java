package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.model.dto.ContactDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ContactController {

    @GetMapping(value = {"", "/"}, produces = "application/hal+json")
    ResponseEntity<PagedModel<EntityModel<ContactDto>>> findAllContacts(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size
    );

    @GetMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<EntityModel<ContactDto>> findContactById(@PathVariable("id") String id);

    @RequestMapping(
            value = {"", "/"},
            method = {RequestMethod.POST, RequestMethod.PUT},
            consumes = "application/json",
            produces = "application/hal+json")
    ResponseEntity<EntityModel<ContactDto>> saveOrUpdateContact(@RequestBody ContactDto dto, HttpServletRequest request);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteContact(@PathVariable("id") String id);
}
