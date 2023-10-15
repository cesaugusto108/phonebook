package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.base.BaseUUID;
import augusto108.ces.phonebook.model.datatypes.Date;
import augusto108.ces.phonebook.model.datatypes.Name;
import augusto108.ces.phonebook.model.datatypes.Note;
import augusto108.ces.phonebook.model.enums.Relationship;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "contact")
public class Contact extends BaseUUID {

    @Embedded
    private Name name;

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship", length = 20)
    private Relationship relationship;

    @Column(name = "company", length = 20)
    private String company;

    @Column(name = "title", length = 15)
    private String title;

    @Column(name = "website", length = 50)
    private String website;

    @Embedded
    private Date date;

    @Embedded
    private Note note;

    @ManyToMany
    @JoinTable(
            name = "contact_telephone",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "telephone_id")
    )
    private final Set<Telephone> telephones = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "contact_address",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private final Set<Address> addresses = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "contact_email",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "email_id")
    )
    private final Set<Email> emails = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "contact_im",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "im_id")
    )
    private final Set<InstantMessenger> messengers = new HashSet<>();

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