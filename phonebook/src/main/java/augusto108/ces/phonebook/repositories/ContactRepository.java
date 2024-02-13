package augusto108.ces.phonebook.repositories;

import augusto108.ces.phonebook.model.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
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
                    order by telephone.number;""")
    Page<Contact> findContactsByTelephoneContains(@Param("number") String number, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                    select contact.*
                    from contact
                        inner join contact_address on contact.id = contact_address.contact_id
                        inner join address on contact_address.address_id = address.id
                    where address.city_name like concat('%', :text, '%')
                        or address.complement like concat('%', :text, '%')
                        or address.country_name like concat('%', :text, '%')
                        or address.country_state like concat('%', :text, '%')
                        or address.district like concat('%', :text, '%')
                        or address.number like concat('%', :text, '%')
                        or address.street like concat('%', :text, '%')
                        or address.postal_code like concat('%', :text, '%')
                    order by address.country_name;""")
    Page<Contact> findContactsByAddressContainsIgnoreCase(@Param("text") String text, Pageable pageable);

    @Query(nativeQuery = true,
            value = """
                    select contact.*
                    from contact
                        inner join contact_email on contact.id = contact_email.contact_id
                        inner join email on contact_email.email_id = email.id
                    where email.email_username like concat('%', :text, '%')
                        or email.email_domain like concat('%', :text, '%')
                    order by contact.first_name;""")
    Page<Contact> findContactsByEmailContainsIgnoreCase(@Param("text") String text, Pageable pageable);
}
