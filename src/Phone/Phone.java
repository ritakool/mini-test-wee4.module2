package Phone;

import Contact.Contact;
import Contact.Type;

public abstract class Phone {
    public abstract void display(Type type);
    public abstract void insertPhone(Contact contact);
    public abstract void removePhone(String name);
    abstract void updatePhone(String name, String newPhone);
}
