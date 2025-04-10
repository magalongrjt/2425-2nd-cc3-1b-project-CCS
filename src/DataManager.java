/**
 * Handles saving and loading team data to/from files.
 * Uses Java serialization for data storage.
 */
import java.io.*;
import java.util.List;

public class DataManager {

    /**
     * Saves teams to a file
     */
    public static void saveData(List<Team> teams, String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(teams);
        }
    }

    /**
     * Loads teams from a file
     */
    public static List<Team> loadData(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<Team>) ois.readObject();
        }
    }
} 