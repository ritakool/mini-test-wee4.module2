package Phone;

import Contact.Contact;
import Contact.Type;

public abstract class Phone {
    protected abstract void display(Type type);
    protected abstract void insertPhone(Contact contact);
    protected abstract void removePhone(String name);
    protected abstract void updatePhone(String name, String newPhone);
}
