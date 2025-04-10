import java.awt.*;
import javax.swing.*;
import java.io.Serializable;
import java.util.List;
import java.io.*;
import java.util.ArrayList;

public class TeamsMenuGUI extends JFrame {
    private static List<Team> teams = new ArrayList<>();
    private static final String dataFile = "teams_data.ser";
    private Team selectedTeam = null;
    private JPanel employeeListPanel;
    
    private JLabel nameLabel;
    private JLabel emailLabel;
    private JLabel phoneLabel;
    private JLabel ageLabel;
    private JLabel sexLabel;
    private JLabel positionLabel;
    private JLabel addressLabel;
    private JLabel teamLabel;

    public TeamsMenuGUI() {
        setTitle("Employee Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLayout(new BorderLayout());

        if (teams.isEmpty()) {
            initializeData();
        }

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

        JPanel teamListPanel = new JPanel();
        teamListPanel.setLayout(new BoxLayout(teamListPanel, BoxLayout.Y_AXIS));
        teamListPanel.setBorder(BorderFactory.createTitledBorder("Team List"));

        employeeListPanel = new JPanel();
        employeeListPanel.setLayout(new BoxLayout(employeeListPanel, BoxLayout.Y_AXIS));
        employeeListPanel.setBorder(BorderFactory.createTitledBorder("Employees"));

        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Employee Details"));

        nameLabel = new JLabel("Name: ");
        emailLabel = new JLabel("Email: ");
        phoneLabel = new JLabel("Phone: ");
        ageLabel = new JLabel("Age: ");
        sexLabel = new JLabel("Sex: ");
        positionLabel = new JLabel("Position: ");
        addressLabel = new JLabel("Address: ");
        teamLabel = new JLabel("Team: ");

        detailsPanel.add(nameLabel);
        detailsPanel.add(emailLabel);
        detailsPanel.add(phoneLabel);
        detailsPanel.add(ageLabel);
        detailsPanel.add(sexLabel);
        detailsPanel.add(positionLabel);
        detailsPanel.add(addressLabel);
        detailsPanel.add(teamLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addTeamButton = new JButton("Add Team");
        JButton removeTeamButton = new JButton("Remove Team");
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton removeEmployeeButton = new JButton("Remove Employee");

        addTeamButton.addActionListener(e -> {
            DialogManager.showAddTeamDialog(this);
        });

        removeTeamButton.addActionListener(e -> {
            DialogManager.showRemoveTeamDialog(this, teams);
        });

        addEmployeeButton.addActionListener(e -> {
            if (teams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please create a team first", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedTeam == null) {
                JOptionPane.showMessageDialog(this, "Please select a team first", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DialogManager.showAddEmployeeDialog(this, selectedTeam);
        });

        removeEmployeeButton.addActionListener(e -> {
            if (teams.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No teams available", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (selectedTeam == null) {
                JOptionPane.showMessageDialog(this, "Please select a team first", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            DialogManager.showRemoveEmployeeDialog(this, selectedTeam);
        });

        buttonPanel.add(addTeamButton);
        buttonPanel.add(removeTeamButton);
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(removeEmployeeButton);

        mainPanel.add(teamListPanel, BorderLayout.WEST);
        mainPanel.add(employeeListPanel, BorderLayout.CENTER);
        mainPanel.add(detailsPanel, BorderLayout.EAST);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
        updateTeamList(teamListPanel);
        setVisible(true);
    }

    private void updateTeamList(JPanel teamListPanel) {
        teamListPanel.removeAll();
        for (Team team : teams) {
            JButton teamButton = new JButton(team.getName());
            teamButton.addActionListener(e -> {
                selectedTeam = team;
                updateEmployeeList(team);
            });
            teamListPanel.add(teamButton);
        }
        teamListPanel.revalidate();
        teamListPanel.repaint();
    }

    private void updateEmployeeList(Team team) {
        employeeListPanel.removeAll();
        for (Employee employee : team.getEmployees()) {
            JButton employeeButton = new JButton(employee.getName());
            employeeButton.addActionListener(e -> updateEmployeeDetails(employee));
            employeeListPanel.add(employeeButton);
        }
        employeeListPanel.revalidate();
        employeeListPanel.repaint();
    }

    private void updateEmployeeDetails(Employee employee) {
        nameLabel.setText("Name: " + employee.getName());
        emailLabel.setText("Email: " + employee.getEmail());
        phoneLabel.setText("Phone: " + employee.getPhone());
        ageLabel.setText("Age: " + employee.getAge());
        sexLabel.setText("Sex: " + employee.getSex());
        positionLabel.setText("Position: " + employee.getPosition());
        addressLabel.setText("Address: " + employee.getAddress());
        teamLabel.setText("Team: " + employee.getTeam());
    }

    private void initializeData() {
        try {
            File file = new File(dataFile);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                teams = (List<Team>) ois.readObject();
                ois.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveTeamsData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(dataFile));
            oos.writeObject(teams);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        saveTeamsData();
    }

    public void addTeam(String teamName) {
        Team newTeam = new Team(teamName);
        teams.add(newTeam);
        saveData();
        Container c = getContentPane();
        JPanel mainPanel = (JPanel) c.getComponent(0);
        JPanel teamListPanel = (JPanel) mainPanel.getComponent(0);
        updateTeamList(teamListPanel);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
        if (selectedTeam == team) {
            selectedTeam = null;
            employeeListPanel.removeAll();
            employeeListPanel.revalidate();
            employeeListPanel.repaint();
        }
        saveData();
        Container c = getContentPane();
        JPanel mainPanel = (JPanel) c.getComponent(0);
        JPanel teamListPanel = (JPanel) mainPanel.getComponent(0);
        updateTeamList(teamListPanel);
    }

    public void addEmployee(Team team, Employee employee) {
        team.addEmployee(employee);
        saveData();
        updateEmployeeList(team);
    }

    public void removeEmployee(Team team, Employee employee) {
        team.removeEmployee(employee);
        saveData();
        updateEmployeeList(team);
    }

    public static List<Team> getTeams() {
        return teams;
    }
}



