package augusto108.ces.phonebook.model.datatypes;

import jakarta.persistence.Embeddable;

@Embeddable
public record Note(String note) {

    public Note() {
        this("");
    }

    public String note() {
        return note;
    }
}
