package augusto108.ces.phonebook.repository;

import augusto108.ces.phonebook.model.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Integer> {
}
