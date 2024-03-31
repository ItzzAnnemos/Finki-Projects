#include <iostream>
#include <cstring>

using namespace std;

// input:
/*John
30
5000
c++
Mike
40
8000
3
Jane
45
6000
Java
Smith
28
5700
2
Ana
33
6200
Python*/

class Employee {
protected:
    char ime[50];
    int godini;
public:
    Employee(char *ime = "", int godini = 0) {
        strcpy(this->ime, ime);
        this->godini = godini;
    }

    virtual void displayInfo() {
        cout << "Name: " << ime << endl;
        cout << "Age: " << godini << endl;
    }
};

class Payable {
protected:
    float plata;
public:
    Payable(float plata = 0.0) {
        this->plata = plata;
    }

    virtual float calculateSalary() = 0;
};

class Developer : public Employee, public Payable {
protected:
    char jazik[50];
public:
    Developer(char *ime = "", int godini = 0, float plata = 0.0, char *jazik = "") : Employee(ime, godini),
                                                                                     Payable(plata) {
        strcpy(this->jazik, jazik);
    }

    float calculateSalary() {
        return plata * 0.9;
    }

    void displayInfo() {
        cout << "-- Developer Information --" << endl;
        Employee::displayInfo();
        cout << "Salary: $" << calculateSalary() << endl;
        cout << "Programming Language: " << jazik << endl;
    }
};

class Manager : public Employee, public Payable {
protected:
    int oddeli;
public:
    Manager(char *ime = "", int godini = 0, float plata = 0.0, int oddeli = 0) : Employee(ime, godini), Payable(plata) {
        this->oddeli = oddeli;
    }

    float calculateSalary() {
        float bonus = 1.0;
        for (int i = 0; i < oddeli; i++) {
            bonus += 0.05;
        }
        return plata * bonus;
    }

    void displayInfo() {
        cout << "-- Manager Information --" << endl;
        Employee::displayInfo();
        cout << "Salary: $" << calculateSalary() << endl;
        cout << "Number of Departments: " << oddeli << endl;
    }
};

double biggestSalary(Payable *payable[], int n) {
    Payable *maxSalaryEmployee = payable[0];

    for (int i = 1; i < n; i++) {
        if (payable[i]->calculateSalary() > maxSalaryEmployee->calculateSalary()) {
            maxSalaryEmployee = payable[i];
        }
    }

    return maxSalaryEmployee->calculateSalary();
}

int main() {
    Payable *payable[5];
    Developer developers[5];
    Manager managers[5];
    int j = 0, k = 0;
    for (int i = 0; i < 5; i++) {
        char name[50];
        int age;
        double salary;
        cin >> name >> age >> salary;
        if (i % 2 == 0) {
            char programmingLanguage[50];
            cin >> programmingLanguage;
            developers[j] = Developer(name, age, salary, programmingLanguage);
            developers[j].displayInfo();
            payable[i] = &developers[j];
            j++;
        } else {
            int numberOfDepartments;
            cin >> numberOfDepartments;
            managers[k] = Manager(name, age, salary, numberOfDepartments);
            managers[k].displayInfo();
            payable[i] = &managers[k];
            k++;
        }
    }
    cout << endl << "Biggest Salary: " << biggestSalary(payable, 5);
    return 0;
}
