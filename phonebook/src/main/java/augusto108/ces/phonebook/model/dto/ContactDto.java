package augusto108.ces.phonebook.model.dto;

import augusto108.ces.phonebook.model.base.BaseUUID;
import augusto108.ces.phonebook.model.entities.Address;
import augusto108.ces.phonebook.model.entities.Email;
import augusto108.ces.phonebook.model.entities.InstantMessenger;
import augusto108.ces.phonebook.model.entities.Telephone;
import augusto108.ces.phonebook.model.enums.DateType;
import augusto108.ces.phonebook.model.enums.Relationship;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ContactDto extends BaseUUID
{

	private String firstName;
	private String middleName;
	private String lastName;
	private String nickname;
	private String phoneticFirstName;
	private String phoneticMiddleName;
	private String phoneticLastName;
	private Relationship relationship;
	private String company;
	private String title;
	private String website;
	private Date date;
	private DateType dateType;
	private String note;

	private final Set<Telephone> telephones = new HashSet<>();
	private final Set<Address> addresses = new HashSet<>();
	private final Set<Email> emails = new HashSet<>();
	private final Set<InstantMessenger> messengers = new HashSet<>();

	public String getFirstName()
	{
		return firstName;
	}

	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	public String getMiddleName()
	{
		return middleName;
	}

	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	public String getNickname()
	{
		return nickname;
	}

	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}

	public String getPhoneticFirstName()
	{
		return phoneticFirstName;
	}

	public void setPhoneticFirstName(String phoneticFirstName)
	{
		this.phoneticFirstName = phoneticFirstName;
	}

	public String getPhoneticMiddleName()
	{
		return phoneticMiddleName;
	}

	public void setPhoneticMiddleName(String phoneticMiddleName)
	{
		this.phoneticMiddleName = phoneticMiddleName;
	}

	public String getPhoneticLastName()
	{
		return phoneticLastName;
	}

	public void setPhoneticLastName(String phoneticLastName)
	{
		this.phoneticLastName = phoneticLastName;
	}

	public Relationship getRelationship()
	{
		return relationship;
	}

	public void setRelationship(Relationship relationship)
	{
		this.relationship = relationship;
	}

	public String getCompany()
	{
		return company;
	}

	public void setCompany(String company)
	{
		this.company = company;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getWebsite()
	{
		return website;
	}

	public void setWebsite(String website)
	{
		this.website = website;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public DateType getDateType()
	{
		return dateType;
	}

	public void setDateType(DateType dateType)
	{
		this.dateType = dateType;
	}

	public String getNote()
	{
		return note;
	}

	public void setNote(String note)
	{
		this.note = note;
	}

	public Set<Telephone> getTelephones()
	{
		return telephones;
	}

	public Set<Address> getAddresses()
	{
		return addresses;
	}

	public Set<Email> getEmails()
	{
		return emails;
	}

	public Set<InstantMessenger> getMessengers()
	{
		return messengers;
	}
}
