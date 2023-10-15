package augusto108.ces.phonebook.model.base;

import jakarta.persistence.*;

@MappedSuperclass
public class BaseId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
