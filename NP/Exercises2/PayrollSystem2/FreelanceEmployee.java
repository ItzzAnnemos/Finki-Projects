package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem2;

import java.util.List;

public class FreelanceEmployee extends Employee {
    private List<Integer> points;
    private double payPerTicket;
    private String bonus;
    public FreelanceEmployee(char type, String id, String level, List<Integer> points, double payPerTicket, String bonus) {
        super(type, id, level);
        this.points = points;
        this.payPerTicket = payPerTicket;
        this.bonus = bonus;
    }

    @Override
    public double getSalary() {
        return points.stream().mapToDouble(Integer::doubleValue).sum() * payPerTicket;
    }

    public int getNumTickets() {
        return points.size();
    }

    @Override
    public double getBonus() {
        if (bonus.equals("false"))
            return 69.69;
        if (bonus.endsWith("%")) {
            return getSalary() * (Double.parseDouble(bonus.substring(0, bonus.length()-1)) / 100);
        } else {
            return Double.parseDouble(bonus);
        }
    }

    public int getPoints() {
        return points.stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public String toString() {
        if (getBonus() != 69.69) {
            return String.format("Employee ID: %s Level: %s Salary: %.2f Tickets count: %d Tickets points: %d Bonus: %.2f",
                    getId(), getLevel(), getSalary() + getBonus(), points.size(), getPoints(), getBonus());
        } else {
            return String.format("Employee ID: %s Level: %s Salary: %.2f Tickets count: %d Tickets points: %d",
                    getId(), getLevel(), getSalary(), points.size(), getPoints());
        }
    }
}
