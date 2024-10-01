package augusto108.ces.phonebook.controller;

import augusto108.ces.phonebook.exceptions.UnmatchedIdException;
import augusto108.ces.phonebook.hateoas.ContactLinkingService;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.services.ContactService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Objects;

@RestController public class ContactControllerImpl implements ContactController
{

	private final ContactLinkingService linkingService;
	private final ContactService contactService;

	@Autowired public ContactControllerImpl(ContactLinkingService linkingService, ContactService contactService)
	{
		this.linkingService = linkingService;
		this.contactService = contactService;
	}

	@Override public ResponseEntity<PagedModel<EntityModel<ContactDto>>> findAllContacts(
			int page,
			int size,
			HttpServletRequest httpServletRequest
	)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findAllContacts(page, size);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contacts);
	}

	@Override public ResponseEntity<EntityModel<ContactDto>> findContactById(String id, HttpServletRequest httpServletRequest)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final EntityModel<ContactDto> contact = linkingService.findContactById(id);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contact);
	}

	@Override public ResponseEntity<EntityModel<ContactDto>> saveContact(ContactDto dto, HttpServletRequest httpServletRequest)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final EntityModel<ContactDto> contact = linkingService.saveContact(dto);
		final String path = "/" + Objects.requireNonNull(contact.getContent()).getId().toString();
		final URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(path).build().toUri();
		return ResponseEntity.status(201).header("Used-HTTP-Method", httpMethod).location(uri).body(contact);
	}

	@Override public ResponseEntity<EntityModel<ContactDto>> updateContact(
			String id,
			ContactDto dto,
			HttpServletRequest httpServletRequest
	)
	{
		if (!id.equals(dto.getId().toString()))
			throw new UnmatchedIdException("ID in body does not match ID in URL");
		final String httpMethod = httpServletRequest.getMethod();
		final EntityModel<ContactDto> contact = linkingService.updateContact(id, dto);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contact);
	}

	@Override public ResponseEntity<Void> deleteContact(String id, HttpServletRequest httpServletRequest)
	{
		contactService.deleteContact(id);
		final String httpMethod = httpServletRequest.getMethod();
		return ResponseEntity.status(204).header("Used-HTTP-Method", httpMethod).build();
	}

	@Override public ResponseEntity<PagedModel<EntityModel<ContactDto>>>
	findContactsByNameContainingIgnoreCase(String text, int page, int size, HttpServletRequest httpServletRequest)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findContactsByNameContainsIgnoreCase(text, page, size);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contacts);
	}

	@Override public ResponseEntity<PagedModel<EntityModel<ContactDto>>>
	findContactsByWebsiteContainsIgnoreCase(String text, int page, int size, HttpServletRequest httpServletRequest)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findContactsByWebsiteContainsIgnoreCase(text, page, size);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contacts);
	}

	@Override public ResponseEntity<PagedModel<EntityModel<ContactDto>>>
	findContactsByNoteContainsIgnoreCase(String text, int page, int size, HttpServletRequest httpServletRequest)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findContactsByNoteContainsIgnoreCase(text, page, size);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contacts);
	}

	@Override public ResponseEntity<PagedModel<EntityModel<ContactDto>>>
	findContactsByTelephoneContains(String number, int page, int size, HttpServletRequest httpServletRequest)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findContactsByTelephoneContains(number, page, size);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contacts);
	}

	@Override public ResponseEntity<PagedModel<EntityModel<ContactDto>>>
	findContactsByAddressContainsIgnoreCase(String text, int page, int size, HttpServletRequest httpServletRequest)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findContactsByAddressContainsIgnoreCase(text, page, size);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contacts);
	}

	@Override public ResponseEntity<PagedModel<EntityModel<ContactDto>>>
	findContactsByEmailContainsIgnoreCase(String text, int page, int size, HttpServletRequest httpServletRequest)
	{
		final String httpMethod = httpServletRequest.getMethod();
		final PagedModel<EntityModel<ContactDto>> contacts = linkingService.findContactsByEmailContainsIgnoreCase(text, page, size);
		return ResponseEntity.status(200).header("Used-HTTP-Method", httpMethod).body(contacts);
	}
}
