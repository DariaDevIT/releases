package CaesarCipher;

import java.util.ArrayList;
import java.util.HashMap;
/* Class for decrypting Caesar cipher. */
public class Decrypt extends ProcessCrypt {
    public Decrypt(char[] bufferedData, int key) {
        super(bufferedData, key);
    }

    /**
     * Processes a symbol during decryption.
     * @param character The symbol to be processed.
     * @return The decrypted symbol.
     */
    @Override
    protected char processSymbol(char character) {
        int characterIndex = ABC.Symbol.LIST.indexOf(character);
        characterIndex = (characterIndex - key) % ABC.Symbol.LIST.size();
        if (characterIndex < 0) characterIndex += ABC.Symbol.LIST.size();
        return ABC.Symbol.LIST.get(characterIndex);
    }

    /**
     * Processes a letter during decryption.
     * @param currentUpperChar The uppercase version of the letter to be processed.
     * @param alphabet The alphabet to be used for processing.
     * @param isLowChar Indicates if the original letter was lowercase.
     * @return The decrypted letter.
     */
    @Override
    protected char processLetter(char currentUpperChar, ArrayList<Character> alphabet, boolean isLowChar) {
        int indexChar = alphabet.indexOf(currentUpperChar);
        int indexCrypt = (indexChar - key) % alphabet.size();
        if (indexCrypt < 0) indexCrypt += alphabet.size();
        char decryptedChar = alphabet.get(indexCrypt);
        return isLowChar ? Character.toLowerCase(decryptedChar) : decryptedChar;
    }
}