import Contact.Contact;
import Contact.Type;
import File.ReadFile;
import PhoneManager.PhoneBookManager;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
public class MenuExample {
    static Scanner scanner = new Scanner(System.in);
    static PhoneBookManager phoneBookManager = new PhoneBookManager();

    public static void main(String[] args) {
        while (true) {
            System.out.println("======== DANH BẠ ĐIỆN THOẠI ========");
            System.out.println("1. Tìm kiếm liên hệ theo tên");
            System.out.println("2. Sắp xếp danh bạ theo tên");
            System.out.println("3. Hiển thị danh sách liên hệ theo loại");
            System.out.println("4. Thêm liên hệ mới");
            System.out.println("5. Xóa liên hệ");
            System.out.println("6. Cập nhật liên hệ");
            System.out.println("0. Thoát chương trình");
            System.out.println("====================================");
            System.out.println("Nhập lựa chọn của bạn: ");

            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 4:
                    System.out.println("Thêm liên hệ mới: ");
                    addContact();
            }
        }
    }
    public static void addContact () {
        String id;
        do {
            System.out.println("Nhap id: ");
            id = scanner.nextLine();
        } while (phoneBookManager.checkId(id));
        String name = null;
        System.out.println(""" 
                               Phân loại: 1. Gia đình
                                          2. Bạn bè
                                          3. Công ty   
                                          4. Thêm mới   """);
        byte choice = scanner.nextByte();
        scanner.nextLine();
        switch (choice){
            case 1:
                name = "Gia đình";
                break;
            case 2:
                name = "Bạn bè";
                break;
            case 3:
                name = "Công ty";
                break;
            case 4:
                System.out.println("Nhập tên loại liên hệ mới:");
                name = scanner.nextLine();
                break;
        }
        Type type = new Type(id, name);
        System.out.println("Nhập tên: ");
        String Name = scanner.nextLine();
        System.out.println("Nhập số điện thoại: ");
        String phoneNumber = scanner.nextLine();
        Contact contact = new Contact(Name, phoneNumber, type);
        phoneBookManager.insertPhone(contact);
    }
    public static void foundContact() {
        System.out.println("Nhập tên: ");
        String name = scanner.nextLine();
        phoneBookManager.searchPhone(name);
    }
}