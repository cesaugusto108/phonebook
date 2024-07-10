package augusto108.ces.phonebook.model.datatypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record Country(@Column(name = "country_name", length = 40) String name) {

	public Country() {
		this("");
	}
}