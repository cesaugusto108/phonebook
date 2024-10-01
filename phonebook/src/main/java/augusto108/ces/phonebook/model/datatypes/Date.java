package augusto108.ces.phonebook.model.datatypes;

import augusto108.ces.phonebook.model.enums.DateType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable public record Date(@Column(name = "date") java.util.Date date,
			       @Enumerated(EnumType.STRING)
			       @Column(name = "date_type", length = 15)
			       DateType dateType)
{

	public Date()
	{
		this(new java.util.Date(), DateType.OTHER);
	}
}
