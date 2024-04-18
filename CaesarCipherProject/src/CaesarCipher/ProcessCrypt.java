package CaesarCipher;

import java.util.ArrayList;

/* Abstract class providing the framework for cryptographic processes. */
abstract class ProcessCrypt {
    protected int key;
    protected char[] bufferedData;

    protected ProcessCrypt(char[] bufferedData, int key) {
        this.bufferedData = bufferedData;
        this.key = key;
    }

    /**
     * Performs the cryptographic process on the data.
     * @return An array of characters representing the processed data.
     */
    public char[] process() {
        char[] result = new char[bufferedData.length];
        // Process each character in the data.
        for (int i = 0; i < bufferedData.length; i++) {
            result[i] = processChars(bufferedData[i]);
        }
        return result;
    }

    /**
     * Processes individual characters based on their type (letter or symbol).
     * @param currentCharCode The character code to be processed.
     * @return The processed character.
     */
    private char processChars(int currentCharCode) {
        char character = (char) currentCharCode;
        // If the character is a letter, call processLetters method.
        if (Character.isLetter(character)) {
            character = processLetters(character);
        }
        // If the character is a symbol, call processSymbol method.
        else if (ABC.Symbol.LIST.contains(character)) {
            character = processSymbol(character);
        }
        return character;
    }

    /**
     * Processes letters, distinguishing between English and Ukrainian alphabets.
     * @param character The letter to be processed.
     * @return The processed letter.
     */
    private char processLetters(char character) {
        char upperCase = Character.toUpperCase(character);
        // Check if it belongs to English or Ukrainian alphabet.
        boolean isEngLanguage = ABC.Eng.LIST.contains(upperCase);
        boolean isUaLanguage = ABC.Ua.LIST.contains(upperCase);

        if (isEngLanguage || isUaLanguage) {
            // Check if the original character was lowercase.
            boolean isLowerCase = Character.isLowerCase(character);

            ArrayList<Character> alphabet = isEngLanguage ? ABC.Eng.LIST : ABC.Ua.LIST;
            // Call the appropriate method to process the letter.
            character = processLetter(upperCase, alphabet, isLowerCase);
        }
        return character;
    }

    /**
     * Abstract method to be implemented by subclasses for processing symbols.
     * @param character The symbol to be processed.
     * @return The processed symbol.
     */
    protected abstract char processSymbol(char character);

    /**
     * Abstract method to be implemented by subclasses for processing letters.
     * @param currentUpperChar The uppercase version of the letter to be processed.
     * @param alphabet The alphabet to be used for processing.
     * @param isLowChar Indicates if the original letter was lowercase.
     * @return The processed letter.
     */
    protected abstract char processLetter(char currentUpperChar, ArrayList<Character> alphabet, boolean isLowChar);
}
