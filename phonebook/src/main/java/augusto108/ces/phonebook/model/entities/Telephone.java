package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.enums.TelephoneType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "telephone")
@JsonIgnoreProperties(value = {"id"})
public class Telephone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "country_code", length = 3)
    private String countryCode;

    @Column(name = "area_code", length = 4)
    private String areaCode;

    @Column(name = "number", nullable = false, length = 20)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "telephone_type", length = 15)
    private TelephoneType telephoneType;

    public Telephone() {
    }

    public Telephone(Integer id, String countryCode, String areaCode, String number, TelephoneType telephoneType) {
        this.id = id;
        this.countryCode = countryCode;
        this.areaCode = areaCode;
        this.number = number;
        this.telephoneType = telephoneType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public TelephoneType getTelephoneType() {
        return telephoneType;
    }

    public void setTelephoneType(TelephoneType telephoneType) {
        this.telephoneType = telephoneType;
    }
}
