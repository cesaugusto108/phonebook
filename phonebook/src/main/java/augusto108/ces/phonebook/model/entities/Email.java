package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.base.BaseId;
import augusto108.ces.phonebook.model.enums.EmailType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "email")
@JsonIgnoreProperties(value = {"id"})
public class Email extends BaseId {

    @Column(name = "email_username", nullable = false, length = 30)
    private String username;

    @Column(name = "email_domain", nullable = false, length = 30)
    private String domain;

    @Enumerated(EnumType.STRING)
    @Column(name = "email_type", length = 15)
    private EmailType emailType;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }
}
