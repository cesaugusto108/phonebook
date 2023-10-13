package augusto108.ces.phonebook.model.entities;

public class Name {

    private String firstName;
    private String middleName;
    private String lastName;
    private String nickname;
    private String phoneticFirstName;
    private String phoneticMiddleName;
    private String phoneticLastName;

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
