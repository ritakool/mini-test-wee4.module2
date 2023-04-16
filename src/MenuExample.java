import Contact.Contact;
import Contact.Type;
import PhoneManager.PhoneBookManager;

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
                case 0:
                    System.out.println("cám ơn bạn đã sử dụng chương trình. ");
                    for (int i = 1; i <= 6; i += 2) {
                        for (int j = 1; j <= 6 - i; j += 2) {
                            System.out.print("  ");
                        }
                        for (int j = 1; j <= i; j++) {
                            System.out.print("* ");
                        }
                        for (int j = 1; j <= 6 - i; j++) {
                            System.out.print("  ");
                        }
                        for (int j = 1; j <= i; j++) {
                            System.out.print("* ");
                        }
                        System.out.println();
                    }
                    for (byte i = 1; i <= 6; i++) {

                        for (byte j = 1; j <= i; j++) {
                            System.out.print("  ");
                        }
                        for (byte j = i; j < 6; j++) {
                            System.out.print("* ");
                        }
                        for (byte j = i; j <= 6; j++) {
                            System.out.print("* ");
                        }
                        System.out.print("\n");
                    }
                    return;
                case 1:
                    foundContact();
                    break;
                case 2:
                    sortContact();
                    break;
                case 3:
                    showContact();
                    break;
                case 4:
                    System.out.println("Thêm liên hệ mới: ");
                    addContact();
                    break;
                case 5:
                    deleteContact();
                    break;
                case 6:
                    System.out.println("Cập nhập liên hệ: ");
                    updateContact();
                    break;
            }
        }
    }

    public static void addContact() {
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
        try {
            switch (choice) {
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
        } catch (Exception e) {
            e.printStackTrace();
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

    public static void showContact() {
        System.out.println("nhập kiểu bạn muốn phân loại: ");
        String Type = scanner.nextLine();
        if (Type.trim().isEmpty()) {
            phoneBookManager.display(null);
        } else {
            Type type = new Type(Type);
            phoneBookManager.display(type);
        }
    }

    public static void sortContact() {
        phoneBookManager.sort();
        System.out.println("danh bạ sau khi sắp xếp là: ");
        phoneBookManager.display(null);
    }

    public static void deleteContact() {
        System.out.println("Nhập tên liên hệ bạn muốn xóa: ");
        String name = scanner.nextLine();
        phoneBookManager.removePhone(name);
    }

    public static void updateContact() {
        System.out.println("Nhập tên liên hệ bạn muốn sửa đổi");
        String name = scanner.nextLine();
        System.out.println("Nhập số điện thoại: ");
        String tel = scanner.nextLine();
        phoneBookManager.updatePhone(name, tel);
    }
}