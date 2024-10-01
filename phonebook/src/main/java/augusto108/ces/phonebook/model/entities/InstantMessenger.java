package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.base.BaseId;
import augusto108.ces.phonebook.model.enums.InstantMessengerType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "instant_messenger")
@JsonIgnoreProperties(value = {"id"})
public class InstantMessenger extends BaseId
{

	@Column(name = "im_username", nullable = false, length = 20)
	private String username;

	@Enumerated(EnumType.STRING)
	@Column(name = "im_type", nullable = false, length = 15)
	private InstantMessengerType imType;

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public InstantMessengerType getImType()
	{
		return imType;
	}

	public void setImType(InstantMessengerType imType)
	{
		this.imType = imType;
	}
}
