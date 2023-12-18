package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.model.dto.ContactDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
    ResponseEntity<EntityModel<ContactDto>> saveContact(@RequestBody ContactDto dto);

    @Operation(summary = "update contact")
    @PutMapping(value = {"/{id}"}, consumes = "application/json", produces = "application/hal+json")
    ResponseEntity<EntityModel<ContactDto>> updateContact(@PathVariable("id") String id, @RequestBody ContactDto dto);

    @Operation(summary = "delete contact")
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteContact(@PathVariable("id") String id);

    @Operation(summary = "search contacts by name")
    @GetMapping(value = "/name-search", produces = "application/hal+json")
    ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByNameContainingIgnoreCase(
            @RequestParam(defaultValue = "", required = false, name = "search") String text,
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size
    );

    @Operation(summary = "search contacts by website")
    @GetMapping(value = "/website-search", produces = "application/hal+json")
    ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByWebsiteContainsIgnoreCase(
            @RequestParam(defaultValue = "", required = false, name = "search") String text,
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size
    );

    @Operation(summary = "search contacts by note content")
    @GetMapping(value = "/note-search", produces = "application/hal+json")
    ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByNoteContainsIgnoreCase(
            @RequestParam(defaultValue = "", required = false, name = "search") String text,
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size
    );

    @Operation(summary = "search contacts by telephone country code, area code or number")
    @GetMapping(value = "/telephone-search", produces = "application/hal+json")
    ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByTelephoneContains(
            @RequestParam(defaultValue = "", required = false, name = "search") String number,
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size
    );

    @Operation(summary = "search contacts by address details")
    @GetMapping(value = "/address-search", produces = "application/hal+json")
    ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByAddressContainsIgnoreCase(
            @RequestParam(defaultValue = "", required = false, name = "search") String text,
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size
    );

    @Operation(summary = "search contacts by email username or domain")
    @GetMapping(value = "/email-search", produces = "application/hal+json")
    ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByEmailContainsIgnoreCase(
            @RequestParam(defaultValue = "", required = false, name = "search") String text,
            @RequestParam(defaultValue = "0", required = false, name = "page") int page,
            @RequestParam(defaultValue = "10", required = false, name = "size") int size
    );
}
