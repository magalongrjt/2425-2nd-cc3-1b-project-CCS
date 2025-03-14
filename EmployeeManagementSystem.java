// Main class to test the system
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Admin admin = new Admin("A101", "admin@example.com", "adminPass", "John", "Doe", 1, "HR Admin", 50000, 35, "123 Street, City", "Morning");
        admin.addEmployee();
        admin.approvePayroll();
        
        Employee emp = new Employee("E202", "employee@example.com", "empPass", "Jane", "Smith", 2, "Software Engineer", 60000, 28, "456 Avenue, City", "Night");
        emp.viewPayroll();
        emp.timeOut();
    }
}
