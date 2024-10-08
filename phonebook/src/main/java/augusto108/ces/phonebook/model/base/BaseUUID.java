package augusto108.ces.phonebook.model.base;

import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.hateoas.RepresentationModel;

import java.util.UUID;

@MappedSuperclass public abstract class BaseUUID extends RepresentationModel<BaseUUID>
{

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	@Column(name = "id", nullable = false, unique = true, columnDefinition = "char(36)")
	@JdbcTypeCode(SqlTypes.CHAR)
	private UUID id;

	public UUID getId()
	{
		return id;
	}

	public void setId(UUID id)
	{
		this.id = id;
	}
}
