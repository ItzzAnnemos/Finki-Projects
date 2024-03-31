#include <iostream>
#include <cstring>

using namespace std;

enum tip {
    smartfon, kompjuter
};

class InvalidProductionDate {
public:
    InvalidProductionDate() {
    }

    void showMessage() {
        cout << "Невалидна година на производство" << endl;
    }
};

class Device {
private:
    char model[100];
    tip tipNaUred;
    static float casoviOsnovnaProverka;
    int godinaProiz;
public:
    Device(char *model = "", int tipNaUred = 0, int godinaProiz = 2000) {
        strcpy(this->model, model);
        this->tipNaUred = (tip) tipNaUred;
        this->godinaProiz = godinaProiz;
    }

    float casoviZaProverka() {
        float sum = casoviOsnovnaProverka;
        if (godinaProiz > 2015) {
            sum += 2;
        }
        if (tipNaUred == 1) {
            sum += 2;
        }
        return sum;
    }

    friend ostream &operator<<(ostream &out, Device &x) {
        out << x.model << endl;
        if (x.tipNaUred == 0) {
            out << "Mobilen";
        } else {
            out << "Laptop";
        }
        out << " " << x.casoviZaProverka() << endl;
        return out;
    }

    int getGodina() {
        return godinaProiz;
    }

    static void setPocetniCasovi(float x) {
        casoviOsnovnaProverka = x;
    }
};

class MobileServis {
private:
    char adresa[100];
    Device *devices;
    int n;
public:
    MobileServis(char *adresa = "") {
        strcpy(this->adresa, adresa);
        this->devices = nullptr;
        this->n = 0;
    }

    MobileServis &operator+=(Device &x) {
        if ((x.getGodina() > 2019) || (x.getGodina() < 2000)) {
            throw InvalidProductionDate();
        }
        Device *tmp = new Device[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i] = devices[i];
        }
        tmp[n] = x;
        delete[] devices;
        devices = new Device[n + 1];
        for (int i = 0; i < n + 1; i++) {
            devices[i] = tmp[i];
        }
        n++;
        delete[] tmp;
        return *this;
    }

    void pecatiCasovi() {
        cout << "Ime: " << adresa << endl;
        for (int i = 0; i < n; i++) {
            cout << devices[i];
        }
    }
};

float Device::casoviOsnovnaProverka = 1;

int main() {
    int testCase;
    cin >> testCase;
    char ime[100];
    int tipDevice;
    int godina;
    int n;
    Device devices[50];
    if (testCase == 1) {
        cout << "===== Testiranje na klasite ======" << endl;
        cin >> ime;
        cin >> tipDevice;
        cin >> godina;
        Device ig(ime, (tip) tipDevice, godina);
        cin >> ime;
        MobileServis t(ime);
        cout << ig;
    }
    if (testCase == 2) {
        cout << "===== Testiranje na operatorot += ======" << endl;
        cin >> ime;
        cin >> n;
        MobileServis t(ime);
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> tipDevice;
            cin >> godina;
            Device tmp(ime, (tip) tipDevice, godina);
            t += tmp;
        }
        t.pecatiCasovi();
    }
    if (testCase == 3) {
        cout << "===== Testiranje na isklucoci ======" << endl;
        cin >> ime;
        cin >> n;
        MobileServis t(ime);
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> tipDevice;
            cin >> godina;
            Device tmp(ime, (tip) tipDevice, godina);
            try {
                t += tmp;
            } catch (InvalidProductionDate e) {
                e.showMessage();
            }
        }
        t.pecatiCasovi();
    }
    if (testCase == 4) {
        cout << "===== Testiranje na konstruktori ======" << endl;
        cin >> ime;
        cin >> n;
        MobileServis t(ime);
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> tipDevice;
            cin >> godina;
            Device tmp(ime, (tip) tipDevice, godina);
            try {
                t += tmp;
            } catch (InvalidProductionDate e) {
                e.showMessage();
            }
        }
        MobileServis t2 = t;
        t2.pecatiCasovi();
    }
    if (testCase == 5) {
        cout << "===== Testiranje na static clenovi ======" << endl;
        cin >> ime;
        cin >> n;
        MobileServis t(ime);
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> tipDevice;
            cin >> godina;
            Device tmp(ime, (tip) tipDevice, godina);

            t += tmp;
        }
        t.pecatiCasovi();
        cout << "===== Promena na static clenovi ======" << endl;
        Device::setPocetniCasovi(2);
        t.pecatiCasovi();
    }

    if (testCase == 6) {
        cout << "===== Testiranje na kompletna funkcionalnost ======" << endl;
        cin >> ime;
        cin >> n;
        MobileServis t(ime);
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> tipDevice;
            cin >> godina;
            Device tmp(ime, (tip) tipDevice, godina);
            try {
                t += tmp;
            } catch (InvalidProductionDate e) {
                e.showMessage();
            }
        }
        Device::setPocetniCasovi(3);
        MobileServis t2 = t;
        t2.pecatiCasovi();
    }

    return 0;

}

