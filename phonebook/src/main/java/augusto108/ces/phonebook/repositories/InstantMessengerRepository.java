package augusto108.ces.phonebook.repositories;

import augusto108.ces.phonebook.model.entities.InstantMessenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstantMessengerRepository extends JpaRepository<InstantMessenger, Integer> {
}
