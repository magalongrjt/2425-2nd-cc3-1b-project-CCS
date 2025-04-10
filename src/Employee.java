/**
 * Represents an employee in the system with their personal and work information.
 * Stores employee data and provides methods to manage it.
 */
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1; // For generating unique IDs
    
    // Employee information
    private final int id;
    private String name;
    private String email;
    private String phone;
    private int age;
    private String sex;
    private String position;
    private String address;
    private String team;
    private double payroll;

    /**
     * Creates a new employee with basic information.
     */
    public Employee(String name, String email, String phone, int age, String sex, String position, String address, String team) {
        this.id = nextId++;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.age = age;
        this.sex = sex;
        this.position = position;
        this.address = address;
        this.team = team;
        this.payroll = 0.0; // Initialize payroll to 0
    }

    // Getters and Setters
    /**
     * @return Employee's unique ID
     */
    public int getId() { return id; }
    
    /**
     * @return Employee's name
     */
    public String getName() { return name; }
    
    /**
     * Updates employee's name
     */
    public void setName(String name) { this.name = name; }
    
    /**
     * @return Employee's email
     */
    public String getEmail() { return email; }
    
    /**
     * Updates employee's email
     */
    public void setEmail(String email) { this.email = email; }
    
    /**
     * @return Employee's phone number
     */
    public String getPhone() { return phone; }
    
    /**
     * Updates employee's phone number
     */
    public void setPhone(String phone) { this.phone = phone; }
    
    /**
     * @return Employee's age
     */
    public int getAge() { return age; }
    
    /**
     * Updates employee's age
     */
    public void setAge(int age) { this.age = age; }
    
    /**
     * @return Employee's gender
     */
    public String getSex() { return sex; }
    
    /**
     * Updates employee's gender
     */
    public void setSex(String sex) { this.sex = sex; }
    
    /**
     * @return Employee's job position
     */
    public String getPosition() { return position; }
    
    /**
     * Updates employee's position
     */
    public void setPosition(String position) { this.position = position; }
    
    /**
     * @return Employee's address
     */
    public String getAddress() { return address; }
    
    /**
     * Updates employee's address
     */
    public void setAddress(String address) { this.address = address; }
    
    /**
     * @return Employee's team
     */
    public String getTeam() { return team; }
    
    /**
     * Updates employee's team
     */
    public void setTeam(String team) { this.team = team; }

    /**
     * @return Employee's payroll amount
     */
    public double getPayroll() { return payroll; }
    
    /**
     * Updates employee's payroll amount
     */
    public void setPayroll(double payroll) { this.payroll = payroll; }

    /**
     * Updates employee's position
     */
    public void updatePosition(String newPosition) {
        this.position = newPosition;
    }

    /**
     * Records employee's time-in
     */
    public void timeIn() {
        System.out.println(name + " has timed in.");
    }

    /**
     * Records employee's time-out
     */
    public void timeOut() {
        System.out.println(name + " has timed out.");
    }

    /**
     * Shows employee's payroll info
     */
    public void viewPayroll() {
        System.out.println(name + " is viewing payroll.");
    }

    /**
     * @return String showing employee ID and name
     */
    @Override
    public String toString() {
        return "ID: " + id + " - " + name;
    }
}
