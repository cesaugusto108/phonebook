package augusto108.ces.phonebook.services;

import augusto108.ces.phonebook.model.dto.ContactDto;
import org.springframework.data.domain.Page;

public interface ContactService {

    Page<ContactDto> findAllContacts(int page, int size);

    ContactDto findContactById(String id);

    ContactDto saveContact(ContactDto dto);

    ContactDto updateContact(ContactDto dto);

    void deleteContact(String id);
}
