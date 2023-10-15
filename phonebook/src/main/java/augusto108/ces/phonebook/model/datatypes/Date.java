package augusto108.ces.phonebook.model.datatypes;

import augusto108.ces.phonebook.model.enums.DateType;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Embeddable
public class Date {

    @Column(name = "date")
    private java.util.Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "date_type", length = 15)
    private DateType dateType;

    public java.util.Date getDate() {
        return date;
    }

    public void setDate(java.util.Date date) {
        this.date = date;
    }

    public DateType getDateType() {
        return dateType;
    }

    public void setDateType(DateType dateType) {
        this.dateType = dateType;
    }
}
