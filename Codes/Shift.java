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
