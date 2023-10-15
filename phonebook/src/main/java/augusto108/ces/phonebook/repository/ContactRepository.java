package augusto108.ces.phonebook.repository;

import augusto108.ces.phonebook.model.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
