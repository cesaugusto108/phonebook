package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.model.dto.ContactDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Application endpoints", description = "Phonebook application endpoints")
public interface ContactController {

    @Operation(summary = "get all contacts from database - default page = 0, default page size = 10")
    @GetMapping(value = {""}, produces = "application/hal+json")
    ResponseEntity<PagedModel<EntityModel<ContactDto>>> findAllContacts(
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size
    );

    @Operation(summary = "get contact - find contact by id")
    @GetMapping(value = "/{id}", produces = "application/hal+json")
    ResponseEntity<EntityModel<ContactDto>> findContactById(@PathVariable("id") String id);

    @Operation(summary = "save contact")
    @PostMapping(value = {""}, consumes = "application/json", produces = "application/hal+json")
    ResponseEntity<EntityModel<ContactDto>> saveContact(@RequestBody ContactDto dto, HttpServletRequest request);

    @Operation(summary = "update contact")
    @PutMapping(value = {""}, consumes = "application/json", produces = "application/hal+json")
    ResponseEntity<EntityModel<ContactDto>> updateContact(@RequestBody ContactDto dto, HttpServletRequest request);

    @Operation(summary = "delete contact")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteContact(@PathVariable("id") String id);
}
