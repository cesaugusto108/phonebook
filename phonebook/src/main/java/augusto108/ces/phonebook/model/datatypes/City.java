package augusto108.ces.phonebook.model.datatypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable
public class City {

    @Column(name = "city_name", nullable = false, length = 40)
    private String name;

    @Column(name = "country_state", length = 40)
    private String state;

    @Embedded
    private Country country;

    public City() {
    }

    public City(String name, String state, Country country) {
        this.name = name;
        this.state = state;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
