package File;


import java.io.*;
import java.util.ArrayList;

public class ReadFile<T> {
    private final String filePath;

    public ReadFile(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<T> read() {
        ArrayList<T> data = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (ArrayList<T>) ois.readObject();
            fis.close();
            ois.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return data;
    }
}
