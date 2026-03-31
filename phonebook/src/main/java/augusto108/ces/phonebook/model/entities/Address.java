package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.base.BaseId;
import augusto108.ces.phonebook.model.datatypes.City;
import augusto108.ces.phonebook.model.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
@JsonIgnoreProperties(value = {"id"})
public class Address extends BaseId {

	@Column(name = "street", nullable = false, length = 30)
	private String street;

	@Column(name = "number", nullable = false, length = 6)
	private String number;

	@Column(name = "complement", length = 30)
	private String complement;

	@Column(name = "district", length = 20)
	private String district;

	@Column(name = "postal_code", length = 15)
	private String postalCode;

	@Embedded
	private City city;

	@Enumerated(EnumType.STRING)
	@Column(name = "address_type", length = 15)
	private AddressType addressType;

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public AddressType getAddressType() {
		return addressType;
	}

	public void setAddressType(AddressType addressType) {
		this.addressType = addressType;
	}
}
