import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class DialogManager {
    public static void showAddTeamDialog(TeamsMenuGUI parent) {
        JDialog dialog = new JDialog(parent, "Add Team", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(2, 2, 5, 5));
        JLabel nameLabel = new JLabel("Team Name:");
        JTextField nameField = new JTextField();

        panel.add(nameLabel);
        panel.add(nameField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            String teamName = nameField.getText().trim();
            if (!teamName.isEmpty()) {
                parent.addTeam(teamName);
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Please enter a team name", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public static void showAddEmployeeDialog(TeamsMenuGUI parent, Team team) {
        JDialog dialog = new JDialog(parent, "Add Employee", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(400, 400);
        dialog.setLocationRelativeTo(parent);

        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JTextField phoneField = new JTextField();
        JTextField ageField = new JTextField();
        JComboBox<String> sexCombo = new JComboBox<>(new String[]{"M", "F"});
        JTextField positionField = new JTextField();
        JTextField addressField = new JTextField();

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Sex:"));
        panel.add(sexCombo);
        panel.add(new JLabel("Position:"));
        panel.add(positionField);
        panel.add(new JLabel("Address:"));
        panel.add(addressField);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> {
            try {
                String name = nameField.getText().trim();
                String email = emailField.getText().trim();
                String phone = phoneField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String sex = (String) sexCombo.getSelectedItem();
                String position = positionField.getText().trim();
                String address = addressField.getText().trim();

                if (name.isEmpty() || email.isEmpty() || phone.isEmpty() || position.isEmpty() || address.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Employee employee = new Employee(name, email, phone, age, sex, position, address, team.getName());
                parent.addEmployee(team, employee);
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Please enter a valid age", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        dialog.add(panel, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public static void showRemoveTeamDialog(TeamsMenuGUI parent, List<Team> teams) {
        if (teams.isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No teams to remove", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(parent, "Remove Team", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);

        JComboBox<Team> teamCombo = new JComboBox<>(teams.toArray(new Team[0]));
        
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            Team selectedTeam = (Team) teamCombo.getSelectedItem();
            if (selectedTeam != null) {
                int confirm = JOptionPane.showConfirmDialog(dialog, 
                    "Are you sure you want to remove " + selectedTeam.getName() + "?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    parent.removeTeam(selectedTeam);
                    dialog.dispose();
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);

        dialog.add(teamCombo, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }

    public static void showRemoveEmployeeDialog(TeamsMenuGUI parent, Team team) {
        if (team.getEmployees().isEmpty()) {
            JOptionPane.showMessageDialog(parent, "No employees to remove", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JDialog dialog = new JDialog(parent, "Remove Employee", true);
        dialog.setLayout(new BorderLayout());
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(parent);

        JComboBox<Employee> employeeCombo = new JComboBox<>(team.getEmployees().toArray(new Employee[0]));
        
        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(e -> {
            Employee selectedEmployee = (Employee) employeeCombo.getSelectedItem();
            if (selectedEmployee != null) {
                int confirm = JOptionPane.showConfirmDialog(dialog, 
                    "Are you sure you want to remove " + selectedEmployee.getName() + "?",
                    "Confirm Removal",
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    parent.removeEmployee(team, selectedEmployee);
                    dialog.dispose();
                }
            }
        });

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> dialog.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(removeButton);
        buttonPanel.add(cancelButton);

        dialog.add(employeeCombo, BorderLayout.CENTER);
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        dialog.setVisible(true);
    }
} 