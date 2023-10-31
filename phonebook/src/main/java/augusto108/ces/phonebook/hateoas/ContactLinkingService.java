package augusto108.ces.phonebook.hateoas;

import augusto108.ces.phonebook.model.dto.ContactDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;

public interface ContactLinkingService {

    PagedModel<EntityModel<ContactDto>> findAllContacts(int page, int size);

    EntityModel<ContactDto> findContactById(String id);

    EntityModel<ContactDto> saveOrUpdateContact(ContactDto dto);
}
