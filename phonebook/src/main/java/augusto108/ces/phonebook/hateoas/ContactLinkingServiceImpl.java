package augusto108.ces.phonebook.hateoas;

import augusto108.ces.phonebook.controller.ContactController;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.services.ContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
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
    public EntityModel<ContactDto> saveOrUpdateContact(ContactDto dto) {
        return getEntityModel(() -> contactService.saveOrUpdateContact(dto));
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
    public PagedModel<EntityModel<ContactDto>> findContactsByTelephone(String number, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByTelephone(number, page, size));
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findContactsByAddress(String text, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByAddress(text, page, size));
    }

    @Override
    public PagedModel<EntityModel<ContactDto>> findContactsByEmailContainsIgnoreCase(String text, int page, int size) {
        return assemblePagedModel(() -> contactService.findContactsByEmailsContainsIgnoreCase(text, page, size));
    }

    private EntityModel<ContactDto> getEntityModel(ContactEntityFunctionalInterface contactEntityFunctionalInterface) {
        final String path = "/api/v1/contacts/";
        final WebMvcLinkBuilder linkBuilder = linkTo(ContactController.class);
        final ContactDto contact = contactEntityFunctionalInterface.getContact();
        contact.add(linkBuilder.slash(path + contact.getId()).withSelfRel());
        contact.add(linkBuilder.slash(path).withRel("contacts"));
        return EntityModel.of(contact);
    }

    private PagedModel<EntityModel<ContactDto>> assemblePagedModel(
            ContactsPageFunctionalInterface contactsPageFunctionalInterface) {

        final String path = "/api/v1/contacts/";
        final WebMvcLinkBuilder linkBuilder = linkTo(ContactController.class);
        final Page<ContactDto> contacts = contactsPageFunctionalInterface.findContacts();
        contacts.forEach(contact -> contact.add(linkBuilder.slash(path + contact.getId()).withSelfRel()));
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
