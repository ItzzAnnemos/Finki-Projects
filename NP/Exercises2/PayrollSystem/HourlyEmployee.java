package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem;

public class HourlyEmployee extends Employee {
    private double hours;
    private double hourlyPay;

    public HourlyEmployee(String id, String level, double hours, double hourlyPay) {
        super(id, level);
        this.hours = hours;
        this.hourlyPay = hourlyPay;
    }

    public double getRegularHours() {
        if (hours <= 40)
            return hours;
        else
            return 40.0;
    }

    public double getOvertimeHours() {
        if (hours - 40 < 0)
            return 0.0;
        else
            return hours - 40;
    }

    @Override
    public double getSalary() {
        if (hours <= 40) {
            return hours * hourlyPay;
        } else {
            return (40 * hourlyPay) + ((hours - 40) * (hourlyPay * 1.5));
        }
    }

    @Override
    public String toString() {
        return String.format("Employee ID: %s Level: %s Salary: %.2f Regular hours: %.2f Overtime hours: %.2f",
                getId(), getLevel(), getSalary(), getRegularHours(), getOvertimeHours());
    }
}
