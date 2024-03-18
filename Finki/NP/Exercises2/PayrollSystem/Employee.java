package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem;

public abstract class Employee {
    private String Id;
    private String level;

    public Employee(String id, String level) {
        Id = id;
        this.level = level;
    }

    public String getId() {
        return Id;
    }

    public String getLevel() {
        return level;
    }

    public abstract double getSalary();
}
