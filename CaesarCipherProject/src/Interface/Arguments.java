package Interface;

import CaesarCipher.AnalysisText;
import FileService.FileService;

import java.io.File;
import java.util.Arrays;
import java.util.List;
/* Arguments class extends ProcessActions and provides methods for processing command line arguments. */
public class Arguments extends ProcessActions {

    /**
     * Process the command line arguments.
     * @param args The array of command line arguments.
     */
    public void process(String[] args) {
        String command = getCommandFromUser(args[0]);
        String path = getPathFromUser(args[1]);
        int key = 0;

        // If the command is not "BRUTE_FORCE", get the key from command line arguments.
        if (command != null && !command.equals("BRUTE_FORCE")) {
            key = getKeyFromUser(args[2]);

            // Validate the key against the maximum key value.
            FileService fileService = new FileService();
            AnalysisText analysisText = new AnalysisText(fileService.readAllBytes(path));

            if (key <= 0 || key >= analysisText.getMaxKey()) {
                System.err.println("Max key is: " + (analysisText.getMaxKey() - 1));
                System.exit(1);
            }
        }

        // Process the command with the provided file path and key.
        if (path != null && command != null) {
            processCommand(command, path, key);
        }
    }

    /**
     * Validates the file path extracted from command line arguments.
     * @param arg The file path extracted from command line arguments.
     * @return The validated file path, or null if invalid.
     */
    private String getPathFromUser(String arg) {
        if (isValidFilePath(arg)) return arg;
        return null;
    }

    /**
     * Checks if the provided file path exists and it is a file.
     * @param filePath The file path to be validated.
     * @return True if the file path is valid, false otherwise.
     */
    private static boolean isValidFilePath(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    /**
     * Validates the command extracted from command line arguments.
     * @param arg The command extracted from command line arguments.
     * @return The validated command, or null if invalid.
     */
    private String getCommandFromUser(String arg) {
        List<String> commandsList = Arrays.asList("ENCRYPT", "DECRYPT", "BRUTE_FORCE");
        if (commandsList.contains(arg)) {
            return arg;
        }
        return null;
    }

    /**
     * Parses the key extracted from command line arguments.
     * @param arg The key extracted from command line arguments.
     * @return The parsed key as an integer.
     */
    private int getKeyFromUser(String arg) {
        try {
            return Integer.parseInt(arg);
        } catch (NumberFormatException e) {
            // Throw a runtime exception if key is not a valid integer.
            throw new RuntimeException(e);
        }
    }
}
