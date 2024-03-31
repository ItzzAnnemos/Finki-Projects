#include <iostream>
#include <cstring>

using namespace std;

class MobilePhone {
private:
    char model[20];
    int brojModel;
    int godina;
public:
    MobilePhone() {
    }

    MobilePhone(char *model, int brojModel, int godina) {
        strcpy(this->model, model);
        this->brojModel = brojModel;
        this->godina = godina;
    }

    MobilePhone(const MobilePhone &m) {
        strcpy(model, m.model);
        brojModel = m.brojModel;
        godina = m.godina;
    }

    ~MobilePhone() {
    }

    void pecati() {
        cout << model << " " << brojModel << " release year: " << godina << endl;
    }
};

class Owner {
private:
    char ime[20];
    char prezime[20];
    MobilePhone phone;
public:
    Owner() {
    }

    Owner(char *ime, char *prezime, MobilePhone phone) {
        strcpy(this->ime, ime);
        strcpy(this->prezime, prezime);
        this->phone = phone;
    }

    Owner(const Owner &o) {
        strcpy(this->ime, ime);
        strcpy(this->prezime, prezime);
        this->phone = o.phone;
    }

    ~Owner() {
    }

    void print() {
        cout << ime << " " << prezime << " has a mobile phone: " << endl;
        phone.pecati();
    }
};

int main() {
    char model[20];
    int modelNumber;
    int year;
    char name[20];
    char surname[20];

    int testCase;

    cin >> testCase;

    cin >> model;
    cin >> modelNumber;
    cin >> year;
    cin >> name;
    cin >> surname;

    if (testCase == 1) {
        MobilePhone mobilePhone(model, modelNumber, year);

        Owner owner(name, surname, mobilePhone);
        owner.print();
    }
    if (testCase == 2) {
        MobilePhone mobilePhone(MobilePhone(model, modelNumber, year));

        Owner owner(name, surname, mobilePhone);
        owner.print();
    }

}
