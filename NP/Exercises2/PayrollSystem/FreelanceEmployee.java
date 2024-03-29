package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem;

import java.util.List;

public class FreelanceEmployee extends Employee {
    private List<Integer> points;
    private double payPerTicket;
    public FreelanceEmployee(String id, String level, List<Integer> points, double payPerTicket) {
        super(id, level);
        this.points = points;
        this.payPerTicket = payPerTicket;
    }

    @Override
    public double getSalary() {
        return points.stream().mapToDouble(Integer::doubleValue).sum() * payPerTicket;
    }

    public int getPoints() {
        return points.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f Tickets count: %d Tickets points: %d",
                getId(), getLevel(), getSalary(), points.size(), getPoints());
    }
}
