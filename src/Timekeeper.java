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
