package com.mycompany.employeemanagementsystem;

class User {
    private String userID;
    private String userEmail;
    private String password;

    public User(String userID, String userEmail, String password) {
        this.userID = userID;
        this.userEmail = userEmail;
        this.password = password;
    }

    public void login() {
        System.out.println(userEmail + " logged in.");
    }

    public void logout() {
        System.out.println(userEmail + " logged out.");
    }
}

// Employee class extending User
class Employee extends User {
    private String firstName;
    private String lastName;
    private int employeeID;
    private String jobTitle;
    private double salary;
    private int age;
    private String address;
    private String assignedShift;

    public Employee(String userID, String userEmail, String password, String firstName, String lastName, int employeeID, String jobTitle, double salary, int age, String address, String assignedShift) {
        super(userID, userEmail, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeeID = employeeID;
        this.jobTitle = jobTitle;
        this.salary = salary;
        this.age = age;
        this.address = address;
        this.assignedShift = assignedShift;
    }

    public void updatePosition(String newPosition) {
        this.jobTitle = newPosition;
    }

    public void timeIn() {
        System.out.println(firstName + " has timed in.");
    }

    public void timeOut() {
        System.out.println(firstName + " has timed out.");
    }

    public void viewPayroll() {
        System.out.println(firstName + " is viewing payroll.");
    }
}

// Admin class extending Employee
class Admin extends Employee {
    public Admin(String userID, String userEmail, String password, String firstName, String lastName, int employeeID, String jobTitle, double salary, int age, String address, String assignedShift) {
        super(userID, userEmail, password, firstName, lastName, employeeID, jobTitle, salary, age, address, assignedShift);
    }

    public void addEmployee() {
        System.out.println("Adding new employee...");
    }

    public void removeEmployee() {
        System.out.println("Removing employee...");
    }

    public void approvePayroll() {
        System.out.println("Payroll approved.");
    }

    public void assignTaskToTeam() {
        System.out.println("Task assigned to a team.");
    }
}

// Manager class extending Employee
class Manager extends Employee {
    private String department;
    private int teamSize;
    private String managerLevel;

    public Manager(String userID, String userEmail, String password, String firstName, String lastName, int employeeID, String jobTitle, double salary, int age, String address, String assignedShift, String department, int teamSize, String managerLevel) {
        super(userID, userEmail, password, firstName, lastName, employeeID, jobTitle, salary, age, address, assignedShift);
        this.department = department;
        this.teamSize = teamSize;
        this.managerLevel = managerLevel;
    }

    public void scheduleMeeting() {
        System.out.println("Meeting scheduled.");
    }

    public void assignTask(int employeeID) {
        System.out.println("Task assigned to Employee ID: " + employeeID);
    }

    public void promoteEmployee(int employeeID) {
        System.out.println("Employee ID: " + employeeID + " promoted.");
    }
}

// Timekeeper class extending Employee
class Timekeeper extends Employee {
    private int timeStart;
    private int timeEnd;
    private String currentDay;
    private String currentDate;

    public Timekeeper(String userID, String userEmail, String password, String firstName, String lastName, int employeeID, String jobTitle, double salary, int age, String address, String assignedShift, int timeStart, int timeEnd, String currentDay, String currentDate) {
        super(userID, userEmail, password, firstName, lastName, employeeID, jobTitle, salary, age, address, assignedShift);
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.currentDay = currentDay;
        this.currentDate = currentDate;
    }

    public void calculateTotalHours() {
        int totalHours = timeEnd - timeStart;
        System.out.println("Total Hours Worked: " + totalHours);
    }
}

// Payroll class
class Payroll {
    private int payrollID;
    private int hoursWorked;
    private double hourlyRate;
    private double deductions;

    public Payroll(int payrollID, int hoursWorked, double hourlyRate, double deductions) {
        this.payrollID = payrollID;
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
        this.deductions = deductions;
    }

    public void calculateNetPay() {
        double netPay = (hoursWorked * hourlyRate) - deductions;
        System.out.println("Net Pay: " + netPay);
    }

    public void printPaySlip() {
        System.out.println("Printing payslip...");
    }
}

// Shift class
class Shift {
    private String shiftType;
    private String startTime;
    private String endTime;
    private int[] assignedEmployees;

    public Shift(String shiftType, String startTime, String endTime, int[] assignedEmployees) {
        this.shiftType = shiftType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.assignedEmployees = assignedEmployees;
    }

    public void assignEmployee(int employeeID) {
        System.out.println("Employee ID: " + employeeID + " assigned to shift.");
    }

    public void removeEmployee(int employeeID) {
        System.out.println("Employee ID: " + employeeID + " removed from shift.");
    }
}
