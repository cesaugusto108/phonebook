package augusto108.ces.phonebook.repository;

import augusto108.ces.phonebook.model.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
