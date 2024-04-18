package CaesarCipher;

import java.util.ArrayList;

/* Class for encrypting Caesar cipher. */
public class Encrypt extends ProcessCrypt {

    public Encrypt(char[] bufferedData, int key) {
        super(bufferedData, key);
    }

    /**
     * Processes a symbol during encryption.
     * @param character The symbol to be processed.
     * @return The encrypted symbol.
     */
    @Override
    protected char processSymbol(char character) {
        int characterIndex = ABC.Symbol.LIST.indexOf(character);
        int newCharacterIndex = (characterIndex + key) % ABC.Symbol.LIST.size();
        return ABC.Symbol.LIST.get(newCharacterIndex);
    }

    /**
     * Processes a letter during encryption.
     * @param currentUpperChar The uppercase version of the letter to be processed.
     * @param alphabet The alphabet to be used for processing.
     * @param isLowChar Indicates if the original letter was lowercase.
     * @return The encrypted letter.
     */
    @Override
    protected char processLetter(char currentUpperChar, ArrayList<Character> alphabet, boolean isLowChar) {
        int indexChar = alphabet.indexOf(currentUpperChar);
        int indexCrypt = (indexChar + key) % alphabet.size();
        char encryptedChar = alphabet.get(indexCrypt);
        return isLowChar ? Character.toLowerCase(encryptedChar) : encryptedChar;
    }
}