import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class PayrollGUI extends JFrame {
    private JTable payrollTable;
    private DefaultTableModel tableModel;
    private List<Team> teams;
    private static final String PAYROLL_FILE = "payroll_data.ser";

    public PayrollGUI() {
        setTitle("Payroll Management");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(900, 500);
        setLayout(new BorderLayout());

        teams = TeamsMenuGUI.getTeams();

        String[] columns = {"ID", "Name", "Hours Worked", "Salary/Hour", "Deductions", "Total Salary", "Paid"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column >= 2 && column != 5;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                switch (columnIndex) {
                    case 0: return Integer.class;
                    case 1: return String.class;
                    case 2: return Integer.class;
                    case 3: return Double.class;
                    case 4: return Double.class;
                    case 5: return Double.class;
                    case 6: return Boolean.class;
                    default: return Object.class;
                }
            }
        };
        payrollTable = new JTable(tableModel);

        DefaultTableCellRenderer currencyRenderer = new DefaultTableCellRenderer() {
            private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof Number) {
                    value = currencyFormat.format(((Number)value).doubleValue());
                }
                return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            }
        };
        payrollTable.getColumnModel().getColumn(3).setCellRenderer(currencyRenderer);
        payrollTable.getColumnModel().getColumn(4).setCellRenderer(currencyRenderer);
        payrollTable.getColumnModel().getColumn(5).setCellRenderer(currencyRenderer);

        payrollTable.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
        payrollTable.addPropertyChangeListener("tableCellEditor", evt -> {
            if (evt.getNewValue() == null) {
                int row = payrollTable.getSelectedRow();
                if (row != -1) {
                    recalculateTotal(row);
                    savePayrollData();
                }
            }
        });

        payrollTable.getModel().addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int row = e.getFirstRow();
                int column = e.getColumn();
                if (column != 5) {
                    recalculateTotal(row);
                    savePayrollData();
                }
            }
        });

        JMenuBar menuBar = new JMenuBar();
        JMenu navigationMenu = new JMenu("Navigate");

        JMenuItem teamsItem = new JMenuItem("Teams");
        JMenuItem payrollItem = new JMenuItem("Payroll");

        teamsItem.addActionListener(e -> {
            dispose();
        });

        payrollItem.addActionListener(e -> {
            dispose();
            new PayrollGUI();
        });

        navigationMenu.add(teamsItem);
        navigationMenu.add(payrollItem);
        menuBar.add(navigationMenu);
        setJMenuBar(menuBar);

        JScrollPane scrollPane = new JScrollPane(payrollTable);
        add(scrollPane, BorderLayout.CENTER);

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        add(refreshButton, BorderLayout.SOUTH);

        loadPayrollData();
        setVisible(true);
    }

    private void recalculateTotal(int row) {
        try {
            Object hoursObj = tableModel.getValueAt(row, 2);
            Object rateObj = tableModel.getValueAt(row, 3);
            Object deductionsObj = tableModel.getValueAt(row, 4);

            int hours = hoursObj != null ? Integer.parseInt(hoursObj.toString()) : 0;
            double ratePerHour = rateObj != null ? Double.parseDouble(rateObj.toString()) : 0.0;
            double deductions = deductionsObj != null ? Double.parseDouble(deductionsObj.toString()) : 0.0;
            
            double totalSalary = (hours * ratePerHour) - deductions;
            tableModel.setValueAt(totalSalary, row, 5);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, 
                "Please enter valid numbers", 
                "Invalid Input", 
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshTable() {
        loadPayrollData();
    }

    private void loadPayrollData() {
        tableModel.setRowCount(0);
        try {
            File file = new File(PAYROLL_FILE);
            if (file.exists()) {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                Object[][] data = (Object[][]) ois.readObject();
                ois.close();
                updatePayrollData(data);
            } else {
                initializePayrollData();
            }
        } catch (Exception e) {
            e.printStackTrace();
            initializePayrollData();
        }
    }

    private void updatePayrollData(Object[][] existingData) {
        java.util.Map<Integer, Object[]> existingRecords = new java.util.HashMap<>();
        for (Object[] row : existingData) {
            if (row[0] instanceof Integer) {
                existingRecords.put((Integer) row[0], row);
            }
        }

        List<Employee> allEmployees = new ArrayList<>();
        for (Team team : teams) {
            allEmployees.addAll(team.getEmployees());
        }
        allEmployees.sort((e1, e2) -> Integer.compare(e1.getId(), e2.getId()));

        for (Employee employee : allEmployees) {
            Object[] rowData;
            if (existingRecords.containsKey(employee.getId())) {
                rowData = existingRecords.get(employee.getId());
                rowData[1] = employee.getName();
            } else {
                rowData = new Object[]{
                    employee.getId(),
                    employee.getName(),
                    0,
                    0.0,
                    0.0,
                    0.0,
                    false
                };
            }
            tableModel.addRow(rowData);
            int lastRow = tableModel.getRowCount() - 1;
            recalculateTotal(lastRow);
        }
    }

    private void initializePayrollData() {
        List<Employee> allEmployees = new ArrayList<>();
        for (Team team : teams) {
            allEmployees.addAll(team.getEmployees());
        }
        allEmployees.sort((e1, e2) -> Integer.compare(e1.getId(), e2.getId()));

        for (Employee employee : allEmployees) {
            tableModel.addRow(new Object[]{
                employee.getId(),
                employee.getName(),
                0,
                0.0,
                0.0,
                0.0,
                false
            });
            int lastRow = tableModel.getRowCount() - 1;
            recalculateTotal(lastRow);
        }
    }

    private void savePayrollData() {
        try {
            int rowCount = tableModel.getRowCount();
            int columnCount = tableModel.getColumnCount();
            Object[][] data = new Object[rowCount][columnCount];
            
            for (int i = 0; i < rowCount; i++) {
                for (int j = 0; j < columnCount; j++) {
                    data[i][j] = tableModel.getValueAt(i, j);
                }
            }
            
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PAYROLL_FILE));
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PayrollGUI::new);
    }
}