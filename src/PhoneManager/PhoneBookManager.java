package PhoneManager;

import Contact.Contact;
import Contact.Type;
import File.ReadFile;
import File.WriteFile;
import Phone.Phone;
import Phone.IPhone;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class PhoneBookManager extends Phone implements IPhone {
    private String filePatch = "F:\\Code Gym\\Module 2\\miniTest.week4\\src\\File\\thuoctinh.txt";
    private ArrayList<Contact> contacts;
    private ReadFile<Contact> contactReadFile;
    private WriteFile<Contact> contactWriteFile;
Scanner sc = new  Scanner(System.in);
    public PhoneBookManager() {
        contacts = new ArrayList<>();
        File file = new File(filePatch);
            if (!file.exists()) {
               try {
                   file.createNewFile();
               }catch (Exception e) {
                   e.printStackTrace();
               }
            }
        contactReadFile = new ReadFile<Contact>(this.filePatch);
        contactWriteFile = new WriteFile<>(this.filePatch);
    }
    @Override
    public void searchPhone(String name) {
        contactReadFile.read();
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
        contactReadFile.read();
        contacts.sort(Comparator.comparing(Contact::getName));
        contactWriteFile.write(contacts);
    }

    @Override
    public void display(Type type) {
        contactReadFile.read();
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
        contactReadFile.read();
        if (contact == null) {
            throw new IllegalArgumentException("Liên hệ không để trống. ");
        }
        if (contact.getPhoneNumber().isEmpty()) {
            throw new IllegalArgumentException("Không để trống số điện thoại.");
        }
        boolean check = contacts.stream().anyMatch(c -> c.getPhoneNumber().equals(contact.getPhoneNumber()));
        if (!check) contacts.add(contact);
        System.out.println("Đã thêm liên hệ thành công.");
        contactWriteFile.write(contacts);
    }

    @Override
    public void removePhone(String name) {
        searchPhone(name);
        System.out.println("Nhập ID của liên hệ bạn muốn xóa.");
        String id = sc.nextLine();
        contacts. removeIf(contact -> contact.getTypeId().equals(id));
        System.out.println("Đã xóa thành công.");
        contactWriteFile.write(contacts);
    }
    @Override
    public void updatePhone(String name, String newPhone) {
        searchPhone(name);
        System.out.println("Nhập ID của liên hệ bạn muốn cập nhập.");
        String id = sc.nextLine();
        for (int i =0; i < contacts.size(); i++) {
            if (contacts.get(i).equals(id)) {
                contacts.get(i).setPhoneNumber(newPhone);
            }
        }
        System.out.println("Đã cập nhật thành công.");
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
}
