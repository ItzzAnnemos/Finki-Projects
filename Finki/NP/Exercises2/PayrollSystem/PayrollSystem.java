package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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

    public void readEmployees(InputStream is) {
        Scanner sc = new Scanner(is);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");

            if (Objects.equals(parts[0], "H")) {
                double tmp = hourlyRateByLevel.get(parts[2]);
                employees.add(new HourlyEmployee(parts[1], parts[2], Double.parseDouble(parts[3]), tmp));
            } else {
                double tmp = ticketRateByLevel.get(parts[2]);
                List<Integer> points = new ArrayList<>();
                for (int i = 3; i < parts.length; i++) {
                    points.add(Integer.parseInt(parts[i]));
                }
                employees.add(new FreelanceEmployee(parts[1], parts[2], points, tmp));
            }
        }
    }

    public Map<String, Set<Employee>> printEmployeesByLevels(OutputStream os, Set<String> levels) {
        Map<String, Set<Employee>> map = new TreeMap<>(Comparator.naturalOrder());
        Comparator<Employee> comparator = Comparator.comparing(Employee::getSalary).reversed()
                .thenComparing(Employee::getLevel);

        for (String level : levels) {
            List<Employee> list = new ArrayList<>();
            for (Employee employee : employees) {
                if (employee.getLevel().equals(level))
                    list.add(employee);
            }
            if (!list.isEmpty()) {
                list.sort(comparator);
                map.put(level, new LinkedHashSet<>(list));
            }
        }

        /*PrintWriter pw = new PrintWriter(os);
        pw.println(map);
        pw.flush();*/

        return map;
    }
}
