package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem2;

public class HourlyEmployee extends Employee {
    private double hours;
    private double hourlyPay;
    private String bonus;

    public HourlyEmployee(char type, String id, String level, double hours, double hourlyPay, String bonus) {
        super(type, id, level);
        this.hours = hours;
        this.hourlyPay = hourlyPay;
        this.bonus = bonus;
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

    public double getOvertimeSalary() {
        return getOvertimeHours() * hourlyPay * 1.5;
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
    public double getBonus() {
        if (bonus.equals("false"))
            return 69.69;
        if (bonus.endsWith("%")) {
            return getSalary() * (Double.parseDouble(bonus.substring(0, bonus.length()-1)) / 100);
        } else {
            return Double.parseDouble(bonus);
        }
    }

    @Override
    public String toString() {
        if (getBonus() != 69.69) {
            return String.format("Employee ID: %s Level: %s Salary: %.2f Regular hours: %.2f Overtime hours: %.2f Bonus: %.2f",
                    getId(), getLevel(), getSalary() + getBonus(), getRegularHours(), getOvertimeHours(), getBonus());
        } else {
            return String.format("Employee ID: %s Level: %s Salary: %.2f Regular hours: %.2f Overtime hours: %.2f",
                    getId(), getLevel(), getSalary(), getRegularHours(), getOvertimeHours());
        }
    }
}
