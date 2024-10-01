package augusto108.ces.phonebook.model.mapper;

import augusto108.ces.phonebook.model.datatypes.City;
import augusto108.ces.phonebook.model.datatypes.Country;
import augusto108.ces.phonebook.model.dto.ContactDto;
import augusto108.ces.phonebook.model.entities.*;
import augusto108.ces.phonebook.model.enums.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayNameGeneration(DisplayNameGenerator.Simple.class)
public class ToContactTest
{

	private static ContactDto dto;

	@BeforeAll static void setUp()
	{
		dto = new ContactDto();
		final Date date = getDate();
		final Telephone telephone = getTelephone();
		final Address address = getAddress();
		final Email email = getEmail();
		final InstantMessenger messenger = getMessenger();
		setUpContactDto(date, telephone, address, email, messenger);
	}

	private static Date getDate()
	{
		final Calendar calendar = Calendar.getInstance();
		calendar.set(1991, Calendar.OCTOBER, 1);
		return calendar.getTime();
	}

	private static Telephone getTelephone()
	{
		final Telephone telephone = new Telephone();
		telephone.setCountryCode("55");
		telephone.setAreaCode("79");
		telephone.setNumber("998980020");
		telephone.setTelephoneType(TelephoneType.MAIN);
		telephone.setId(10);
		return telephone;
	}

	private static Address getAddress()
	{
		final Address address = new Address();
		final Country country = new Country("Brasil");
		final City city = new City("Aracaju", "Sergipe", country);
		address.setStreet("Amazonas");
		address.setNumber("352");
		address.setComplement("Casa A");
		address.setDistrict("Siqueira Campos");
		address.setPostalCode("49180-990");
		address.setCity(city);
		address.setAddressType(AddressType.HOME);
		address.setId(14);
		return address;
	}

	private static Email getEmail()
	{
		final Email email = new Email();
		email.setUsername("fernanda");
		email.setDomain("email.com");
		email.setEmailType(EmailType.WORK);
		email.setId(18);
		return email;
	}

	private static InstantMessenger getMessenger()
	{
		final InstantMessenger messenger = new InstantMessenger();
		messenger.setUsername("fsantos");
		messenger.setImType(InstantMessengerType.OTHER);
		messenger.setId(40);
		return messenger;
	}

	private static void setUpContactDto(Date date,
					    Telephone telephone,
					    Address address,
					    Email email,
					    InstantMessenger messenger)
	{
		dto.setFirstName("Fernanda");
		dto.setMiddleName("Santos");
		dto.setLastName("Sá");
		dto.setNickname("Nanda");
		dto.setPhoneticFirstName("Fernanda");
		dto.setPhoneticMiddleName("Sahntoss");
		dto.setPhoneticLastName("Sah");
		dto.setRelationship(Relationship.FRIEND);
		dto.setCompany("Mercado Livre");
		dto.setTitle("Mrs");
		dto.setWebsite("www.fake.com");
		dto.setDate(date);
		dto.setDateType(DateType.OTHER);
		dto.setNote("Lorem ipsum");
		dto.getTelephones().add(telephone);
		dto.getAddresses().add(address);
		dto.getEmails().add(email);
		dto.getMessengers().add(messenger);
	}

	@Test void fromContactDtoToContact()
	{
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final Contact contact = DtoMapper.fromContactDtoToContact(dto);
		testNonCollectionFields(contact, simpleDateFormat);
		testTelephones(contact);
		testAddresses(contact);
		testEmails(contact);
		testMessengers(contact);
	}

	private static void testNonCollectionFields(Contact contact, SimpleDateFormat simpleDateFormat)
	{
		assertEquals("Fernanda", contact.getName().firstName());
		assertEquals("Santos", contact.getName().middleName());
		assertEquals("Sá", contact.getName().lastName());
		assertEquals("Fernanda", contact.getName().phoneticFirstName());
		assertEquals("Sahntoss", contact.getName().phoneticMiddleName());
		assertEquals("Sah", contact.getName().phoneticLastName());
		assertEquals("FRIEND", contact.getRelationship().toString());
		assertEquals("Mercado Livre", contact.getCompany());
		assertEquals("Mrs", contact.getTitle());
		assertEquals("www.fake.com", contact.getWebsite());
		assertEquals("1991-10-01", simpleDateFormat.format(contact.getDate().date()));
		assertEquals(DateType.OTHER, contact.getDate().dateType());
		assertEquals("Lorem ipsum", contact.getNote().note());
	}

	private static void testTelephones(Contact contact)
	{
		final Telephone telephone = contact.getTelephones().stream().toList().get(0);
		final String countryCode = telephone.getCountryCode();
		final String areaCode = telephone.getAreaCode();
		final String number = telephone.getNumber();
		final String telephoneType = telephone.getTelephoneType().toString();
		final String telephoneString = countryCode + areaCode + number + " (" + telephoneType + ")";
		assertEquals(1, contact.getTelephones().size());
		assertEquals("5579998980020 (MAIN)", telephoneString);
		assertEquals(10, telephone.getId());
	}

	private static void testAddresses(Contact contact)
	{
		final Address address = contact.getAddresses().stream().toList().get(0);
		final String street = address.getStreet();
		final String number = address.getNumber();
		final String complement = address.getComplement();
		final String district = address.getDistrict();
		final String postalCode = address.getPostalCode();
		final String cityName = address.getCity().name();
		final String cityState = address.getCity().state();
		final String countryName = address.getCity().country().name();
		final String addressType = address.getAddressType().toString();
		final String addressString =
				street + ", " + number + ", " + complement + ", " + district + ", " + postalCode + ", " +
						cityName + ", " + cityState + ", " + countryName + " (" + addressType + ")";
		assertEquals(1, contact.getAddresses().stream().toList().size());
		assertEquals("Amazonas, 352, Casa A, Siqueira Campos, 49180-990, Aracaju, Sergipe, Brasil (HOME)", addressString);
		assertEquals(14, address.getId());
	}

	private static void testEmails(Contact contact)
	{
		final Email email = contact.getEmails().stream().toList().get(0);
		final String username = email.getUsername();
		final String domain = email.getDomain();
		final String emailType = email.getEmailType().toString();
		final String emailString = username + "@" + domain + " (" + emailType + ")";
		assertEquals(1, contact.getEmails().stream().toList().size());
		assertEquals("fernanda@email.com (WORK)", emailString);
		assertEquals(18, email.getId());
	}

	private static void testMessengers(Contact contact)
	{
		final InstantMessenger messenger = contact.getMessengers().stream().toList().get(0);
		final String username = messenger.getUsername();
		final String imType = messenger.getImType().toString();
		final String messengerString = username + " (" + imType + ")";
		assertEquals(1, contact.getMessengers().size());
		assertEquals("fsantos (OTHER)", messengerString);
		assertEquals(40, messenger.getId());
	}
}
