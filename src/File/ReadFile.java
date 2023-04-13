package File;

import Contact.Contact;

import java.io.*;
import java.util.ArrayList;

public class ReadFile<T> {
    private String fileName;

    public ReadFile(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<T> read() {
        ArrayList<T> Contacts = new ArrayList<>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            Contacts = (ArrayList<T>) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Contacts;
    }
}
