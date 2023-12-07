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
    Page<Contact> findContactsByNameContainsIgnoreCase(@Param("text") String text, Pageable pageable);

    @Query("from Contact c where " +
            "c.website like concat('%', :text, '%') " +
            "order by c.name.firstName")
    Page<Contact> findContactsByWebsiteContainsIgnoreCase(@Param("text") String text, Pageable pageable);

    @Query("from Contact c where " +
            "c.note.note like concat('%', :text, '%') " +
            "order by c.name.firstName")
    Page<Contact> findContactsByNoteContainsIgnoreCase(@Param("text") String text, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                    select contact.*
                    from contact
                        inner join contact_telephone on contact.id = contact_telephone.contact_id
                        inner join telephone on contact_telephone.telephone_id = telephone.id
                    where telephone.country_code like concat('%', :number, '%')
                        or telephone.area_code like concat('%', :number, '%')
                        or telephone.number like concat('%', :number, '%')
                    order by telephone.country_code;""")
    Page<Contact> findContactsByTelephones(@Param("number") String number, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                    select contact.*
                    from contact
                        inner join contact_email on contact.id = contact_email.contact_id
                        inner join email on contact_email.email_id = email.id
                    where email.email_username like concat('%', :text, '%')
                        or email.email_domain like concat('%', :text, '%')
                    order by contact.first_name;""")
    Page<Contact> findContactsByEmailsContainsIgnoreCase(@Param("text") String text, Pageable pageable);
}
