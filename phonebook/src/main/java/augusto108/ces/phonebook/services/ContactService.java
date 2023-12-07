package augusto108.ces.phonebook.services;

import augusto108.ces.phonebook.model.dto.ContactDto;
import org.springframework.data.domain.Page;

public interface ContactService {

    Page<ContactDto> findAllContacts(int page, int size);

    ContactDto findContactById(String id);

    ContactDto saveOrUpdateContact(ContactDto dto);

    void deleteContact(String id);

    Page<ContactDto> findContactsByNameContainsIgnoreCase(String text, int page, int size);

    Page<ContactDto> findContactsByWebsiteContainsIgnoreCase(String text, int page, int size);

    Page<ContactDto> findContactsByNoteContainsIgnoreCase(String text, int page, int size);

    Page<ContactDto> findContactsByTelephones(String number, int page, int size);

    Page<ContactDto> findContactsByEmailsContainsIgnoreCase(String text, int page, int size);
}
