package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.enums.EmailType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "email")
@JsonIgnoreProperties(value = {"id"})
public class Email {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "email_username", nullable = false, length = 30)
    private String username;

    @Column(name = "email_domain", nullable = false, length = 30)
    private String domain;

    @Enumerated(EnumType.STRING)
    @Column(name = "email_type", length = 15)
    private EmailType emailType;

    public Email() {
    }

    public Email(Integer id, String username, String domain, EmailType emailType) {
        this.id = id;
        this.username = username;
        this.domain = domain;
        this.emailType = emailType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
