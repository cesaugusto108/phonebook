package augusto108.ces.phonebook.model.services;

import augusto108.ces.phonebook.model.dto.ContactDto;
import org.springframework.data.domain.Page;

public interface ContactService
{

	Page<ContactDto> findAllContacts(int page, int size);

	ContactDto findContactById(String id);

	ContactDto saveContact(ContactDto dto);

	ContactDto updateContact(String id, ContactDto dto);

	void deleteContact(String id);

	Page<ContactDto> findContactsByNameContainsIgnoreCase(String text, int page, int size);

	Page<ContactDto> findContactsByWebsiteContainsIgnoreCase(String text, int page, int size);

	Page<ContactDto> findContactsByNoteContainsIgnoreCase(String text, int page, int size);

	Page<ContactDto> findContactsByTelephoneContains(String number, int page, int size);

	Page<ContactDto> findContactsByAddressContainsIgnoreCase(String text, int page, int size);

	Page<ContactDto> findContactsByEmailContainsIgnoreCase(String text, int page, int size);
}
