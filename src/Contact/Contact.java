package Contact;

import java.io.Serializable;

public class Contact implements Serializable {
    private String name;
    private String phoneNumber;
    private Type type;

    public String getName() {
        return name;
    }

    public Contact() {
    }

    public Contact(String name, String phoneNumber, Type type) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getTypeId() {
        return type.getId();
    }

    public String getTypeName() {
        return type.getName();
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", " + type +
                '}';
    }
}
