package augusto108.ces.phonebook.repository;

import augusto108.ces.phonebook.model.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {

    @Query("from Contact c where " +
            "c.name.firstName like concat('%', :text, '%') or " +
            "c.name.middleName like concat('%', :text, '%') or " +
            "c.name.lastName like concat('%', :text, '%') " +
            "order by c.name.firstName")
    Page<Contact> findContactsByNameContainsIgnoreCase(@Param("text") String name, Pageable pageable);

    @Query("from Contact c where " +
            "c.note.note like concat('%', :text, '%') " +
            "order by c.name.firstName"
    )
    Page<Contact> findContactsByNoteContainsIgnoreCase(@Param("text") String text, Pageable pageable);
}
