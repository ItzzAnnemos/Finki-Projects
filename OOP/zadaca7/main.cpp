#include <iostream>
#include <cstring>

using namespace std;
// ZADACA 7 !!!!
enum tip {
    pop, rap, rock
};

class Pesna {
private:
    char *ime;
    int minuti;
    tip type;
public:
    Pesna(char *ime = "ime", int minuti = 0, enum tip type = (tip)0) {
        this->ime = new char[strlen(ime) + 1];
        strcpy(this->ime, ime);
        this->minuti = minuti;
        this->type = type;
    }

    Pesna(const Pesna &p) {
        this->ime = new char[strlen(p.ime) + 1];
        strcpy(this->ime, p.ime);
        this->minuti = p.minuti;
        this->type = p.type;
    }

    Pesna operator=(const Pesna &p) {
        if (this != &p) {
            delete[] ime;
            this->ime = new char[strlen(p.ime) + 1];
            strcpy(this->ime, p.ime);
            this->minuti = p.minuti;
            this->type = p.type;
        }
        return *this;
    }

    ~Pesna() {
        delete[] ime;
    }

    void pecati() {
        cout << "\"" << ime << "\"-" << minuti << "min" << endl;
    }

    enum tip getTip() {
        return type;
    }

    int getMinuti() {
        return minuti;
    }
};

class CD {
private:
    Pesna pesni[10];
    int broj;
    int max;
    int vremetraenje = 0;
public:
    CD(int x) {
        max = x;
        this->broj = broj;
    }

    CD(const CD &c) {
        max = c.max;
        broj = c.broj;
        for (int i = 0; i < broj; i++) {
            pesni[i] = c.pesni[i];
        }
    }

    ~CD() {
    }

    void dodadiPesna(Pesna &p) {
        if (max < vremetraenje + p.getMinuti()) {
            return;
        }
        if (broj < 10) {
            pesni[broj++] = p;
            vremetraenje += p.getMinuti();
        }
    }

    Pesna getPesna(int k) {
        return pesni[k];
    }

    int getBroj() {
        return broj;
    }

    void pecatiPesniPoTip(enum tip t) {
        for (int j = 0; j < broj; j++) {
            if (t == pesni[j].getTip())
                pesni[j].pecati();
        }
    }

};

int main() {
    // se testira zadacata modularno
    int testCase;
    cin >> testCase;

    int n, minuti, kojtip;
    char ime[50];

    if (testCase == 1) {
        cout << "===== Testiranje na klasata Pesna ======" << endl;
        cin >> ime;
        cin >> minuti;
        cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
        Pesna p(ime, minuti, (tip) kojtip);
        p.pecati();
    } else if (testCase == 2) {
        cout << "===== Testiranje na klasata CD ======" << endl;
        CD omileno(20);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> minuti;
            cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
            Pesna p(ime, minuti, (tip) kojtip);
            omileno.dodadiPesna(p);
        }
        for (int i = 0; i < n; i++)
            (omileno.getPesna(i)).pecati();
    } else if (testCase == 3) {
        cout << "===== Testiranje na metodot dodadiPesna() od klasata CD ======" << endl;
        CD omileno(20);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> minuti;
            cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
            Pesna p(ime, minuti, (tip) kojtip);
            omileno.dodadiPesna(p);
        }
        for (int i = 0; i < omileno.getBroj(); i++)
            (omileno.getPesna(i)).pecati();
    } else if (testCase == 4) {
        cout << "===== Testiranje na metodot pecatiPesniPoTip() od klasata CD ======" << endl;
        CD omileno(20);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> minuti;
            cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
            Pesna p(ime, minuti, (tip) kojtip);
            omileno.dodadiPesna(p);
        }
        cin >> kojtip;
        omileno.pecatiPesniPoTip((tip) kojtip);

    } else if (testCase == 5) {
        cout << "===== Testiranje na metodot pecatiPesniPoTip() od klasata CD ======" << endl;
        CD omileno(20);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> ime;
            cin >> minuti;
            cin >> kojtip; //se vnesuva 0 za POP,1 za RAP i 2 za ROK
            Pesna p(ime, minuti, (tip) kojtip);
            omileno.dodadiPesna(p);
        }
        cin >> kojtip;
        omileno.pecatiPesniPoTip((tip) kojtip);

    }

    return 0;
}