package FileService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/* FileService class provides methods for reading from and writing to files. */
public class FileService {

    /**
     * Reads all bytes from the specified file.
     * @param pathIn The path to the input file.
     * @return An array of characters representing the contents of the file.
     */
    public char[] readAllBytes(String pathIn) {
        List<Character> characters = new ArrayList<>();

        try (FileReader reader = new FileReader(new File(pathIn))) {
            char[] buffer = new char[1024];
            int numCharsRead;

            // Read characters from the file and add them to the list.
            while ((numCharsRead = reader.read(buffer)) != -1) {
                for (int i = 0; i < numCharsRead; i++) {
                    characters.add(buffer[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert the list of characters to a char array.
        char[] charactersArray = new char[characters.size()];
        for (int i = 0; i < characters.size(); i++) {
            charactersArray[i] = characters.get(i);
        }

        return charactersArray;
    }

    /**
     * Saves the specified character array to the specified file.
     * @param bytes The character array to be saved.
     * @param file The file to which the character array should be saved.
     */
    public void saveFile(char[] bytes, File file) {
        try {
            // Create a BufferedWriter to write to the file.
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // Write the character array to the file.
            writer.write(bytes);

            // Close the writer.
            writer.close();
        } catch (IOException e) {
            // Print error message if an exception occurs while saving the file.
            System.err.println("Error to save file: " + e.getMessage());
        }
    }

}
