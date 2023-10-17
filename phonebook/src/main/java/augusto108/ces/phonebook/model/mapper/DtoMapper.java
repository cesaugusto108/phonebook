package augusto108.ces.phonebook.model.mapper;

import augusto108.ces.phonebook.model.base.BaseId;
import augusto108.ces.phonebook.model.datatypes.Date;
import augusto108.ces.phonebook.model.datatypes.Name;
import augusto108.ces.phonebook.model.datatypes.Note;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.Contact;

import java.util.Set;

public class DtoMapper {

    public static ContactDto fromContactToContactDto(Contact contact) {
        final ContactDto dto = new ContactDto();
        final Name name = contact.getName();
        final Date date = contact.getDate();
        final Note note = contact.getNote();
        dto.setFirstName(name.firstName());
        dto.setMiddleName(name.middleName());
        dto.setLastName(name.lastName());
        dto.setNickname(name.nickname());
        dto.setPhoneticFirstName(name.phoneticFirstName());
        dto.setPhoneticMiddleName(name.phoneticMiddleName());
        dto.setPhoneticLastName(name.phoneticLastName());
        dto.setRelationship(contact.getRelationship());
        dto.setCompany(contact.getCompany());
        dto.setTitle(contact.getTitle());
        dto.setWebsite(contact.getWebsite());
        dto.setDate(date.getDate());
        dto.setDateType(date.getDateType());
        dto.setNote(note.note());
        setCollections(contact.getTelephones(), dto.getTelephones());
        setCollections(contact.getAddresses(), dto.getAddresses());
        setCollections(contact.getEmails(), dto.getEmails());
        setCollections(contact.getMessengers(), dto.getMessengers());
        return dto;
    }

    public static Contact fromContactDtoToContact(ContactDto dto) {
        final Contact contact = new Contact();
        final Date date = new Date();
        setContactName(dto, contact);
        contact.setRelationship(dto.getRelationship());
        contact.setCompany(dto.getCompany());
        contact.setTitle(dto.getTitle());
        contact.setWebsite(dto.getWebsite());
        setContactDate(dto, date, contact);
        setContactNote(dto, contact);
        setCollections(dto.getTelephones(), contact.getTelephones());
        setCollections(dto.getAddresses(), contact.getAddresses());
        setCollections(dto.getEmails(), contact.getEmails());
        setCollections(dto.getMessengers(), contact.getMessengers());
        return contact;
    }

    private static <T extends BaseId> void setCollections(Set<T> collectionSource, Set<T> collectionTarget) {
        collectionTarget.addAll(collectionSource);
    }

    private static void setContactName(ContactDto dto, Contact contact) {
        final Name n = new Name(
                dto.getFirstName(),
                dto.getMiddleName(),
                dto.getLastName(),
                dto.getNickname(),
                dto.getPhoneticFirstName(),
                dto.getPhoneticMiddleName(),
                dto.getPhoneticLastName());
        contact.setName(n);
    }

    private static void setContactDate(ContactDto dto, Date date, Contact contact) {
        date.setDate(dto.getDate());
        date.setDateType(dto.getDateType());
        contact.setDate(date);
    }

    private static void setContactNote(ContactDto dto, Contact contact) {
        final Note note = new Note(dto.getNote());
        contact.setNote(note);
    }
}
