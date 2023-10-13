package augusto108.ces.phonebook.model.entities;

import augusto108.ces.phonebook.model.enums.InstantMessengerType;

public class InstantMessenger {

    private Integer id;
    private String username;
    private InstantMessengerType imType;

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

    public InstantMessengerType getImType() {
        return imType;
    }

    public void setImType(InstantMessengerType imType) {
        this.imType = imType;
    }
}
