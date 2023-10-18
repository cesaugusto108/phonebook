package augusto108.ces.phonebook.services;

import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.Contact;
import augusto108.ces.phonebook.model.mapper.DtoMapper;
import augusto108.ces.phonebook.repository.ContactRepository;
import jakarta.persistence.NoResultException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Page<ContactDto> findAllContacts(int page, int size) {
        final Page<Contact> contacts = contactRepository.findAll(PageRequest.of(page, size));
        return contacts.map(DtoMapper::fromContactToContactDto);
    }

    @Override
    public ContactDto findContactById(String id) {
        final Contact contact = contactRepository
                .findById(UUID.fromString(id))
                .orElseThrow(() -> new NoResultException("No result found for id: " + id));
        return DtoMapper.fromContactToContactDto(contact);
    }

    @Override
    public ContactDto saveContact(ContactDto dto) {
        final Contact contact = contactRepository.save(DtoMapper.fromContactDtoToContact(dto));
        return DtoMapper.fromContactToContactDto(contact);
    }

    @Override
    public void deleteContact(String id) {
        contactRepository.deleteById(UUID.fromString(id));
    }
}
