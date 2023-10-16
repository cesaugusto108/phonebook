package augusto108.ces.phonebook.model.mapper;

import augusto108.ces.phonebook.model.datatypes.Date;
import augusto108.ces.phonebook.model.datatypes.Name;
import augusto108.ces.phonebook.model.datatypes.Note;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.Contact;

public class DtoMapper {

    public static ContactDto fromContactToContactDto(Contact contact) {
        final ContactDto dto = new ContactDto();
        final Name name = contact.getName();
        final Date date = contact.getDate();
        final Note note = contact.getNote();
        dto.setFirstName(name.getFirstName());
        dto.setMiddleName(name.getMiddleName());
        dto.setLastName(name.getLastName());
        dto.setNickname(name.getNickname());
        dto.setPhoneticFirstName(name.getPhoneticFirstName());
        dto.setPhoneticMiddleName(name.getPhoneticMiddleName());
        dto.setPhoneticLastName(name.getPhoneticLastName());
        dto.setRelationship(contact.getRelationship());
        dto.setCompany(contact.getCompany());
        dto.setTitle(contact.getTitle());
        dto.setWebsite(contact.getWebsite());
        dto.setDate(date.getDate());
        dto.setDateType(date.getDateType());
        dto.setNote(note.getText());
        setContactDtoCollections(contact, dto);
        return dto;
    }

    public static Contact fromContactDtoToContact(ContactDto dto) {
        final Contact contact = new Contact();
        final Name name = new Name();
        final Date date = new Date();
        final Note note = new Note();
        setContactName(dto, name, contact);
        contact.setRelationship(dto.getRelationship());
        contact.setCompany(dto.getCompany());
        contact.setTitle(dto.getTitle());
        contact.setWebsite(dto.getWebsite());
        setContactDate(dto, date, contact);
        setContactNote(dto, note, contact);
        setContactCollections(dto, contact);
        return contact;
    }

    private static void setContactDtoCollections(Contact contact, ContactDto dto) {
        contact.getTelephones().forEach(telephone -> dto.getTelephones().add(telephone));
        contact.getAddresses().forEach(address -> dto.getAddresses().add(address));
        contact.getEmails().forEach(email -> dto.getEmails().add(email));
        contact.getMessengers().forEach(messenger -> dto.getMessengers().add(messenger));
    }

    private static void setContactName(ContactDto dto, Name name, Contact contact) {
        name.setFirstName(dto.getFirstName());
        name.setMiddleName(dto.getMiddleName());
        name.setLastName(dto.getLastName());
        name.setNickname(dto.getNickname());
        name.setPhoneticFirstName(dto.getPhoneticFirstName());
        name.setPhoneticMiddleName(dto.getPhoneticMiddleName());
        name.setPhoneticLastName(dto.getPhoneticLastName());
        contact.setName(name);
    }

    private static void setContactDate(ContactDto dto, Date date, Contact contact) {
        date.setDate(dto.getDate());
        date.setDateType(dto.getDateType());
        contact.setDate(date);
    }

    private static void setContactNote(ContactDto dto, Note note, Contact contact) {
        note.setText(dto.getNote());
        contact.setNote(note);
    }

    private static void setContactCollections(ContactDto dto, Contact contact) {
        dto.getTelephones().forEach(telephone -> contact.getTelephones().add(telephone));
        dto.getAddresses().forEach(address -> contact.getAddresses().add(address));
        dto.getEmails().forEach(email -> contact.getEmails().add(email));
        dto.getMessengers().forEach(messenger -> contact.getMessengers().add(messenger));
    }
}
