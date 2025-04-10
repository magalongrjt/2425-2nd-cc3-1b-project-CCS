/**
 * Login interface for the system.
 * Handles user authentication and main menu access.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame implements ActionListener {

    // UI Components
    JButton loginButton;
    JTextField userName;
    JTextField password;
    JLabel message;

    /**
     * Creates the login window
     */
    GUI() {

        // TEMP CODE
        message = new JLabel();
        message.setText("login feature to be implemented");
        message.setVisible(false);

        // Title
        JLabel title = new JLabel();
        title.setText("Employee Management System");

        // Credentials fields
        userName = new JTextField();
        userName.setPreferredSize(new Dimension(300, 40));
        password = new JTextField();
        password.setPreferredSize(new Dimension(300, 40));

        // Login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        loginButton.setFocusable(false);

        // Title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setPreferredSize(new Dimension(800, 30)); 
        titlePanel.setBackground(Color.GRAY);
        titlePanel.add(title);

        // Login panel
        JPanel loginPanel = new JPanel();
        loginPanel.setPreferredSize(new Dimension(300, 200));
        loginPanel.add(new JLabel("Username"));
        loginPanel.add(userName);
        loginPanel.add(new JLabel("Password"));
        loginPanel.add(password);
        loginPanel.add(loginButton);

        // Window settings
        this.setTitle("CCS Employee Management System");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(new FlowLayout());
        this.add(titlePanel);
        this.add(loginPanel);
        this.add(message);
        this.setVisible(true);
    }

    /**
     * Handles login button clicks
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            // Close login screen
            this.dispose();
            
            // Open TeamsMenuGUI
            new TeamsMenuGUI();
        }
    }

    /**
     * Starts the application
     */
    public static void main(String[] args) {
        new GUI();
    }
}
