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
