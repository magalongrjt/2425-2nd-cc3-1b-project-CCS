/**
 * Represents a work team that can contain multiple employees.
 * Manages team members and their assignments.
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Team implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private List<Employee> employees;

    /**
     * Creates a new team with a name
     */
    public Team(String name) {
        this.name = name;
        this.employees = new ArrayList<>();
    }

    /**
     * @return Team's name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates team's name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return List of team members
     */
    public List<Employee> getEmployees() {
        return employees;
    }

    /**
     * Adds an employee to the team
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    /**
     * Removes an employee from the team
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    /**
     * @return Team's name as string
     */
    @Override
    public String toString() {
        return name;
    }
} 