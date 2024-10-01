package augusto108.ces.phonebook.model.repositories;

import augusto108.ces.phonebook.model.entities.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional(readOnly = true)
@Repository
public interface ContactRepository extends JpaRepository<Contact, UUID>
{

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
					SELECT contact.*
					FROM contact
					    INNER JOIN contact_telephone ON contact.id = contact_telephone.contact_id
					    INNER JOIN telephone ON contact_telephone.telephone_id = telephone.id
					WHERE telephone.country_code LIKE CONCAT('%', :number, '%')
					    OR telephone.area_code LIKE CONCAT('%', :number, '%')
					    OR telephone.number LIKE CONCAT('%', :number, '%')
					ORDER BY telephone.number;""")
	Page<Contact> findContactsByTelephoneContains(@Param("number") String number, Pageable pageable);

	@Query(nativeQuery = true,
			value = """
					SELECT contact.*
					FROM contact
					    INNER JOIN contact_address ON contact.id = contact_address.contact_id
					    INNER JOIN address ON contact_address.address_id = address.id
					WHERE address.city_name LIKE CONCAT('%', :text, '%')
					    OR address.complement LIKE CONCAT('%', :text, '%')
					    OR address.country_name LIKE CONCAT('%', :text, '%')
					    OR address.country_state LIKE CONCAT('%', :text, '%')
					    OR address.district LIKE CONCAT('%', :text, '%')
					    OR address.number LIKE CONCAT('%', :text, '%')
					    OR address.street LIKE CONCAT('%', :text, '%')
					    OR address.postal_code LIKE CONCAT('%', :text, '%')
					ORDER BY address.country_name;""")
	Page<Contact> findContactsByAddressContainsIgnoreCase(@Param("text") String text, Pageable pageable);

	@Query(nativeQuery = true,
			value = """
					SELECT contact.*
					FROM contact
					    INNER JOIN contact_email ON contact.id = contact_email.contact_id
					    INNER JOIN email ON contact_email.email_id = email.id
					WHERE email.email_username LIKE CONCAT('%', :text, '%')
					    OR email.email_domain LIKE CONCAT('%', :text, '%')
					ORDER BY contact.first_name;""")
	Page<Contact> findContactsByEmailContainsIgnoreCase(@Param("text") String text, Pageable pageable);
}
