package programtiketbioskop.Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Connector {
    private Connection koneksi;
    private Statement statement;
    private final String url = "jdbc:mysql://localhost:3306/bioskop";
    private final String username = "root";
    private final String pass = "";

    public Connector() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Correct MySQL driver class
            koneksi = DriverManager.getConnection(url, username, pass); // Get a connection
            statement = koneksi.createStatement(); // Create a statement
            System.out.println("Connection established.");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found.");
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: Driver tidak ditemukan!");
        } catch (SQLException e) {
            System.err.println("Connection failed.");
            JOptionPane.showMessageDialog(null, "Koneksi Gagal: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return koneksi;
    }

    public Statement getStatement() {
        return statement;
    }

    public static void main(String[] args) {
        Connector connector = new Connector();
        if (connector.getConnection() != null) {
            System.out.println("Testing database connection...");
            // Test database interaction here if needed
        }
    }
}
