package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.hateoas.ContactLinkingService;
import augusto108.ces.phonebook.model.dto.ContactDto;
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

    public ContactControllerImpl(ContactLinkingService linkingService) {
        this.linkingService = linkingService;
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
}
