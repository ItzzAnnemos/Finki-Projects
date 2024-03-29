package mk.ukim.finki.NP.ZadaciZaVezbanje2.PayrollSystem2;

public abstract class Employee {
    private char type;
    private String Id;
    private String level;

    public char getType() {
        return type;
    }

    public Employee(char type, String id, String level) {
        Id = id;
        this.level = level;
        this.type = type;
    }

    public String getId() {
        return Id;
    }

    public String getLevel() {
        return level;
    }

    public abstract double getSalary();

    public abstract double getBonus();
}
