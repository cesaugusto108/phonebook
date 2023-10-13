package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.enums.Relationship;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Contact {

    private UUID id;
    private Name name;
    private Relationship relationship;
    private String company;
    private String title;
    private String website;
    private Date date;
    private Note note;

    private final Set<Telephone> telephones = new HashSet<>();
    private final Set<Address> addresses = new HashSet<>();
    private final Set<Email> emails = new HashSet<>();
    private final Set<InstantMessenger> messengers = new HashSet<>();

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    public void setRelationship(Relationship relationship) {
        this.relationship = relationship;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Set<Telephone> getTelephones() {
        return telephones;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public Set<InstantMessenger> getMessengers() {
        return messengers;
    }
}
