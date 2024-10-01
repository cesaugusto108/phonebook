package augusto108.ces.phonebook.model.repositories;

import augusto108.ces.phonebook.model.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository public interface EmailRepository extends JpaRepository<Email, Integer>
{
}
