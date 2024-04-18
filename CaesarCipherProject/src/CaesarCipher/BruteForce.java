package CaesarCipher;

/* Class for performing brute force attack to decrypt Caesar cipher. */
public class BruteForce {
    private final char[] bufferedData;
    private int key;
    private AnalysisText analysisText;

    public BruteForce(char[] bufferedData) {
        this.bufferedData = bufferedData;
    }

    /**
     * Performs brute force attack to decrypt the encrypted data.
     * @return The decrypted data.
     */
    public char[] process() {
        // Analyze the encrypted data to determine the most probable key.
        analysisText = new AnalysisText(bufferedData);
        key = analysisText.getKeyMaxNumSpaces();
        // Decrypt the data using the determined key.
        Decrypt decrypt = new Decrypt(bufferedData, key);
        return decrypt.process();
    }

    /**
     * Gets the maximum number of spaces found during the brute force attack.
     * @return The maximum number of spaces.
     */
    public int getMaxNunSpaces() {
        return analysisText.getMaxNumSpaces();
    }

    /**
     * Gets the key used for decryption.
     * @return The decryption key.
     */
    public int getKey() {
        return key;
    }
}
