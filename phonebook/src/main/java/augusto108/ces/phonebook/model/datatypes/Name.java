package augusto108.ces.phonebook.model.datatypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Name(
        @Column(name = "first_name", nullable = false, length = 50) String firstName,
        @Column(name = "middle_name", length = 50) String middleName,
        @Column(name = "last_name", length = 50) String lastName,
        @Column(name = "nickname", length = 50) String nickname,
        @Column(name = "phonetic_first_name", length = 50) String phoneticFirstName,
        @Column(name = "phonetic_middle_name", length = 50) String phoneticMiddleName,
        @Column(name = "phonetic_last_name", length = 50) String phoneticLastName
) {

    public Name() {
        this("", "", "", "", "", "", "");
    }
}
