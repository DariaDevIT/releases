package Interface;

import CaesarCipher.*;
import FileService.FileService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

/* GUI class represents the main graphical user interface for the Caesar Cipher application. */
public class GUI extends JFrame {
    /**
     * Width JFrame.
     */
    private final int WIDTH = 800;

    /**
     * Height JFrame.
     */
    private final int HEIGHT = 700;

    /**
     * Image icon for JFrame.
     */
    private ImageIcon icon = new ImageIcon("D:\\code\\Javarush\\Project1\\ArrayChars\\src\\Interface\\icon_cipher.png");

    /**
     * Buttons for opening and saving files.
     */
    private JButton openButton, saveButton;

    /**
     * Resultant processed text and input text.
     */
    private char[] resultProcess, inputText;

    /**
     * Text field for inputting the key.
     */
    private JTextField textField;

    /**
     * File filter for .txt files.
     */
    private final FileNameExtensionFilter FILTER = new FileNameExtensionFilter("txt", "txt");

    /**
     * Font for title.
     */
    private final Font FONT_TITLE = new Font("Verdana", Font.BOLD, 20);

    /**
     * Font for text.
     */
    private final Font FONT_TEXT = new Font("Verdana", Font.PLAIN, 12);

    /**
     * Panels for organizing components.
     */
    private JPanel leftPanel, buttonPanelEncDec, jPanelKey, bruteForce;

    /**
     * Labels for title and text about key.
     */
    private JLabel jLabelTitle, keyIs;

    /**
     * Label for "or" text.
     */
    private final JLabel JLABEL_OR = new JLabel("or");

    /**
     * Text areas for displaying information.
     */
    private JTextArea textArea, textDescription;

    /**
     * Flags for encryption, decryption, key validity, and file status.
     */
    private boolean isWasEncrypt, isWasDecrypt, validKey, isNewFileOpen;

    public GUI() {
        setTitle("Caesar Cipher");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Set center window

        setIconImage(icon.getImage()); // Set window icon

        initTextArea();

        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        iniLeftPanel();

        getContentPane().setLayout(new BorderLayout());// Set layout to BorderLayout
        getContentPane().add(jScrollPane, BorderLayout.CENTER); // Add scroll pane to center
        getContentPane().add(leftPanel, BorderLayout.WEST); // Add left panel to the west side
    }

    /**
     * Initialize text area with instructions.
     */
    private void initTextArea() {
        textArea = new JTextArea();
        textArea.setEditable(false);

        textArea.setText("""
                For encryption and decryption:
                1. Select a file (.txt).
                2. Enter the key.
                3. Choose an action.

                For a brute force attack:
                1. Select a file (.txt).
                2. Select Brute force.""");
        textArea.setForeground(Color.gray);
    }

    /**
     * Initialize components of the left panel.
     */
    private void iniLeftPanel() {
        initDescription(); // Initialize description
        initOpenSaveButtons(); // Initialize open and save buttons
        initPromptKey(); // Initialize key input components
        initEncDec(); // Initialize encryption and decryption buttons
        initBruteForce(); // Initialize brute force button

        fillTheLeftPanel(); // Fill the left panel with components
    }

    /**
     * Initialize description label and text area.
     */
    private void initDescription() {
        jLabelTitle = new JLabel("Caesar cipher");
        jLabelTitle.setVerticalAlignment(JLabel.CENTER);
        jLabelTitle.setHorizontalAlignment(JLabel.CENTER);
        jLabelTitle.setFont(FONT_TITLE);

        String jTextWelcome = """
                Welcome to Caesar, an assistant in encrypting and
                decrypting files using the famous Caesar cipher.

                Keep your files safe by encrypting them, or uncover
                secrets with ease with our powerful tool.
                """;
        textDescription = new JTextArea();
        textDescription.setText(jTextWelcome);
        textDescription.setFont(FONT_TEXT);
        textDescription.setBackground(jLabelTitle.getBackground());
    }

    /**
     * Initialize open and save buttons.
     */
    private void initOpenSaveButtons() {
        openButton = new JButton("Open");
        openButton.setBackground(Color.LIGHT_GRAY);
        openButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }
        });

        saveButton = new JButton("Save");
        saveButton.setBackground(Color.LIGHT_GRAY);

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }
        });

    }

    /**
     * Initialize components for key input.
     */
    private void initPromptKey() {
        jPanelKey = new JPanel();
        JLabel jLabelKey = new JLabel("Key: ");
        jLabelKey.setDisplayedMnemonic(KeyEvent.VK_N);
        keyIs = new JLabel();
        keyIs.setBorder(new EmptyBorder(0, 10, 0, 0));

        textField = new JTextField(5);
        jLabelKey.setLabelFor(textField);
        textField.setSize(80, 20);

        jPanelKey.add(jLabelKey, BorderLayout.WEST);
        jPanelKey.add(textField, BorderLayout.CENTER);
    }

    /**
     * Initialize encryption and decryption buttons.
     */
    private void initEncDec() {
        JButton encryptButton = new JButton("Encrypt");
        encryptButton.setBackground(Color.LIGHT_GRAY);
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encryptFile();
            }
        });

        JButton decryptButton = new JButton("Decrypt");
        decryptButton.setBackground(Color.LIGHT_GRAY);
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                decryptFile();
            }
        });

        buttonPanelEncDec = new JPanel();
        buttonPanelEncDec.add(encryptButton);
        buttonPanelEncDec.add(decryptButton);
    }

    /**
     * Initialize brute force button.
     */
    private void initBruteForce() {
        JButton bruteForceButton = new JButton("Brute Force");
        bruteForceButton.setBackground(Color.LIGHT_GRAY);
        bruteForceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bruteForce();
            }
        });

        bruteForce = new JPanel();
        bruteForce.add(bruteForceButton);
        bruteForce.add(keyIs);
    }

    /**
     * Fill the left panel with components.
     */
    private void fillTheLeftPanel() {
        leftPanel = new JPanel(new GridBagLayout());
        leftPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.anchor = GridBagConstraints.CENTER;

        gbc1.insets = new Insets(0, 5, 10, 5); // Spacing between components
        leftPanel.add(jLabelTitle, gbc1);

        gbc1.gridy++;
        leftPanel.add(textDescription, gbc1);

        gbc1.gridy++;
        leftPanel.add(openButton, gbc1);

        gbc1.gridy++;
        leftPanel.add(buttonPanelEncDec, gbc1);

        gbc1.gridy++;
        leftPanel.add(jPanelKey, gbc1);

        gbc1.gridy++;
        leftPanel.add(JLABEL_OR, gbc1);

        gbc1.gridy++;
        leftPanel.add(bruteForce, gbc1);

        gbc1.gridy++;
        leftPanel.add(saveButton, gbc1);
    }

    /**
     * Prompt for key input and validate.
     *
     * @param maxKey Maximum key, which depends on the text language.
     * @return Key from user.
     */
    private int promptKey(int maxKey) {
        int key = 0;
        if (textField.getText() != null) {
            try {
                key = Integer.parseInt(textField.getText());
            } catch (NumberFormatException e) {
                validKey = false;
                showErrorDialog("Invalid key format");
            }
            if (key <= 0 || key >= maxKey) {
                showErrorDialog("Max key is: " + (maxKey - 1));
                validKey = false;
            } else {
                validKey = true;
            }
        }
        return key;
    }

    /**
     * Decrypt the file.
     * Checks whether this is a new file or an old one, which in textArea.
     */
    private void decryptFile() {
        if (!isWasDecrypt || isWasEncrypt) {
            char[] text = isNewFileOpen ? inputText : resultProcess;
            AnalysisText analysisText = new AnalysisText(text);
            int key = promptKey(analysisText.getMaxKey());
            if (validKey) {
                Decrypt decrypt = new Decrypt(text, key);
                resultProcess = decrypt.process();
                isWasDecrypt = true;
                isWasEncrypt = false;
                isNewFileOpen = false;
                printText(resultProcess);
            }

        }

    }

    /**
     * Show error dialog.
     * @param message Text for display.
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(null, message, "Inane error", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Decrypt the file brute force attack method.
     * Checks whether this is a new file or an old one, which in textArea.
     */
    private void bruteForce() {
        char[] text = isNewFileOpen ? inputText : resultProcess;
        if (text == null) {
            showErrorDialog("Please provide a file.");
        } else {
            BruteForce bruteForce = new BruteForce(text);
            resultProcess = bruteForce.process();
            keyIs.setText("Key: " + bruteForce.getKey());
            if (bruteForce.getMaxNunSpaces() == 0) {
                showErrorDialog("Key not found.");
            } else {
                isWasEncrypt = false;
                isWasDecrypt = true;
                isNewFileOpen = false;
                printText(resultProcess);
            }
        }

    }

    /**
     * Encrypt the file.
     * Checks whether this is a new file or an old one, which in textArea.
     */
    private void encryptFile() {
        if (!isWasEncrypt) {
            char[] text = isNewFileOpen ? inputText : resultProcess;
            if (text == null) {
                showErrorDialog("Please provide a file.");
            } else {
                AnalysisText analysisText = new AnalysisText(text);
                int key = promptKey(analysisText.getMaxKey());
                if (validKey) {
                    Encrypt encrypt = new Encrypt(text, key);
                    resultProcess = encrypt.process();
                    isWasEncrypt = true;
                    isWasDecrypt = false;
                    printText(resultProcess);
                    isNewFileOpen = false;
                }
            }
        }
    }

    /**
     * Open file.
     * Opens a file through the FileService class, which handles the error.
     * Analyzes whether it is an encrypted file.
     */
    private void openFile() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(FILTER);

        int value = jFileChooser.showOpenDialog(this);
        if (value == JFileChooser.APPROVE_OPTION) {
            FileService readFile = new FileService();
            inputText = readFile.readAllBytes(jFileChooser.getSelectedFile().getAbsolutePath());

            AnalysisText analysisText = new AnalysisText(inputText);
            if (analysisText.getNumSpace() == analysisText.getMaxNumSpaces()) isWasEncrypt = false;

            isNewFileOpen = true;
            printText(inputText);
        }
    }

    /**
     * Print text to text area.
     */
    private void printText(char[] chars) {
        textArea.setForeground(Color.black);
        if (!textArea.getText().isEmpty()) textArea.setText("");
        for (char c : chars) {
            textArea.append(String.valueOf(c));
        }
    }

    /**
     * Save file.
     * Saves the file through the FileService class, which handles the error.
     * Displays a successful download window.
     */
    private void saveFile() {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(FILTER);
        int value = jFileChooser.showSaveDialog(this);
        if (value == JFileChooser.APPROVE_OPTION) {
            File selectionFile = jFileChooser.getSelectedFile();
            if (!selectionFile.exists()) {
                String name = selectionFile.getAbsolutePath()
                        + ".txt";
                selectionFile = new File(name);
            }

            FileService fileService = new FileService();
            fileService.saveFile(resultProcess, selectionFile);

            JOptionPane.showMessageDialog(null, "Successful", "Saved", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
