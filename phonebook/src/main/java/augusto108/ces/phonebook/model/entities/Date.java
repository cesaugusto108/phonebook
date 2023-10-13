package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.enums.DateType;

public class Date {

    private java.util.Date date;
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
