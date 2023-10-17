package augusto108.ces.phonebook.model.datatypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Note(@Column(name = "note") String note) {

    public Note() {
        this("");
    }

    public String note() {
        return note;
    }
}
