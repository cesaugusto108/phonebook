package augusto108.ces.phonebook.model.datatypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Country {

    @Column(name = "country_name", length = 40)
    private String name;

    public Country() {
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
