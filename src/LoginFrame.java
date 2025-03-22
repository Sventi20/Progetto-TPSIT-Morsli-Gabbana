import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String USERS_FILE = "utenti_banca.dat";

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private final Color BACKGROUND_COLOR = new Color(30, 30, 40);
    private final Color PANEL_COLOR = new Color(45, 45, 60);
    private final Color TEXT_COLOR = new Color(220, 220, 230);
    private final Color HIGHLIGHT_COLOR = new Color(70, 90, 120);
    private final Color BUTTON_COLOR = new Color(60, 60, 80);
    private final Color BUTTON_TEXT_COLOR = new Color(200, 200, 210);
    private final Color ACCENT_COLOR = new Color(100, 120, 180);

    public LoginFrame() {
        super("Banca BinGian - Login");
        setupUI();
    }

    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window fullscreen
        setSize(400, 350); // Set the size to a smaller value if needed
        setLocationRelativeTo(null);
        setResizable(true); // Make the window non-resizable





    JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Logo/Title panel
        JPanel logoPanel = new JPanel();
        logoPanel.setBackground(BACKGROUND_COLOR);
        JLabel titleLabel = new JLabel("Banca BinGian");
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 36)); // Increase the font size to 36
        titleLabel.setForeground(ACCENT_COLOR);
        logoPanel.add(titleLabel);


        // Login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridBagLayout());
        loginPanel.setBackground(PANEL_COLOR);
        loginPanel.setBorder(createFancyBorder("Accedi"));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(TEXT_COLOR);
        usernameLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); // Increase the font size to 18
        usernameField = new JTextField(15);
        styleTextField(usernameField);

        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(usernameField, gbc);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(TEXT_COLOR);
        passwordLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); // Increase the font size to 18
        passwordField = new JPasswordField(15);
        styleTextField(passwordField);

        gbc.gridx = 0;
        gbc.gridy = 2;
        loginPanel.add(passwordLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        loginPanel.add(passwordField, gbc);

        // Buttons panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.setBackground(PANEL_COLOR);

        loginButton = createStyledButton("Accedi", "Accedi al tuo account");
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 18)); // Increase the font size to 18
        registerButton = createStyledButton("Registrati", "Crea un nuovo account");
        registerButton.setFont(new Font("SansSerif", Font.BOLD, 18)); // Increase the font size to 18

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(15, 5, 5, 5);
        loginPanel.add(buttonPanel, gbc);

        // Add panels to main panel
        mainPanel.add(logoPanel, BorderLayout.NORTH);
        mainPanel.add(loginPanel, BorderLayout.CENTER);

        // Add action listeners
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> handleRegister());

        // Allow login on Enter key
        getRootPane().setDefaultButton(loginButton);

        setContentPane(mainPanel);
    }

    private void styleTextField(JTextField textField) {
        textField.setBackground(new Color(55, 55, 70));
        textField.setForeground(TEXT_COLOR);
        textField.setCaretColor(TEXT_COLOR);
        textField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        textField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(HIGHLIGHT_COLOR),
                BorderFactory.createEmptyBorder(8, 8, 8, 8)
        ));
    }

    private Border createFancyBorder(String title) {
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(HIGHLIGHT_COLOR, 2),
                title
        );
        titledBorder.setTitleColor(ACCENT_COLOR);
        titledBorder.setTitleFont(new Font("SansSerif", Font.BOLD, 14));

        return BorderFactory.createCompoundBorder(
                titledBorder,
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        );
    }

    private JButton createStyledButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(BUTTON_TEXT_COLOR);
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setToolTipText(tooltip);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(HIGHLIGHT_COLOR),
                BorderFactory.createEmptyBorder(8, 15, 8, 15)
        ));

        // Mouse hover effect
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HIGHLIGHT_COLOR);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    private void handleLogin() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "I campi username e password non possono essere vuoti",
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, UserAccount> users = loadUsers();
        UserAccount user = users.get(username);

        if (user != null && user.checkPassword(password)) {
            // Login successful
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                BancaGUI app = new BancaGUI(user);
                app.setVisible(true);
            });
        } else {
            JOptionPane.showMessageDialog(this,
                    "Username o password non validi",
                    "Errore di autenticazione", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "I campi username e password non possono essere vuoti",
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Map<String, UserAccount> users = loadUsers();

        if (users.containsKey(username)) {
            JOptionPane.showMessageDialog(this,
                    "Username già in uso, scegli un altro username",
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Create new user
        UserAccount newUser = new UserAccount(username, password);
        users.put(username, newUser);
        saveUsers(users);

        JOptionPane.showMessageDialog(this,
                "Registrazione avvenuta con successo!\nOra puoi accedere con le tue credenziali",
                "Registrazione completata", JOptionPane.INFORMATION_MESSAGE);
    }

    @SuppressWarnings("unchecked")
    private Map<String, UserAccount> loadUsers() {
        File file = new File(USERS_FILE);

        if (!file.exists()) {
            return new HashMap<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<String, UserAccount>) ois.readObject();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Errore nel caricamento degli utenti: " + e.getMessage(),
                    "Errore", JOptionPane.ERROR_MESSAGE);
            return new HashMap<>();
        }
    }

    private void saveUsers(Map<String, UserAccount> users) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USERS_FILE))) {
            oos.writeObject(users);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Errore nel salvataggio degli utenti: " + e.getMessage(),
                    "Errore", JOptionPane.ERROR_MESSAGE);
        }
    }
}
