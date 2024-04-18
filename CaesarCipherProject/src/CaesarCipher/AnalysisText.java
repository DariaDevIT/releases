package CaesarCipher;

import java.util.ArrayList;

/* Class for analyzing text for Caesar cipher.*/
public class AnalysisText {
    char[] bufferText;

    public AnalysisText(char[] bufferText) {
        this.bufferText = bufferText;
    }

    /**
     * Calculates the number of spaces in the text.
     *
     * @return The number of spaces.
     */
    public int getNumSpace() {
        int countWhitespace = 0;
        for (char code : bufferText) {
            if (code == ' ') {
                countWhitespace++;
            }
        }
        return countWhitespace;
    }

    /**
     * Determines if the text prefers English characters over Ukrainian characters.
     *
     * @return True if the text prefers English, false otherwise.
     */
    public boolean isPreferenceEngText() {
        int countEngChar = 0;
        int countUaChar = 0;
        for (char c : bufferText) {
            if (ABC.Eng.LIST.contains(c)) countEngChar++;
            if (ABC.Ua.LIST.contains(c)) countUaChar++;
        }
        return countEngChar > countUaChar;
    }

    /**
     * Gets the preferred alphabet based on the language preference of the text.
     *
     * @return The preferred alphabet.
     */
    public ArrayList<Character> getPreferenceAbc() {
        return isPreferenceEngText() ? ABC.Eng.LIST : ABC.Ua.LIST;
    }

    /**
     * Gets the maximum possible key based on the language preference of the text.
     *
     * @return The maximum possible key.
     */
    public int getMaxKey() {
        return isPreferenceEngText() ? ABC.Eng.LIST.size() : ABC.Ua.LIST.size();
    }

    private int keyMaxNumSpaces;

    /**
     * Calculates the maximum number of spaces and the key associated with it.
     *
     * @return The maximum number of spaces.
     */
    public int getMaxNumSpaces() {
        int numSpaces = 0, key = 0;
        int maxKey = getMaxKey();
        while (key <= maxKey) {
            Decrypt newDecrypt = new Decrypt(bufferText, key);
            char[] res = newDecrypt.process();
            AnalysisText a = new AnalysisText(res);
            int currentNumSpace = a.getNumSpace();
            if (currentNumSpace > numSpaces) {
                numSpaces = currentNumSpace;
                keyMaxNumSpaces = key;
            }
            key++;
        }
        return numSpaces;
    }

    /**
     * Gets the key associated with the maximum number of spaces.
     *
     * @return The key associated with the maximum number of spaces.
     */
    public int getKeyMaxNumSpaces() {
        getMaxNumSpaces();
        return keyMaxNumSpaces;
    }
}
