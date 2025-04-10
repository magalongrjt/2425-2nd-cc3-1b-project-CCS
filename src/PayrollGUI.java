import java.io.*;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.AbstractTableModel;

public class PayrollGUI extends JFrame {

    public PayrollGUI() {
        setTitle("Payroll Management");
        setSize(900, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        PayrollTableModel model = new PayrollTableModel();
        JTable table = new JTable(model);

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);

        // Load saved data if available
        model.loadFromFile("payroll_data.ser");

        // Save data on window close
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                model.saveToFile("payroll_data.ser");
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PayrollGUI::new);
    }
}

class PayrollTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Payroll ID", "Hours Worked", "Hourly Rate", "Deductions", "Net Pay", "Paid"};
    private Object[][] data = {
            {1, 40, 100.0, 200.0, 3800.0, false},
            {2, 35, 90.0, 150.0, 2925.0, true},
            {3, 45, 120.0, 100.0, 5300.0, false}
    };

    public PayrollTableModel() {
        addTableModelListener(e -> {
            if (e.getType() == TableModelEvent.UPDATE && e.getColumn() >= 1 && e.getColumn() <= 3) {
                int row = e.getFirstRow();
                recalculateNetPay(row);
            }
        });
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 1 || columnIndex == 2 || columnIndex == 3 || columnIndex == 5;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (getRowCount() > 0 && getValueAt(0, columnIndex) != null) {
            return getValueAt(0, columnIndex).getClass();
        }
        return Object.class;
    }

    private void recalculateNetPay(int row) {
        try {
            int hoursWorked = Integer.parseInt(data[row][1].toString());
            double hourlyRate = Double.parseDouble(data[row][2].toString());
            double deductions = Double.parseDouble(data[row][3].toString());
            double netPay = (hoursWorked * hourlyRate) - deductions;
            data[row][4] = netPay;
            fireTableCellUpdated(row, 4);
        } catch (Exception e) {
            System.out.println("Error calculating net pay for row " + row);
        }
    }

    public void saveToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
            System.out.println("Saved payroll data.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            data = (Object[][]) ois.readObject();
            fireTableDataChanged();
            System.out.println("Loaded payroll data.");
        } catch (Exception e) {
            System.out.println("No saved payroll data found. Starting fresh.");
        }
    }
}