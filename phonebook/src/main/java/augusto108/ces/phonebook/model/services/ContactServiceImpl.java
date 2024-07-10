package augusto108.ces.phonebook.model.services;

import augusto108.ces.phonebook.exceptions.UUIDNumberFormatException;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.Contact;
import augusto108.ces.phonebook.model.mapper.DtoMapper;
import augusto108.ces.phonebook.model.repositories.*;
import jakarta.persistence.NoResultException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

	private final ContactRepository contactRepository;
	private final AddressRepository addressRepository;
	private final TelephoneRepository telephoneRepository;
	private final EmailRepository emailRepository;
	private final InstantMessengerRepository instantMessengerRepository;

	@Autowired
	public ContactServiceImpl(ContactRepository contactRepository,
							  AddressRepository addressRepository,
							  TelephoneRepository telephoneRepository,
							  EmailRepository emailRepository,
							  InstantMessengerRepository instantMessengerRepository) {
		this.contactRepository = contactRepository;
		this.addressRepository = addressRepository;
		this.telephoneRepository = telephoneRepository;
		this.emailRepository = emailRepository;
		this.instantMessengerRepository = instantMessengerRepository;
	}

	@Override
	public Page<ContactDto> findAllContacts(int page, int size) {
		final Page<Contact> contacts = contactRepository.findAll(PageRequest.of(page, size));
		return contacts.map(DtoMapper::fromContactToContactDto);
	}

	@Override
	public ContactDto findContactById(String id) {
		final UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch (NumberFormatException e) {
			throw new UUIDNumberFormatException("Incorrect or malformed UUID string: " + e.getMessage());
		}
		final Contact contact = contactRepository
				.findById(uuid)
				.orElseThrow(() -> new NoResultException("No result found for id: " + id));
		return DtoMapper.fromContactToContactDto(contact);
	}

	@Override
	public ContactDto saveContact(ContactDto dto) {
		addressRepository.saveAll(dto.getAddresses());
		telephoneRepository.saveAll(dto.getTelephones());
		emailRepository.saveAll(dto.getEmails());
		instantMessengerRepository.saveAll(dto.getMessengers());
		final Contact contact = contactRepository.save(DtoMapper.fromContactDtoToContact(dto));
		return DtoMapper.fromContactToContactDto(contact);
	}

	@Override
	public ContactDto updateContact(String id, ContactDto dto) {
		final UUID uuid = UUID.fromString(id);
		Contact contact = contactRepository
				.findById(uuid)
				.orElseThrow(() -> new NoResultException("No result found for id: " + id));
		BeanUtils.copyProperties(DtoMapper.fromContactDtoToContact(dto), contact, "id");
		return DtoMapper.fromContactToContactDto(contactRepository.save(contact));
	}

	@Override
	public void deleteContact(String id) {
		final UUID uuid;
		try {
			uuid = UUID.fromString(id);
		} catch (NumberFormatException e) {
			throw new UUIDNumberFormatException("Incorrect or malformed UUID string: " + e.getMessage());
		}
		final Contact contact = contactRepository
				.findById(uuid)
				.orElseThrow(() -> new NoResultException("No result found for id: " + id));
		contactRepository.delete(contact);
	}

	@Override
	public Page<ContactDto> findContactsByNameContainsIgnoreCase(String text, int page, int size) {
		final Page<Contact> contacts =
				contactRepository.findContactsByNameContainsIgnoreCase(text, PageRequest.of(page, size));
		return contacts.map(DtoMapper::fromContactToContactDto);
	}

	@Override
	public Page<ContactDto> findContactsByWebsiteContainsIgnoreCase(String text, int page, int size) {
		final Page<Contact> contacts =
				contactRepository.findContactsByWebsiteContainsIgnoreCase(text, PageRequest.of(page, size));
		return contacts.map(DtoMapper::fromContactToContactDto);
	}

	@Override
	public Page<ContactDto> findContactsByNoteContainsIgnoreCase(String text, int page, int size) {
		final Page<Contact> contacts =
				contactRepository.findContactsByNoteContainsIgnoreCase(text, PageRequest.of(page, size));
		return contacts.map(DtoMapper::fromContactToContactDto);
	}

	@Override
	public Page<ContactDto> findContactsByTelephoneContains(String number, int page, int size) {
		final Page<Contact> contacts = contactRepository.findContactsByTelephoneContains(number, PageRequest.of(page, size));
		return contacts.map(DtoMapper::fromContactToContactDto);
	}

	@Override
	public Page<ContactDto> findContactsByAddressContainsIgnoreCase(String text, int page, int size) {
		final Page<Contact> contacts = contactRepository.findContactsByAddressContainsIgnoreCase(text, PageRequest.of(page, size));
		return contacts.map(DtoMapper::fromContactToContactDto);
	}

	@Override
	public Page<ContactDto> findContactsByEmailContainsIgnoreCase(String text, int page, int size) {
		final Page<Contact> contacts =
				contactRepository.findContactsByEmailContainsIgnoreCase(text, PageRequest.of(page, size));
		return contacts.map(DtoMapper::fromContactToContactDto);
	}
}
