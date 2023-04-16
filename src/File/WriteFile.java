package File;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class WriteFile<T> {
    private String filePath;

    public WriteFile(String filePath) {
        this.filePath = filePath;
    }

    public void write(ArrayList<T> objects) {
        ArrayList<T> oldData = new ArrayList<>();
//        try {
//            ReadFile<T> data = new ReadFile<T>(filePath);
//            oldData = data.read();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        oldData.addAll(objects);
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(objects);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
