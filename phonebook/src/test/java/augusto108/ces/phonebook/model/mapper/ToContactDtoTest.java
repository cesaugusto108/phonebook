package augusto108.ces.phonebook.model.mapper;

import augusto108.ces.phonebook.model.datatypes.*;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.*;
import augusto108.ces.phonebook.model.enums.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
class ToContactDtoTest {

    private static Contact contact;

    @BeforeAll
    static void setUp() {
        contact = new Contact();
        final Name name = getName();
        final Date date = getDate();
        final Note note = getNote();
        final Telephone telephone = getTelephone();
        final Address address = getAddress();
        final Email email = getEmail();
        final InstantMessenger messenger = getMessenger();
        setUpContact(name, date, note, telephone, address, email, messenger);
    }

    private static Name getName() {
        final Name name = new Name();
        name.setFirstName("Ana");
        name.setMiddleName("Maria");
        name.setLastName("Santos");
        name.setNickname("Aninha");
        name.setPhoneticFirstName("Ana");
        name.setPhoneticMiddleName("Mareeah");
        name.setPhoneticLastName("Sahntoss");
        return name;
    }

    private static Note getNote() {
        final Note note = new Note();
        note.setText("Lorem ipsum");
        return note;
    }

    private static Date getDate() {
        final Date date = new Date();
        final Calendar calendar = Calendar.getInstance();
        calendar.set(1991, Calendar.OCTOBER, 1);
        date.setDate(calendar.getTime());
        date.setDateType(DateType.BIRTHDAY);
        return date;
    }

    private static Telephone getTelephone() {
        final Telephone telephone = new Telephone();
        telephone.setCountryCode("55");
        telephone.setAreaCode("79");
        telephone.setNumber("991910018");
        telephone.setTelephoneType(TelephoneType.COMPANY_MAIN);
        telephone.setId(12);
        return telephone;
    }

    private static Address getAddress() {
        final Country country = new Country();
        country.setName("Brasil");
        final City city = new City();
        city.setName("Aracaju");
        city.setState("Sergipe");
        city.setCountry(country);
        final Address address = new Address();
        address.setStreet("Ivo do Prado");
        address.setNumber("1199");
        address.setComplement("Bloco A, apto 101");
        address.setDistrict("São José");
        address.setPostalCode("49110-001");
        address.setCity(city);
        address.setAddressType(AddressType.HOME);
        address.setId(25);
        return address;
    }

    private static Email getEmail() {
        final Email email = new Email();
        email.setUsername("ana");
        email.setDomain("email.com");
        email.setEmailType(EmailType.PERSONAL);
        email.setId(15);
        return email;
    }

    private static InstantMessenger getMessenger() {
        final InstantMessenger messenger = new InstantMessenger();
        messenger.setUsername("anass");
        messenger.setImType(InstantMessengerType.DISCORD);
        messenger.setId(30);
        return messenger;
    }

    private static void setUpContact(Name name,
                                     Date date,
                                     Note note,
                                     Telephone telephone,
                                     Address address,
                                     Email email,
                                     InstantMessenger messenger) {
        contact.setName(name);
        contact.setRelationship(Relationship.FRIEND);
        contact.setCompany("Ambev");
        contact.setTitle("Mrs");
        contact.setWebsite("www.fictitious.com");
        contact.setDate(date);
        contact.setNote(note);
        contact.getTelephones().add(telephone);
        contact.getAddresses().add(address);
        contact.getEmails().add(email);
        contact.getMessengers().add(messenger);
    }

    @Test
    void fromContactToContactDto() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        final ContactDto contactDto = DtoMapper.fromContactToContactDto(contact);
        testNonCollectionFields(contactDto, simpleDateFormat);
        testTelephones(contactDto);
        testAddresses(contactDto);
        testEmails(contactDto);
        testMessengers(contactDto);
    }

    private static void testNonCollectionFields(ContactDto contactDto, SimpleDateFormat simpleDateFormat) {
        assertEquals("Ana", contactDto.getFirstName());
        assertEquals("Maria", contactDto.getMiddleName());
        assertEquals("Santos", contactDto.getLastName());
        assertEquals("Aninha", contactDto.getNickname());
        assertEquals("Ana", contactDto.getPhoneticFirstName());
        assertEquals("Mareeah", contactDto.getPhoneticMiddleName());
        assertEquals("Sahntoss", contactDto.getPhoneticLastName());
        assertEquals(Relationship.FRIEND, contactDto.getRelationship());
        assertEquals("Ambev", contactDto.getCompany());
        assertEquals("Mrs", contactDto.getTitle());
        assertEquals("www.fictitious.com", contactDto.getWebsite());
        assertEquals("1991-10-01", simpleDateFormat.format(contactDto.getDate()));
        assertEquals(DateType.BIRTHDAY, contactDto.getDateType());
        assertEquals("Lorem ipsum", contactDto.getNote());
    }

    private static void testTelephones(ContactDto contactDto) {
        final Telephone telephone = contactDto.getTelephones().stream().toList().get(0);
        final String countryCode = telephone.getCountryCode();
        final String areaCode = telephone.getAreaCode();
        final String number = telephone.getNumber();
        final String telephoneType = telephone.getTelephoneType().toString();
        final String telephoneString = countryCode + areaCode + number + " (" + telephoneType + ")";
        assertEquals(1, contactDto.getTelephones().size());
        assertEquals("5579991910018 (COMPANY_MAIN)", telephoneString);
        assertEquals(12, telephone.getId());
    }

    private static void testAddresses(ContactDto contactDto) {
        final Address address = contactDto.getAddresses().stream().toList().get(0);
        final String street = address.getStreet();
        final String number = address.getNumber();
        final String complement = address.getComplement();
        final String district = address.getDistrict();
        final String postalCode = address.getPostalCode();
        final String cityName = address.getCity().getName();
        final String cityState = address.getCity().getState();
        final String countryName = address.getCity().getCountry().getName();
        final String addressType = address.getAddressType().toString();
        final String addressString =
                street + ", " + number + ", " + complement + ", " + district + ", " + postalCode + ", " +
                        cityName + ", " + cityState + ", " + countryName + " (" + addressType + ")";
        assertEquals(1, contactDto.getAddresses().stream().toList().size());
        assertEquals("Ivo do Prado, 1199, Bloco A, apto 101, São José, 49110-001, Aracaju, Sergipe, Brasil (HOME)", addressString);
        assertEquals(25, address.getId());
    }

    private static void testEmails(ContactDto contactDto) {
        final Email email = contactDto.getEmails().stream().toList().get(0);
        final String username = email.getUsername();
        final String domain = email.getDomain();
        final String emailType = email.getEmailType().toString();
        final String emailString = username + "@" + domain + " (" + emailType + ")";
        assertEquals(1, contactDto.getEmails().stream().toList().size());
        assertEquals("ana@email.com (PERSONAL)", emailString);
        assertEquals(15, email.getId());
    }

    private static void testMessengers(ContactDto contactDto) {
        final InstantMessenger messenger = contactDto.getMessengers().stream().toList().get(0);
        final String username = messenger.getUsername();
        final String imType = messenger.getImType().toString();
        final String messengerString = username + " (" + imType + ")";
        assertEquals(1, contactDto.getMessengers().size());
        assertEquals("anass (DISCORD)", messengerString);
        assertEquals(30, messenger.getId());
    }
}