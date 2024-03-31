#include <iostream>
#include <cstring>
#include <cmath>

using namespace std;

class Employee {
private:
    char name[100];
    char surname[100];
    int salary;
public:
    Employee() {
    }

    Employee(char *name, char *surname, int salary) {
        strcpy(this->name, name);
        strcpy(this->surname, surname);
        this->salary = salary;
    }

    Employee(const Employee &x) {
        strcpy(name, x.name);
        strcpy(surname, x.surname);
        salary = x.salary;
    }

    ~Employee() {
    }

    void printEmployee() {
        cout << "Employee name: " << name << endl;
        cout << "Employee surname: " << surname << endl;
        cout << "Employee salary: " << salary << endl;
    }

    void setName(char *x) {
        strcpy(name, x);
    }

    void setSurame(char *y) {
        strcpy(name, y);
    }

    void setSalary(int z) {
        salary = z;
    }

    char *getName() {
        return name;
    }

    char *getSurname() {
        return surname;
    }

    int getSalary() {
        return salary;
    }
};

class TechCompany {
private:
    char name[100];
    Employee *employees;
    int numOfEmployees;
public:
    TechCompany(char *name = "name", int n = 0) {
        strcpy(this->name, name);
        numOfEmployees = n;
        employees = new Employee[n];
    }

    TechCompany(const TechCompany &x) {
        strcpy(name, x.name);
        numOfEmployees = x.numOfEmployees;
        employees = new Employee[numOfEmployees];
        for (int i = 0; i < numOfEmployees; i++) {
            employees[i] = x.employees[i];
        }
    }

    ~TechCompany() {
        delete[] employees;
    }

    double getAverageSalary() {
        int sum = 0;
        for (int i = 0; i < numOfEmployees; i++) {
            sum += employees[i].getSalary();
        }
        return (double) sum / numOfEmployees;
    }

    void addEmployee(Employee &x) {
        employees[numOfEmployees++] = x;
    }

    void setName(char *x) {
        strcpy(name, x);
    }

    void setNumOfEmployees(int n) {
        numOfEmployees = n;
    }

    char *getName() {
        return name;
    }

    int getNumEmployees() {
        return numOfEmployees;
    }

    Employee getEmployee(int k) {
        return employees[k];
    }
};

TechCompany printCompanyWithHighestAverageSalary(TechCompany companies[], int numCompanies) {
    int i;
    TechCompany tmp = companies[0];
    for (i = 1; i < numCompanies; i++) {
        if (tmp.getAverageSalary() <= companies[i].getAverageSalary()) {
            tmp = companies[i];
        }
    }
    return tmp;
}

TechCompany printCompanyWithHighestStdSalary(TechCompany companies[], int numCompanies) {
    int highestIndex = -1;
    TechCompany tmp = companies[0];
    double highestStdSalary = -1;

    for (int i = 0; i < numCompanies; i++) {
        int numEmployees = companies[i].getNumEmployees();

        double meanSalary = companies[i].getAverageSalary();
        double variance = 0.0;

        for (int j = 0; j < numEmployees; j++) {
            double diff = companies[i].getEmployee(j).getSalary() - meanSalary;
            variance += diff * diff;
        }

        double stdSalary = std::sqrt(variance / (numEmployees - 1));

        if (stdSalary > highestStdSalary) {
            highestStdSalary = stdSalary;
            highestIndex = i;
        }
    }

    return companies[highestIndex];

}

bool isSameCompany(TechCompany company1, TechCompany company2) {
    if (company1.getName() == company2.getName()) {
        return true;
    } else
        return false;
}

int main() {
    const int MAX_COMPANIES = 10;
    const int MAX_EMPLOYEES = 20;

    TechCompany companies[MAX_COMPANIES];

    int n;
    std::cin >> n;

    for (int i = 0; i < n; i++) {
        char name[100];
        std::cin >> name;

        TechCompany company(name);

        int m;
        std::cin >> m;

        for (int j = 0; j < m; j++) {
            char name[100];
            char surname[100];
            int salary;

            std::cin >> name;

            std::cin >> surname;

            std::cin >> salary;

            Employee employee(name, surname, salary);

            company.addEmployee(employee);
        }

        companies[i] = company;
    }

    TechCompany copy = companies[0];

    std::cout << "-->Testing get and set methods for one object and copy constructor" << std::endl;
    copy.setName("copy");
    std::cout << copy.getName() << std::endl;


    std::cout << "-->Testing addEmployee function" << std::endl;
    Employee newEmployee("John", "Doe", 5000);
    copy.addEmployee(newEmployee);
    std::cout << "Number of employees in copy: " << copy.getNumEmployees() << std::endl;


    std::cout << "-->Testing copy of first employee" << std::endl;
    Employee firstEmployee = copy.getEmployee(0);
    firstEmployee.printEmployee();


    std::cout << "-->Testing methods" << std::endl;
    TechCompany t = printCompanyWithHighestAverageSalary(companies, n);
    TechCompany t1 = printCompanyWithHighestStdSalary(companies, n);

    std::cout << "Tech company with the highest average salary: " << t.getName() << std::endl;
    std::cout << "Tech company with the highest standard deviation for salaries: " << t1.getName() << std::endl;

    if (isSameCompany(t, t1)) {
        std::cout << "The tech company: " << t.getName()
                  << " has the highest standard deviation and highest average salary" << std::endl;
    }
    return 0;
}
