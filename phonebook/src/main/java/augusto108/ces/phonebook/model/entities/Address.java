package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.datatypes.City;
import augusto108.ces.phonebook.model.enums.AddressType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "address")
@JsonIgnoreProperties(value = {"id"})
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

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

    public Address() {
    }

    public Address(Integer id,
                   String street,
                   String number,
                   String complement,
                   String district,
                   String postalCode,
                   City city,
                   AddressType addressType) {
        this.id = id;
        this.street = street;
        this.number = number;
        this.complement = complement;
        this.district = district;
        this.postalCode = postalCode;
        this.city = city;
        this.addressType = addressType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
