package augusto108.ces.phonebook.hateoas;

import augusto108.ces.phonebook.controller.ContactController;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.services.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Service
public class ContactLinkingServiceImpl implements ContactLinkingService {

    private final ContactService contactService;
    private final PagedResourcesAssembler<ContactDto> assembler;

    public ContactLinkingServiceImpl(ContactService contactService, PagedResourcesAssembler<ContactDto> assembler) {
        this.contactService = contactService;
        this.assembler = assembler;
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findAllContacts(int page, int size) {
        return assemblePagedModel(() -> contactService.findAllContacts(page, size));
    }

    @Override
    public EntityModel<ContactDto> findContactById(String id) {
        return getEntityModel(() -> contactService.findContactById(id));
    }

    @Override
    public EntityModel<ContactDto> saveContact(ContactDto dto) {
        return getEntityModel(() -> contactService.saveContact(dto));
    }

    @Override
    public EntityModel<ContactDto> updateContact(String id, ContactDto dto) {
        return getEntityModel(() -> contactService.updateContact(id, dto));
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findContactsByNameContainsIgnoreCase(String text, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByNameContainsIgnoreCase(text, page, size));
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findContactsByWebsiteContainsIgnoreCase(String text, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByWebsiteContainsIgnoreCase(text, page, size));
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findContactsByNoteContainsIgnoreCase(String text, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByNoteContainsIgnoreCase(text, page, size));
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findContactsByTelephoneContains(String number, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByTelephoneContains(number, page, size));
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findContactsByAddressContainsIgnoreCase(String text, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByAddressContainsIgnoreCase(text, page, size));
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findContactsByEmailContainsIgnoreCase(String text, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByEmailContainsIgnoreCase(text, page, size));
    }

    private EntityModel<ContactDto> getEntityModel(ContactEntityFunctionalInterface contactEntityFunctionalInterface) {
        final ContactDto contact = contactEntityFunctionalInterface.getContact();
        final Link selfLink = linkTo(ContactController.class).slash(contact.getId()).withSelfRel();
        final Link contactsLink = linkTo(ContactController.class).slash("").withRel("contacts");
        contact.add(selfLink, contactsLink);
        return EntityModel.of(contact);
    }

    private PagedModel<EntityModel<ContactDto>> assemblePagedModel(ContactsPageFunctionalInterface contactsPageFunctionalInterface) {
        final Page<ContactDto> contacts = contactsPageFunctionalInterface.findContacts();
        contacts.forEach(contact -> contact.add(linkTo(ContactController.class).slash(contact.getId()).withSelfRel()));
        return assembler.toModel(contacts);
    }

    @FunctionalInterface
    private interface ContactEntityFunctionalInterface {
        ContactDto getContact();
    }

    @FunctionalInterface
    private interface ContactsPageFunctionalInterface {
        Page<ContactDto> findContacts();
    }
}
