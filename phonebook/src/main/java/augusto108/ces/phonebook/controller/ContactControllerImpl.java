package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.hateoas.ContactLinkingService;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.services.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contacts")
public class ContactControllerImpl implements ContactController {

    private final ContactLinkingService linkingService;
    private final ContactService contactService;

    public ContactControllerImpl(ContactLinkingService linkingService, ContactService contactService) {
        this.linkingService = linkingService;
        this.contactService = contactService;
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<ContactDto>>> findAllContacts(int page, int size) {
        final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findAllContacts(page, size);
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    @Override
    public ResponseEntity<EntityModel<ContactDto>> findContactById(String id) {
        final EntityModel<ContactDto> contact = linkingService.findContactById(id);
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }

    @Override
    public ResponseEntity<EntityModel<ContactDto>> saveContact(ContactDto dto, HttpServletRequest request) {
        final EntityModel<ContactDto> contact = linkingService.saveOrUpdateContact(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(contact);
    }

    @Override
    public ResponseEntity<EntityModel<ContactDto>> updateContact(ContactDto dto, HttpServletRequest request) {
        final EntityModel<ContactDto> contact = linkingService.saveOrUpdateContact(dto);
        return ResponseEntity.status(HttpStatus.OK).body(contact);
    }

    @Override
    public ResponseEntity<Void> deleteContact(String id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByNameContainingIgnoreCase(String text,
                                                                                                      int page,
                                                                                                      int size) {

        final PagedModel<EntityModel<ContactDto>> contacts =
                linkingService.findContactsByNameContainsIgnoreCase(text, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByWebsiteContainsIgnoreCase(String text,
                                                                                                       int page,
                                                                                                       int size) {

        final PagedModel<EntityModel<ContactDto>> contacts =
                linkingService.findContactsByWebsiteContainsIgnoreCase(text, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByNoteContainsIgnoreCase(String text,
                                                                                                    int page,
                                                                                                    int size) {

        final PagedModel<EntityModel<ContactDto>> contacts =
                linkingService.findContactsByNoteContainsIgnoreCase(text, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByTelephoneContains(String number,
                                                                                               int page,
                                                                                               int size) {

        final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findContactsByTelephoneContains(number, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByAddressContainsIgnoreCase(String text,
                                                                                                       int page,
                                                                                                       int size) {

        final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findContactsByAddressContainsIgnoreCase(text, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }

    @Override
    public ResponseEntity<PagedModel<EntityModel<ContactDto>>> findContactsByEmailContainsIgnoreCase(String text,
                                                                                                     int page,
                                                                                                     int size) {

        final PagedModel<EntityModel<ContactDto>> contacts =
                linkingService.findContactsByEmailContainsIgnoreCase(text, page, size);
        return ResponseEntity.status(HttpStatus.OK).body(contacts);
    }
}
