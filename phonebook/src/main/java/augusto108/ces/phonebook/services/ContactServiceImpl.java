package augusto108.ces.phonebook.services;

import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.Contact;
import augusto108.ces.phonebook.model.mapper.DtoMapper;
import augusto108.ces.phonebook.repository.*;
import jakarta.persistence.NoResultException;
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
        final UUID uuid = UUID.fromString(id);
        final Contact contact = contactRepository
                .findById(uuid)
                .orElseThrow(() -> new NoResultException("No result found for id: " + id));
        return DtoMapper.fromContactToContactDto(contact);
    }

    @Override
    public ContactDto saveOrUpdateContact(ContactDto dto) {
        addressRepository.saveAll(dto.getAddresses());
        telephoneRepository.saveAll(dto.getTelephones());
        emailRepository.saveAll(dto.getEmails());
        instantMessengerRepository.saveAll(dto.getMessengers());
        final Contact contact = contactRepository.save(DtoMapper.fromContactDtoToContact(dto));
        return DtoMapper.fromContactToContactDto(contact);
    }

    @Override
    public void deleteContact(String id) {
        final UUID uuid = UUID.fromString(id);
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
    public Page<ContactDto> findContactsByTelephone(String number, int page, int size) {
        final Page<Contact> contacts = contactRepository.findContactsByTelephone(number, PageRequest.of(page, size));
        return contacts.map(DtoMapper::fromContactToContactDto);
    }

    @Override
    public Page<ContactDto> findContactsByEmailsContainsIgnoreCase(String text, int page, int size) {
        final Page<Contact> contacts =
                contactRepository.findContactsByEmailsContainsIgnoreCase(text, PageRequest.of(page, size));
        return contacts.map(DtoMapper::fromContactToContactDto);
    }
}
