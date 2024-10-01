package augusto108.ces.phonebook.model.datatypes;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;

@Embeddable public record City(@Column(name = "city_name", nullable = false, length = 40) String name,
			       @Column(name = "country_state", length = 40) String state,
			       @Embedded Country country)
{

	public City()
	{
		this("", "", new Country());
	}
}