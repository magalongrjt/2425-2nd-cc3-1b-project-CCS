/*TODO: add setters and getters for adding employees
 * 
 */
import java.io.Serializable;

public class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private static int nextId = 1; // Static counter for generating unique IDs
    
    private final int id; // Unique employee ID
    private String name;
    private String email;
    private String phone;
    private int age;
    private String sex;
    private String position;
    private String address;
    private String team;
    private double payroll; // Store employee's payroll amount

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
    public int getId() { return id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }
    
    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
    
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    
    public String getTeam() { return team; }
    public void setTeam(String team) { this.team = team; }

    public double getPayroll() { return payroll; }
    public void setPayroll(double payroll) { this.payroll = payroll; }

    public void updatePosition(String newPosition) {
        this.position = newPosition;
    }

    public void timeIn() {
        System.out.println(name + " has timed in.");
    }

    public void timeOut() {
        System.out.println(name + " has timed out.");
    }

    public void viewPayroll() {
        System.out.println(name + " is viewing payroll.");
    }

    @Override
    public String toString() {
        return "ID: " + id + " - " + name;
    }
}
