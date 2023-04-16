package PhoneManager;

import Contact.Contact;
import Contact.Type;

import File.ReadFile;
import File.WriteFile;

import Phone.Phone;
import Phone.IPhone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PhoneBookManager extends Phone implements IPhone {
    private String filePatch = "./src/File/thuoctinh.bin";
    private ArrayList<Contact> contacts;
    private ArrayList<Type> Type;
    private ReadFile<Contact> contactReadFile;
    private WriteFile<Contact> contactWriteFile;
    Scanner sc = new Scanner(System.in);

    public PhoneBookManager() {
        contacts = new ArrayList<>();
        contactReadFile = new ReadFile<Contact>(filePatch);
        contacts = contactReadFile.read();
        contactWriteFile = new WriteFile<>(filePatch);
    }

    public boolean checkName(String name) {
        boolean check = false;
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                check = true;
            }
        } return check;
    }

    @Override
    public void searchPhone(String name) {
        ArrayList<Contact> foundContacts = new ArrayList<>();
        for (Contact contact : contacts) {
            if (contact.getName().equalsIgnoreCase(name)) {
                foundContacts.add(contact);
            }
        }
        if (foundContacts.isEmpty()) {
            System.out.println("Không tìm thấy liên hệ nào...");
        } else {
            System.out.println("Các liên hệ có tên " + name + " là:");
            foundContacts.forEach(System.out::println);
        }
    }

    @Override
    public void sort() {
        contacts.sort(Comparator.comparing(Contact::getName));
        contactWriteFile.write(contacts);
    }

    @Override
    public void display(Type type) {
        contacts = contactReadFile.read();
        if (type == null) {
            for (int i = 0; i < contacts.size(); i++) {
                System.out.println(contacts.get(i));
            }
        } else {
            boolean check = false;
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getTypeName().equals(type.getName())) {
                    System.out.println(contacts.get(i));
                    check = true;
                }
            }
            if (!check) {
                System.out.println("không có kiểu này.");
            }
        }
    }

    @Override
    public void insertPhone(Contact contact) {
        if (contact.getPhoneNumber().isEmpty()) {
            System.out.println("Không để trống số điện thoại.");
            return;
        }
        boolean check = false;
        for (Contact c : contacts) {
            if (c.getPhoneNumber().equals(contact.getPhoneNumber())) {
                check = true;
                break;
            }
        }
        if (check) {
            System.out.println("Số điện thoại đã tồn tại");
        } else {
            contacts.add(contact);
            System.out.println("Đã thêm liên hệ thành công.");
            contactWriteFile.write(contacts);
        }
    }

    @Override
    public void removePhone(String name) {
        searchPhone(name);
        System.out.println("Nhập ID của liên hệ bạn muốn xóa.");
        String id = sc.nextLine();
        System.out.println("Bạn có chắc muốn xóa liên hệ này? (Y/N)");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            contacts.removeIf(contact -> contact.getTypeId().equals(id));
            System.out.println("Đã xóa thành công.");
            contactWriteFile.write(contacts);
        } else {
            System.out.println("Không xóa liên hệ này nữa...");
        }
    }

    @Override
    public void updatePhone(String name, String newPhone) {
        searchPhone(name);
        System.out.println("Nhập ID của liên hệ bạn muốn cập nhập.");
        String id = sc.nextLine();
        System.out.println("Bạn có chắc chắn muốn thay đổi số điện thoại của liên hệ này? (Y/N)");
        String confirm = sc.nextLine();
        if (confirm.equalsIgnoreCase("y")) {
            for (Contact contact : contacts) {
                if (contact.getTypeId().equals(id)) {
                    contact.setPhoneNumber(newPhone);
                    break;
                }
            }
            System.out.println("Đã cập nhật thành công.");
        } else {
            System.out.println("Không cập nhật số điện thoại của liên hệ này.");
        }
        contactWriteFile.write(contacts);
    }

    public boolean checkId(String id) {
        boolean isExit = false;
        for (Contact contact : contacts) {
            if (contact.getTypeId().equals(id)) {
                isExit = true;
                break;
            }
        }
        return isExit;
    }

    public boolean checkNumber(String number) {
        boolean isExit = false;
        for (Contact contact : contacts) {
            if (contact.getPhoneNumber().equals(number)) {
                isExit = true;
            }
        }
        return isExit;
    }
}
