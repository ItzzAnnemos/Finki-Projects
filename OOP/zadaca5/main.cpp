#include <iostream>
#include <cstring>

using namespace std;

enum position {
    employee, manager, owner
};

class Employee {
private:
    char name[20];
    int salary;
    position p;
public:
    Employee() {
    }

    Employee(char *name, int salary, position pos) {
        strcpy(name, name);
        this->salary = salary;
        p = pos;
    }

    void setName(char *n) {
        strcpy(name, n);
    }

    void setSalary(int x) {
        salary = x;
    }

    void setPosition(position pos) {
        p = pos;
    }

    int getSalary() {
        return salary;
    }

    char const * getName() {
        return name;
    }

    position getPosition() {
        return p;
    }
};

void sort(Employee a[], int n) {
    int i, j;
    Employee tmp;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n - i - 1; j++) {
            if (a[j].getSalary() < a[j + 1].getSalary()) {
                tmp = a[j];
                a[j] = a[j + 1];
                a[j + 1] = tmp;
            }
        }
    }
}

int main() {
    Employee employees[100];
    int i, n, p;
    char name[20];
    int salary;

    cin >> n;
    for (i = 0; i < n; i++) {
        cin >> name;
        cin >> salary;
        cin >> p;
        employees[i].setName(name);
        employees[i].setSalary(salary);
        employees[i].setPosition((position) p);
    }

    sort(employees, n);

    for (i = 0; i < n; i++) {
        cout << i + 1 << ". " << employees[i].getName() << "\t" << employees[i].getSalary() << "\t"
             << employees[i].getPosition() << endl;
    }

    return 0;
}
