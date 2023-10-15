package augusto108.ces.phonebook.model.mapper;

import augusto108.ces.phonebook.model.datatypes.Date;
import augusto108.ces.phonebook.model.datatypes.Name;
import augusto108.ces.phonebook.model.datatypes.Note;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.*;

import java.util.Set;

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
        dto.setTelephones(contact.getTelephones());
        dto.setAddresses(contact.getAddresses());
        dto.setEmails(contact.getEmails());
        dto.setMessengers(contact.getMessengers());
        return dto;
    }

    public static Contact fromContactDtoToContact(ContactDto dto) {
        final Contact contact = new Contact();
        final Name name = new Name();
        final Date date = new Date();
        final Note note = new Note();
        final Set<Telephone> telephones = dto.getTelephones();
        final Set<Address> addresses = dto.getAddresses();
        final Set<Email> emails = dto.getEmails();
        final Set<InstantMessenger> messengers = dto.getMessengers();
        setContactName(dto, name, contact);
        contact.setRelationship(dto.getRelationship());
        contact.setCompany(dto.getCompany());
        contact.setTitle(dto.getTitle());
        contact.setWebsite(dto.getWebsite());
        setContactDate(dto, date, contact);
        setContactNote(dto, note, contact);
        setContactCollections(contact, telephones, addresses, emails, messengers);
        return contact;
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

    private static void setContactCollections(Contact contact,
                                              Set<Telephone> telephones,
                                              Set<Address> addresses,
                                              Set<Email> emails,
                                              Set<InstantMessenger> messengers) {
        telephones.forEach(telephone -> contact.getTelephones().add(telephone));
        addresses.forEach(address -> contact.getAddresses().add(address));
        emails.forEach(email -> contact.getEmails().add(email));
        messengers.forEach(messenger -> contact.getMessengers().add(messenger));
    }
}
