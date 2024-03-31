#include <iostream>
#include <cstring>

using namespace std;

enum state {
    neispraven, se_poprava, ispraven
};

class Airplane {
private:
    char reg[13];
    char *ime;
    char maticen[20];
    state sostojba;
    int vkupno;
public:
    Airplane() {
        ime = nullptr;
    }

    Airplane(char *reg, char *ime, char *maticen, int vkupno) {
        strcpy(this->reg, reg);
        this->ime = new char[strlen(ime) + 1];
        strcpy(this->ime, ime);
        strcpy(this->maticen, maticen);
        this->vkupno = vkupno;
    }

    Airplane(char *reg, char *ime, char *maticen, int vkupno, int x) {
        strcpy(this->reg, reg);
        this->ime = new char[strlen(ime) + 1];
        strcpy(this->ime, ime);
        strcpy(this->maticen, maticen);
        this->vkupno = vkupno;
        this->sostojba = (state) x;
    }

    Airplane(const Airplane &x) {
        strcpy(this->reg, x.reg);
        this->ime = new char[strlen(x.ime) + 1];
        strcpy(this->ime, x.ime);
        strcpy(this->maticen, x.maticen);
        this->vkupno = x.vkupno;
        this->sostojba = x.sostojba;
    }

    Airplane operator=(const Airplane &x) {
        if (this != &x) {
            delete[] ime;
            strcpy(this->reg, x.reg);
            this->ime = new char[strlen(x.ime) + 1];
            strcpy(this->ime, x.ime);
            strcpy(this->maticen, x.maticen);
            this->vkupno = x.vkupno;
            this->sostojba = x.sostojba;
        }
        return *this;
    }

    ~Airplane() {
        delete[] ime;
    }

    int getSostojba() {
        return sostojba;
    }

    char * getIme() {
        return ime;
    }

    int getVkupno() {
        return vkupno;
    }

    void print() {
        cout << "ID: " << reg << " - ";
        if (sostojba == 0) {
            cout << "NOT_WORKING ";
        } else if (sostojba == 1) {
            cout << "REPAIRING ";
        } else {
            cout << "WORKING ";
        }
        cout << ime << ", " << vkupno << ", " << maticen << endl;
    }
};

class AirportService {
private:
    char ime[20];
    Airplane *avioni;
    int n;
public:
    AirportService() {
        avioni = nullptr;
        n = 0;
    }

    AirportService(char *ime) {
        strcpy(this->ime, ime);
        n = 0;
    }

    void print() {
        cout << ime << endl;
        for (int i = 0; i < n; i++) {
            avioni[i].print();
        }
    }

    void addAirplane(Airplane plane) {
        if (plane.getSostojba() == 0) {
            for (int i = 0;i < n;i++) {
                if (strcmp(avioni[i].getIme(), plane.getIme()) == 0) {
                    if (avioni[i].getVkupno() < plane.getVkupno()) {
                        Airplane tmp[n];
                        for (int j = 0;j < n;j++) {
                            tmp[j] = avioni[i];
                        }
                        

                    }
                }
            }
        }
    }

    Airplane serviceOldestAirplane() {

    }
};


int main() {
    int testCase;
    cin >> testCase;
    char ID[12];
    char company_name[20];
    char company_airport[20];
    int flight_hours;
    int state;
    if (testCase == 1) {
        cout << "TESTING CONSTRUCTOR FOR AIRPLANE" << endl;
        Airplane a;
        cout << "TEST FOR DEFAULT CONSTRUCTOR PASSED" << endl;
        Airplane a1("ZHN-96-TY", "FINKI-Airline", "TMF", 13);
        cout << "TEST FOR CONSTRUCTOR WITH 3 ARGUMENTS PASSED" << endl;
        Airplane a2("ZHN-96-TA", "FINKI1-Airline", "TMF", 13, 0);
        cout << "TEST FOR CONSTRUCTOR WITH 4 ARGUMENTS PASSED" << endl;
    } else if (testCase == 2) {
        cout << "TESTING COPY-CONSTRUCTOR AND OPERATOR = (ASSIGNMENT) FOR AIRPLANE" << endl;
        Airplane p("ZHN-96-TA", "FINKI-Airline", "TMF", 13, 0);
        Airplane p1(p);
        cout << "TEST FOR COPY CONSTRUCTOR PASSED" << endl;
        Airplane p2;
        p2 = p;
        cout << "TEST FOR OPERATOR = (ASSIGNMENT) PASSED" << endl;
    } else if (testCase == 3) {
        cout << "TESTING PRINT() FOR AIRPLANE" << endl;
        cin >> ID >> company_name >> company_airport >> flight_hours >> state;
        Airplane p(ID, company_name, company_airport, flight_hours, state);
        p.print();
    } else if (testCase == 4) {
        cout << "TESTING CONSTRUCTOR FOR AIRPORTSERVICE" << endl;
        AirportService as("FINKI");
        cout << "TEST PASSED" << endl;
    } else if (testCase == 5) {
        cout << "TESTING ADD() AND PRINT() FOR AIRPORTSERVICE" << endl;
        AirportService as("FINKI");
        int n;
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ID >> company_name >> company_airport >> flight_hours >> state;
            Airplane p(ID, company_name, company_airport, flight_hours, state);
            as.addAirplane(p);
        }
        as.print();
    } else if (testCase == 6) {
        cout << "TESTING serviceOldestAirplane() AND PRINT() FOR AIRPORTSERVICE" << endl;
        AirportService as("FINKI");
        int n;
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ID >> company_name >> company_airport >> flight_hours >> state;
            Airplane p(ID, company_name, company_airport, flight_hours, state);
            as.addAirplane(p);
        }
        Airplane p = as.serviceOldestAirplane();
        cout << "Removed plane:" << endl;
        p.print();
        cout << "-----------------" << endl;
        as.print();
    } else if (testCase == 7) {
        cout << "TESTING COPY CONSTRUCTOR AND OPERATOR = FOR AIRPORTSERVICE" << endl;
        AirportService as("FINKI");
        Airplane a1("ZHN-96-TY", "FINKI-Airline", "TMF", 13);
        as.addAirplane(a1);

        AirportService s1 = as; //copy constructor
        AirportService s2;
        s2 = s1; //operator =
        s1.print();
        s2.print();
    }
    return 0;
}