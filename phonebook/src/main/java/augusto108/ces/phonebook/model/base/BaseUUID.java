package augusto108.ces.phonebook.model.base;

import jakarta.persistence.*;

import java.util.UUID;

@MappedSuperclass
public abstract class BaseUUID {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, unique = true)
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
