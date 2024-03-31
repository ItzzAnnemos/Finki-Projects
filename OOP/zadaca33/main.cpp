#include <iostream>
#include <cstring>

using namespace std;

class Vozac {
protected:
    char ime[100];
    int vozrast;
    int brTrki;
    bool veteran;
public:
    Vozac(char *ime, int vozrast = 0, int brTrki = 0, bool veteran = true) {
        strcpy(this->ime, ime);
        this->vozrast = vozrast;
        this->brTrki = brTrki;
        this->veteran = veteran;
    }

    friend ostream &operator<<(ostream &out, Vozac &x) {
        out << x.ime << endl;
        out << x.vozrast << endl;
        out << x.brTrki << endl;
        if (x.veteran) {
            out << "VETERAN" << endl;
        }
        return out;
    }

    bool operator==(const Vozac &x) const {
        if (this->zarabotuvacka() == x.zarabotuvacka()) {
            return true;
        } else
            return false;
    }

    virtual float danok() = 0;

    virtual int zarabotuvacka() const = 0;
};

class Avtomobilist : public Vozac {
private:
    double cenaAvtomobil;
public:
    Avtomobilist(char *ime, int vozrast = 0, int brTrki = 0, bool veteran = true, double cenaAvtomobil = 0.0)
            : Vozac(
            ime, vozrast, brTrki, veteran) {
        this->cenaAvtomobil = cenaAvtomobil;
    }

    int zarabotuvacka() const {
        return (cenaAvtomobil / 5.0);
    }

    float danok() {
        if (brTrki > 10) {
            return zarabotuvacka() * 0.15;
        } else
            return zarabotuvacka() * 0.1;
    }
};

class Motociklist : public Vozac {
private:
    int mokjMotor;
public:
    Motociklist(char *ime, int vozrast = 0, int brTrki = 0, bool veteran = true, int mokjMotor = 1) :
            Vozac(ime, vozrast, brTrki, veteran) {
        this->mokjMotor = mokjMotor;
    }

    int zarabotuvacka() const {
        return mokjMotor * 20;
    }

    float danok() {
        if (veteran) {
            return zarabotuvacka() * 0.25;
        } else
            return zarabotuvacka() * 0.2;
    }
};

int soIstaZarabotuvachka(Vozac *v[], int n, Vozac *x) {
    int max = 0;
    for (int i = 0; i < n; i++) {
        if (v[i]->zarabotuvacka() == x->zarabotuvacka()) {
            max++;
        }
    }
    return max;
}

int main() {
    int n, x;
    cin >> n >> x;
    Vozac **v = new Vozac *[n];
    char ime[100];
    int vozrast;
    int trki;
    bool vet;
    for (int i = 0; i < n; ++i) {
        cin >> ime >> vozrast >> trki >> vet;
        if (i < x) {
            float cena_avto;
            cin >> cena_avto;
            v[i] = new Avtomobilist(ime, vozrast, trki, vet, cena_avto);
        } else {
            int mokjnost;
            cin >> mokjnost;
            v[i] = new Motociklist(ime, vozrast, trki, vet, mokjnost);
        }
    }
    cout << "=== DANOK ===" << endl;
    for (int i = 0; i < n; ++i) {
        cout << *v[i];
        cout << v[i]->danok() << endl;
    }
    cin >> ime >> vozrast >> trki >> vet;
    int mokjnost;
    cin >> mokjnost;
    Vozac *vx = new Motociklist(ime, vozrast, trki, vet, mokjnost);
    cout << "=== VOZAC X ===" << endl;
    cout << *vx;
    cout << "=== SO ISTA ZARABOTUVACKA KAKO VOZAC X ===" << endl;
    cout << soIstaZarabotuvachka(v, n, vx);
    for (int i = 0; i < n; ++i) {
        delete v[i];
    }
    delete[] v;
    delete vx;
    return 0;
}