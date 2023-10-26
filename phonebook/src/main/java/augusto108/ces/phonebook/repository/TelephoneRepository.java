package augusto108.ces.phonebook.repository;

import augusto108.ces.phonebook.model.entities.Telephone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelephoneRepository extends JpaRepository<Telephone, Integer> {
}
