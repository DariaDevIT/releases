package Interface;

import CaesarCipher.AnalysisText;
import FileService.FileService;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.List;

/* Console class extends ProcessActions and provides methods for interacting with the user via the console. */
public class Console extends ProcessActions {

    // process method initiates the processing of user commands.
    public void process() {
        Scanner scanner = new Scanner(System.in);
        String command = getCommandFromUser(scanner);
        String path = getPathFromUser(scanner);

        int key = 0;

        // If the command is not "BRUTE_FORCE", get the encryption/decryption key from the user.
        if (!command.equals("BRUTE_FORCE")) {
            FileService fileService = new FileService();
            AnalysisText analysisText = new AnalysisText(fileService.readAllBytes(path));
            key = getKeyFromUser(scanner, analysisText.getMaxKey());
        }

        // Process the command with the provided file path and key.
        processCommand(command, path, key);
    }

    /**
     * Prompts the user to enter a valid file path.
     */
    private String getPathFromUser(Scanner scanner) {
        String path;
        do {
            path = getDataFromUser(scanner, "Enter a path to file: ");
        } while (!isValidFilePath(path));
        return path;
    }

    /**
     * Checks if the provided file path exists, and it is a file.
     */
    private static boolean isValidFilePath(String filePath) {
        File file = new File(filePath);
        return file.exists() && file.isFile();
    }

    /**
     * Prompts the user to enter data with the given message.
     */
    private String getDataFromUser(Scanner scanner, String message) {
        String input;
        do {
            System.out.println(message);
            input = scanner.nextLine();
        } while (input.isEmpty());
        return input;
    }

    /**
     * Prompts the user to enter a valid command ("ENCRYPT", "DECRYPT", "BRUTE_FORCE").
     *
     * @param scanner Scanner object for user input.
     * @return The user-entered command.
     */
    private String getCommandFromUser(Scanner scanner) {
        List<String> commandsList = Arrays.asList("ENCRYPT", "DECRYPT", "BRUTE_FORCE");
        String command;
        do {
            command = getDataFromUser(scanner, "Enter command - \"ENCRYPT\", \"DECRYPT\", \"BRUTE_FORCE\": ");
        } while (!commandsList.contains(command));
        return command;
    }

    /**
     * Prompts the user to enter a valid encryption/decryption key within the specified range.
     *
     * @param scanner Scanner object for user input.
     * @param maxKey  Maximum allowed key value.
     * @return The valid encryption/decryption key entered by the user.
     */
    private int getKeyFromUser(Scanner scanner, int maxKey) {
        int key;
        do {
            System.out.println("Please enter a valid key (1-" + (maxKey - 1) + "): ");
            while (!scanner.hasNextInt()) {
                System.out.println("Please enter a valid key (1-" + (maxKey - 1) + "): ");
                scanner.next();
            }
            key = scanner.nextInt();
        } while (key <= 0 || key >= maxKey);
        return key;
    }
}
