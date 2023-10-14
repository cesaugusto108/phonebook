package augusto108.ces.phonebook.model.datatypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Name {

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "middle_name", length = 50)
    private String middleName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "phonetic_first_name", length = 50)
    private String phoneticFirstName;

    @Column(name = "phonetic_middle_name", length = 50)
    private String phoneticMiddleName;

    @Column(name = "phonetic_last_name", length = 50)
    private String phoneticLastName;

    public Name() {
    }

    public Name(String firstName,
                String middleName,
                String lastName,
                String nickname,
                String phoneticFirstName,
                String phoneticMiddleName,
                String phoneticLastName) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nickname = nickname;
        this.phoneticFirstName = phoneticFirstName;
        this.phoneticMiddleName = phoneticMiddleName;
        this.phoneticLastName = phoneticLastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoneticFirstName() {
        return phoneticFirstName;
    }

    public void setPhoneticFirstName(String phoneticFirstName) {
        this.phoneticFirstName = phoneticFirstName;
    }

    public String getPhoneticMiddleName() {
        return phoneticMiddleName;
    }

    public void setPhoneticMiddleName(String phoneticMiddleName) {
        this.phoneticMiddleName = phoneticMiddleName;
    }

    public String getPhoneticLastName() {
        return phoneticLastName;
    }

    public void setPhoneticLastName(String phoneticLastName) {
        this.phoneticLastName = phoneticLastName;
    }
}
