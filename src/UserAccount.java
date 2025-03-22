import java.io.Serializable;

public class UserAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String password;
    private double portafoglio;
    private double contoBancario;
    private int anno;
    private int mesiTrascorsi;

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
        this.portafoglio = 100.0; // Initial funds
        this.contoBancario = 0.0;
        this.anno = 2024;
        this.mesiTrascorsi = 0;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public boolean checkPassword(String password) { return this.password.equals(password); }
    public double getPortafoglio() { return portafoglio; }
    public void setPortafoglio(double portafoglio) { this.portafoglio = portafoglio; }
    public double getContoBancario() { return contoBancario; }
    public void setContoBancario(double contoBancario) { this.contoBancario = contoBancario; }
    public int getAnno() { return anno; }
    public void setAnno(int anno) { this.anno = anno; }
    public int getMesiTrascorsi() { return mesiTrascorsi; }
    public void setMesiTrascorsi(int mesiTrascorsi) { this.mesiTrascorsi = mesiTrascorsi; }

    public void advanceMonth() {
        mesiTrascorsi++;
        // Add 100€ to the wallet every month
        portafoglio += 100.0;
    }
}
