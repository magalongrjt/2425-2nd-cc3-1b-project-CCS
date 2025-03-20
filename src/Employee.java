/*TODO: add setters and getters for adding employees
 * 
 */
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
