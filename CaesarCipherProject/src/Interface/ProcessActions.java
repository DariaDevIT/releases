package Interface;

import CaesarCipher.BruteForce;
import CaesarCipher.Decrypt;
import CaesarCipher.Encrypt;
import FileService.FileService;

import java.io.File;
/* Performs various actions such as encryption, decryption and brute force. */
public class ProcessActions {
    private char[] resultProcess;

    protected void processCommand(String command, String path, int key) {
        FileService fileService = new FileService();
        char[] bufferFile = fileService.readAllBytes(path);

        String nameFile = path.split("\\.")[0] + "[" + command + "]";

        switch (command) {
            case "ENCRYPT" -> {
                Encrypt encrypt = new Encrypt(bufferFile, key);
                resultProcess = encrypt.process();
            }
            case "DECRYPT" -> {
                Decrypt decrypt = new Decrypt(bufferFile, key);
                resultProcess = decrypt.process();
            }
            case "BRUTE_FORCE" -> {
                BruteForce bruteForce = new BruteForce(bufferFile);
                resultProcess = bruteForce.process();
                nameFile += "_" + bruteForce.getKey();
            }
        }
        nameFile += ".txt";
        File selectionFile = new File(nameFile);
        fileService.saveFile(resultProcess, selectionFile);
    }
}
