import java.awt.*;
import javax.swing.*;

public class TeamsMenuGUI extends JFrame {

    public TeamsMenuGUI() {
        setTitle("Employee Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());

        // Navigation Bar
        JMenuBar menuBar = new JMenuBar();
        JMenu navigationMenu = new JMenu("Navigate");

        JMenuItem teamsItem = new JMenuItem("Teams");
        JMenuItem payrollItem = new JMenuItem("Payroll");

        teamsItem.addActionListener(e -> {
            dispose();
            new TeamsMenuGUI();
        });

        payrollItem.addActionListener(e -> {
            new PayrollGUI();
        });

        navigationMenu.add(teamsItem);
        navigationMenu.add(payrollItem);
        menuBar.add(navigationMenu);
        setJMenuBar(menuBar);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Left - Team List
        JPanel teamListPanel = new JPanel();
        teamListPanel.setLayout(new BoxLayout(teamListPanel, BoxLayout.Y_AXIS));
        teamListPanel.setBorder(BorderFactory.createTitledBorder("Team List"));

        JButton teamButton = new JButton("Team 1");
        teamListPanel.add(teamButton);

        // Center - Employee List
        JPanel employeeListPanel = new JPanel();
        employeeListPanel.setLayout(new BoxLayout(employeeListPanel, BoxLayout.Y_AXIS));
        employeeListPanel.setBorder(BorderFactory.createTitledBorder("Find Employee"));

        String[] employees = {"Employee 1", "Employee 2", "Employee 3"};
        JButton[] employeeButtons = new JButton[3];

        for (int i = 0; i < employees.length; i++) {
            employeeButtons[i] = new JButton(employees[i]);
            employeeListPanel.add(employeeButtons[i]);
        }

        // Right - Employee Details
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        JLabel nameLabel = new JLabel("Name: ");
        JLabel emailLabel = new JLabel("Email: ");
        JLabel phoneLabel = new JLabel("Phone: ");
        JLabel ageLabel = new JLabel("Age: ");
        JLabel sexLabel = new JLabel("Sex: ");
        JLabel positionLabel = new JLabel("Position: ");
        JLabel addressLabel = new JLabel("Address: ");
        JLabel teamLabel = new JLabel("Team: ");

        detailsPanel.add(nameLabel);
        detailsPanel.add(emailLabel);
        detailsPanel.add(phoneLabel);
        detailsPanel.add(ageLabel);
        detailsPanel.add(sexLabel);
        detailsPanel.add(positionLabel);
        detailsPanel.add(addressLabel);
        detailsPanel.add(teamLabel);

        // TEMP Added event listeners
        for (JButton button : employeeButtons) {
            button.addActionListener(e -> {
                nameLabel.setText("Name: " + button.getText());
                emailLabel.setText("Email: example@email.com");
                phoneLabel.setText("Phone: 09123456789");
                ageLabel.setText("Age: XX");
                sexLabel.setText("Sex: X");
                positionLabel.setText("Position: XXXXX");
                addressLabel.setText("Address: XXX XXX XXX");
                teamLabel.setText("Team: Team 1");
            });
        }

        mainPanel.add(teamListPanel, BorderLayout.WEST);
        mainPanel.add(employeeListPanel, BorderLayout.CENTER);
        mainPanel.add(detailsPanel, BorderLayout.EAST);

        add(mainPanel);
        setVisible(true);
    }
} 
