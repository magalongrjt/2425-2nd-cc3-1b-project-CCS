import java.io.*;
import java.util.List;

public class DataManager {

    public static void saveData(List<Team> teams, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(teams);
        }
    }

    public static List<Team> loadData(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Team>) ois.readObject();
        }
    }
} 