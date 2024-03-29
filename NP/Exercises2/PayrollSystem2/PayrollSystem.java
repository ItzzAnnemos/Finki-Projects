package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem2;

import java.util.*;
import java.util.stream.Collectors;

public class PayrollSystem {
    private List<Employee> employees;
    private Map<String, Double> hourlyRateByLevel;
    private Map<String, Double> ticketRateByLevel;

    public PayrollSystem(Map<String, Double> hourlyRateByLevel, Map<String, Double> ticketRateByLevel) {
        this.hourlyRateByLevel = hourlyRateByLevel;
        this.ticketRateByLevel = ticketRateByLevel;
        this.employees = new ArrayList<>();
    }

    public Employee createEmployee(String line) throws BonusNotAllowedException {
        String[] bonus = line.split("\\s+");
        String[] parts = bonus[0].split(";");
        if (bonus.length < 2) {
            if (Objects.equals(parts[0], "H")) {
                double tmp = hourlyRateByLevel.get(parts[2]);
                employees.add(new HourlyEmployee(parts[0].toCharArray()[0], parts[1], parts[2], Double.parseDouble(parts[3]), tmp, "false"));
            } else {
                double tmp = ticketRateByLevel.get(parts[2]);
                List<Integer> points = new ArrayList<>();
                for (int i = 3; i < parts.length; i++) {
                    points.add(Integer.parseInt(parts[i]));
                }
                employees.add(new FreelanceEmployee(parts[0].toCharArray()[0], parts[1], parts[2], points, tmp, "false"));
            }
        } else {
            if (bonus[1].endsWith("%") && Double.parseDouble(bonus[1].substring(0, bonus[1].length() - 1)) > 20) {
                throw new BonusNotAllowedException(bonus[1]);
            } else if (!bonus[1].endsWith("%") && Double.parseDouble(bonus[1]) > 1000) {
                throw new BonusNotAllowedException(bonus[1]);
            } else {
                if (Objects.equals(parts[0], "H")) {
                    double tmp = hourlyRateByLevel.get(parts[2]);
                    employees.add(new HourlyEmployee(parts[0].toCharArray()[0], parts[1], parts[2], Double.parseDouble(parts[3]), tmp, bonus[1]));
                } else {
                    double tmp = ticketRateByLevel.get(parts[2]);
                    List<Integer> points = new ArrayList<>();
                    for (int i = 3; i < parts.length; i++) {
                        points.add(Integer.parseInt(parts[i]));
                    }
                    employees.add(new FreelanceEmployee(parts[0].toCharArray()[0], parts[1], parts[2], points, tmp, bonus[1]));
                }
            }
        }
        return employees.get(employees.size() - 1);
    }

    public Map<String, Double> getOvertimeSalaryForLevels() {
        Map<String, Double> map = new HashMap<>();
        for (Employee employee : employees) {
            if (employee instanceof HourlyEmployee) {
                String level = employee.getLevel();
                if (map.containsKey(level)) {
                    map.replace(level, map.get(level) + ((HourlyEmployee) employee).getOvertimeSalary());
                } else {
                    map.put(level, ((HourlyEmployee) employee).getOvertimeSalary());
                }
            }
        }

        return map;
    }

    public void printStatisticsForOvertimeSalary() {
        DoubleSummaryStatistics dss = new DoubleSummaryStatistics();
        for (Employee employee : employees) {
            if (employee instanceof HourlyEmployee) {
                dss.accept(((HourlyEmployee) employee).getOvertimeSalary());
            }
        }

        System.out.printf("Statistics for overtime salary: Min: %.2f Average: %.2f Max: %.2f Sum: %.2f",
                dss.getMin(), dss.getAverage(), dss.getMax(), dss.getSum());
    }

    public Map<String, Integer> ticketsDoneByLevel() {
        Map<String, Integer> map = new HashMap<>();
        for (Employee employee : employees) {
            if (employee instanceof FreelanceEmployee) {
                String level = employee.getLevel();
                if (map.containsKey(level)) {
                    map.replace(level, map.get(level) + ((FreelanceEmployee) employee).getNumTickets());
                } else {
                    map.put(level, ((FreelanceEmployee) employee).getNumTickets());
                }
            }
        }

        return map;
    }

    public Collection<Employee> getFirstNEmployeesByBonus(int n) {
        Comparator<Employee> comparator = Comparator.comparing(Employee::getBonus).reversed();

        employees.sort(comparator);

        return employees.stream()
                .limit(n)
                .collect(Collectors.toList());
    }
}
