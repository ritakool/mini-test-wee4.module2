package PhoneManager;

import Contact.Contact;
import Contact.Type;
import File.ReadFile;
import File.WriteFile;
import Phone.Phone;
import Phone.IPhone;

import java.util.ArrayList;
import java.util.Comparator;

public class PhoneBookManager extends Phone implements IPhone {
    private ArrayList<Contact> contacts;
    private ArrayList<Type> types;
    private ReadFile<Contact> contactReadFile;
    private WriteFile<Contact> contactWriteFile;
    private ReadFile<Type> typeReadFile;
    private WriteFile<Type> typeWriteFile;
    public PhoneBookManager() {
        contacts = new ArrayList<>();
        types = new ArrayList<>();
        contactReadFile = new ReadFile<Contact>("danhba.txt");
        contactWriteFile = new WriteFile<>("danhba.txt");
        typeReadFile = new ReadFile<Type>("thuoctinh.txt");
        typeWriteFile = new WriteFile<>("thuoctinh.txt");
    }
    @Override
    public void searchPhone(String name) {
        boolean found = false;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).getName().equalsIgnoreCase(name)) {
                System.out.println(contacts.get(i));
                found = true;
            }
        } if (!found) System.out.println("Không có liên hệ nào...");
    }

    @Override
    public void sort() {
        contacts.sort(Comparator.comparing(Contact::getName));
    }

    @Override
    public void display(Type type) {
        if (type == null) {
            contacts.forEach(System.out::println);
        }
        else {
            contacts.stream()
                    .filter(contact -> contact.getType().equals(type))
                    .forEachOrdered(System.out::println);
        }
    }

    @Override
    public void insertPhone(Contact contact) {
        if (contact == null) {
            return;
        }
        boolean check = false;
        for (Contact c : contacts ) {
            if (c.getPhoneNumber().equals(contact.getPhoneNumber())) {
                check = true; // liên hệ đã tồn tại.
                break;
            }
        }
        if (!check) {
            contacts.add(contact);
        }
        contactWriteFile.write(contacts);
    }

    @Override
    public void removePhone(String name) {

    }

    @Override
    public void updatePhone(String name, String newPhone) {

    }
}
