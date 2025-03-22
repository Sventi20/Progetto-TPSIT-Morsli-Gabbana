import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

public class BancaGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final String USERS_FILE = "utenti_banca.dat";
    private static final DecimalFormat df = new DecimalFormat("0.00");

    // User data
    private UserAccount user;

    // UI Components
    private JLabel labelData;
    private JLabel labelPortafoglio;
    private JLabel labelContoBancario;
    private JTextField inputField;
    private JTextArea logArea;
    private JButton depositButton;
    private JButton prelievoButton;
    private JButton investimentoButton;
    private JButton avanzaMeseButton;
    private JButton logoutButton;
    private JPanel mainPanel;

    // Colors for dark theme
    private final Color BACKGROUND_COLOR = new Color(30, 30, 40);
    private final Color PANEL_COLOR = new Color(45, 45, 60);
    private final Color TEXT_COLOR = new Color(220, 220, 230);
    private final Color HIGHLIGHT_COLOR = new Color(70, 90, 120);
    private final Color BUTTON_COLOR = new Color(60, 60, 80);
    private final Color BUTTON_TEXT_COLOR = new Color(200, 200, 210);
    private final Color ACCENT_COLOR = new Color(100, 120, 180);

    // Add a HashMap to store investment details
    private Map<String, Investment> investments = new HashMap<>();

    public BancaGUI(UserAccount user) {
        super("Banca BinGian - " + user.getUsername());
        this.user = user;
        setupUI();
        updateDisplay();
    }

    private void setupUI() {
        // Basic frame setup
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Make the window fullscreen
        setLocationRelativeTo(null);

        // Main panel with dark background
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBackground(BACKGROUND_COLOR);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Header panel (date and account info)
        JPanel headerPanel = createHeaderPanel();

        // Action panel (buttons)
        JPanel actionPanel = createActionPanel();

        // Log panel
        JPanel logPanel = createLogPanel();

        // Add all panels to main panel
        mainPanel.add(headerPanel, BorderLayout.NORTH);
        mainPanel.add(actionPanel, BorderLayout.CENTER);
        mainPanel.add(logPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        setContentPane(mainPanel);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(createFancyBorder("Informazioni Account"));

        // User label
        JLabel userLabel = new JLabel("Utente: " + user.getUsername());
        userLabel.setForeground(ACCENT_COLOR);
        userLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        userLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(userLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Date label
        labelData = new JLabel("Data: ");
        labelData.setForeground(TEXT_COLOR);
        labelData.setFont(new Font("SansSerif", Font.BOLD, 14));
        labelData.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelData);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Portfolio label
        labelPortafoglio = new JLabel("Bilancio portafoglio: ");
        labelPortafoglio.setForeground(TEXT_COLOR);
        labelPortafoglio.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelPortafoglio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelPortafoglio);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));

        // Bank account label
        labelContoBancario = new JLabel("Bilancio conto bancario: ");
        labelContoBancario.setForeground(TEXT_COLOR);
        labelContoBancario.setFont(new Font("SansSerif", Font.PLAIN, 14));
        labelContoBancario.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(labelContoBancario);

        return panel;
    }

    private JPanel createActionPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10)); // Change the layout to 3x2 grid
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(createFancyBorder("Operazioni"));

        // Deposit button
        depositButton = createStyledButton("Deposita", "Trasferisci denaro dal portafoglio al conto");
        depositButton.addActionListener(e -> handleDeposit());
        depositButton.setPreferredSize(new Dimension(150, 25)); // Decrease the size of the button
        panel.add(depositButton);

        // Withdraw button
        prelievoButton = createStyledButton("Preleva", "Trasferisci denaro dal conto al portafoglio");
        prelievoButton.addActionListener(e -> handleWithdraw());
        prelievoButton.setPreferredSize(new Dimension(150, 25)); // Decrease the size of the button
        panel.add(prelievoButton);

        // Investment button
        investimentoButton = createStyledButton("Investimento", "Effettua un investimento");
        investimentoButton.addActionListener(e -> handleInvestment());
        investimentoButton.setPreferredSize(new Dimension(150, 25)); // Decrease the size of the button
        panel.add(investimentoButton);

        // Blank button
        JButton blankButton = createStyledButton("", "");
        blankButton.setEnabled(false); // Disable the blank button
        blankButton.setPreferredSize(new Dimension(150, 25)); // Decrease the size of the button
        panel.add(blankButton);

        // Advance month button
        avanzaMeseButton = createStyledButton("Avanza Mese", "Passa al mese successivo (+100€)");
        avanzaMeseButton.addActionListener(e -> advanceMonth());
        avanzaMeseButton.setPreferredSize(new Dimension(150, 25)); // Decrease the size of the button
        panel.add(avanzaMeseButton);

        // Logout button
        logoutButton = createStyledButton("Logout", "Esci dall'applicazione");
        logoutButton.setBackground(new Color(100, 50, 50));
        logoutButton.addActionListener(e -> handleLogout());
        logoutButton.setPreferredSize(new Dimension(150, 25)); // Decrease the size of the button
        panel.add(logoutButton);

        return panel;
    }


    private JPanel createInputPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(5, 5));
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(createFancyBorder("Input"));

        // Label
        JLabel inputLabel = new JLabel("Importo:");
        inputLabel.setForeground(TEXT_COLOR);
        inputLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(inputLabel, BorderLayout.NORTH);

        // Text field
        inputField = new JTextField();
        inputField.setBackground(new Color(55, 55, 70));
        inputField.setForeground(TEXT_COLOR);
        inputField.setCaretColor(TEXT_COLOR);
        inputField.setFont(new Font("Monospaced", Font.PLAIN, 16));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(HIGHLIGHT_COLOR),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        panel.add(inputField, BorderLayout.CENTER);

        // Placeholder for a potential OK button or confirmation
        JPanel spacer = new JPanel();
        spacer.setBackground(PANEL_COLOR);
        panel.add(spacer, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createLogPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(PANEL_COLOR);
        panel.setBorder(createFancyBorder("Log Operazioni"));

        // Text area for logs
        logArea = new JTextArea(8, 40);
        logArea.setBackground(new Color(40, 40, 50));
        logArea.setForeground(TEXT_COLOR);
        logArea.setCaretColor(TEXT_COLOR);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 15)); // Increase the font size to 18
        logArea.setEditable(false);
        logArea.setLineWrap(true);
        logArea.setWrapStyleWord(true);
        logArea.append("Benvenuto nel sistema bancario BinGian!\n");
        logArea.append("Usa i pulsanti per effettuare operazioni sul tuo conto.\n");
        logArea.append("Ogni mese riceverai automaticamente 100€ nel tuo portafoglio.\n");

        JScrollPane scrollPane = new JScrollPane(logArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
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
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        );
    }

    private JButton createStyledButton(String text, String tooltip) {
        JButton button = new JButton(text);
        button.setBackground(BUTTON_COLOR);
        button.setForeground(Color.BLACK); // Change the text color to black
        button.setFocusPainted(false);
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        button.setToolTipText(tooltip);
        /*button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(HIGHLIGHT_COLOR),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));*/
        button.setPreferredSize(new Dimension(150, 25)); // Decrease the size of the button


        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(HIGHLIGHT_COLOR);
                button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(BUTTON_COLOR);
                if (button == logoutButton) {
                    button.setBackground(new Color(100, 50, 50));
                } else {
                    button.setBackground(BUTTON_COLOR);
                }
                button.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

        return button;
    }

    private void updateDisplay() {
        // Update date
        String[] mesi = {"gennaio", "febbraio", "marzo", "aprile", "maggio", "giugno",
                "luglio", "agosto", "settembre", "ottobre", "novembre", "dicembre"};
        int meseCorrente = user.getMesiTrascorsi() % 12;
        int annoCorrente = user.getAnno() + (user.getMesiTrascorsi() / 12);
        labelData.setText("Data: " + mesi[meseCorrente] + " " + annoCorrente);

        // Update account information
        labelPortafoglio.setText("Bilancio portafoglio: " + df.format(user.getPortafoglio()) + " €");
        labelContoBancario.setText("Bilancio conto bancario: " + df.format(user.getContoBancario()) + " €");

        // Update button states based on available funds
        depositButton.setEnabled(user.getPortafoglio() > 0);
        prelievoButton.setEnabled(user.getContoBancario() > 0);

        // Save user data
        saveUserData();
    }

    private void addLog(String message) {
        logArea.append("\n" + message);
        // Scroll to bottom
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    private void handleDeposit() {
        String input = JOptionPane.showInputDialog(this, "Inserisci l'importo da depositare:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input.replace(',', '.'));
                if (!operazioneSoldiValida(amount, user.getPortafoglio())) {
                    return;
                }

                user.setContoBancario(user.getContoBancario() + amount);
                user.setPortafoglio(user.getPortafoglio() - amount);
                addLog("Deposito di " + df.format(amount) + " € effettuato con successo.");
                updateDisplay();
            } catch (NumberFormatException e) {
                addLog("Input non valido! Inserisci un importo numerico valido.");
            }
        }
    }


    private void handleWithdraw() {
        String input = JOptionPane.showInputDialog(this, "Inserisci l'importo da prelevare:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input.replace(',', '.'));
                if (!operazioneSoldiValida(amount, user.getContoBancario())) {
                    return;
                }

                user.setPortafoglio(user.getPortafoglio() + amount);
                user.setContoBancario(user.getContoBancario() - amount);
                addLog("Prelievo di " + df.format(amount) + " € effettuato con successo.");
                updateDisplay();
            } catch (NumberFormatException e) {
                addLog("Input non valido! Inserisci un importo numerico valido.");
            }
        }
    }

    private void handleInvestment() {
        String input = JOptionPane.showInputDialog(this, "Inserisci l'importo da investire:");
        if (input != null) {
            try {
                double amount = Double.parseDouble(input.replace(',', '.'));
                if (!operazioneSoldiValida(amount, user.getContoBancario())) {
                    return;
                }

                String[] options = {"Basso rischio (3 mesi, +/- 3%)", "Medio rischio (6 mesi, +/- 10%)", "Alto rischio (12 mesi, +/- 25%)"};
                int choice = JOptionPane.showOptionDialog(this, "Scegli il tipo di investimento:", "Investimento", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                double investmentReturn;
                int months;
                switch (choice) {
                    case 0:
                        investmentReturn = Math.random() * 0.06 - 0.03; // +/- 3%
                        months = 3;
                        break;
                    case 1:
                        investmentReturn = Math.random() * 0.2 - 0.1; // +/- 10%
                        months = 6;
                        break;
                    case 2:
                        investmentReturn = Math.random() * 0.5 - 0.25; // +/- 25%
                        months = 12;
                        break;
                    default:
                        addLog("Investimento annullato.");
                        return;
                }

                // Store investment details
                Investment investment = new Investment(amount, investmentReturn, months, user.getMesiTrascorsi());
                investments.put(String.valueOf(investments.size()), investment);

                user.setContoBancario(user.getContoBancario() - amount);
                addLog("Investimento di " + df.format(amount) + " € effettuato con successo.");
                updateDisplay();
            } catch (NumberFormatException e) {
                addLog("Input non valido! Inserisci un importo numerico valido.");
            }
        }
    }

    private void advanceMonth() {
        user.advanceMonth();
        addLog("Sei avanzato al mese successivo!");
        addLog("Hai ricevuto 100€ nel tuo portafoglio.");

        // Check for ended investments
        for (Map.Entry<String, Investment> entry : investments.entrySet()) {
            Investment investment = entry.getValue();
            if (user.getMesiTrascorsi() - investment.getStartMonth() >= investment.getMonths()) {
                double investmentGain = investment.getAmount() * investment.getReturn();
                user.setContoBancario(user.getContoBancario() + investment.getAmount() + investmentGain);
                addLog("Investimento di " + df.format(investment.getAmount()) + " € è terminato.");
                addLog("Hai guadagnato " + df.format(investmentGain) + " € dall'investimento.");
                investments.remove(entry.getKey());
            }
        }

        updateDisplay();
    }

    private void handleLogout() {
        saveUserData();
        this.dispose();
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }

    private boolean operazioneSoldiValida(double soldiUsati, double soldiTotali) {
        if (soldiUsati <= 0) {
            addLog("La cifra inserita deve essere maggiore di zero!");
            return false;
        } else if (soldiUsati > soldiTotali) {
            addLog("Non puoi inserire una cifra maggiore di quella disponibile!");
            return false;
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private void saveUserData() {
        try {
            Map<String, UserAccount> users;
            File file = new File(USERS_FILE);

            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    users = (Map<String, UserAccount>) ois.readObject();
                }
            } else {
                users = new HashMap<>();
            }

            users.put(user.getUsername(), user);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(users);
            }
        } catch (Exception e) {
            addLog("Errore nel salvataggio dei dati: " + e.getMessage());
        }
    }
}
