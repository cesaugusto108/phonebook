package augusto108.ces.phonebook.hateoas;

import augusto108.ces.phonebook.model.dto.ContactDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface ContactLinkingService {

    PagedModel<EntityModel<ContactDto>> findAllContacts(int page, int size);

    EntityModel<ContactDto> findContactById(String id);

    EntityModel<ContactDto> saveOrUpdateContact(ContactDto dto);

    PagedModel<EntityModel<ContactDto>> findContactsByNameContainsIgnoreCase(String text, int page, int size);

    PagedModel<EntityModel<ContactDto>> findContactsByNoteContainsIgnoreCase(String text, int page, int size);

    PagedModel<EntityModel<ContactDto>> findContactsByEmailContainsIgnoreCase(String text, int page, int size);
}
