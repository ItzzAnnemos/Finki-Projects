#include <iostream>
#include <cstring>

using namespace std;

class FudbalskaEkipa {
protected:
    char imeNaTrener[100];
    int golovi[10];
public:
    FudbalskaEkipa(char *imeNaTrener = "", int *golovi = NULL) {
        strcpy(this->imeNaTrener, imeNaTrener);
        for (int i = 0; i < 10; i++) {
            this->golovi[i] = golovi[i];
        }
    }

    virtual int uspeh() = 0;

    virtual char *getIme() = 0;

    virtual char *getTrener() = 0;

    FudbalskaEkipa &operator+=(int x) {
        golovi[0] = x;
        return *this;
    }

    bool operator>(FudbalskaEkipa &x) {
        if (this->uspeh() > x.uspeh())
            return true;
        else
            return false;
    }

    friend ostream &operator<<(ostream &out, FudbalskaEkipa &x) {
        out << x.getIme() << endl;
        out << x.imeNaTrener << endl;
        out << x.uspeh() << endl;
        return out;
    }
};

class Klub : public FudbalskaEkipa {
private:
    char ime[50];
    int brTituli;
public:
    Klub(char *imeNaTrener = "", int *golovi = NULL, char *ime = "", int brTituli = 1) : FudbalskaEkipa(imeNaTrener,
                                                                                                        golovi) {
        strcpy(this->ime, ime);
        this->brTituli = brTituli;
    }

    int uspeh() {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += golovi[i];
        }
        total *= 3;
        total += brTituli * 1000;

        return total;
    }

    char *getIme() {
        return ime;
    }

    char *getTrener() {
        return imeNaTrener;
    }

    friend ostream &operator<<(ostream &out, Klub &x) {
        out << x.ime << endl;
        out << x.imeNaTrener << endl;
        out << x.uspeh() << endl;
        return out;
    }

    Klub &operator+=(int x) {
        int tmp[10];
        for (int i = 0; i < 9; i++) {
            tmp[i] = golovi[i + 1];
        }
        for (int i = 0; i < 9; i++) {
            golovi[i] = tmp[i];
        }
        golovi[9] = x;

        return *this;
    }
};

class Reprezentacija : public FudbalskaEkipa {
private:
    char ime[100];
    int brNastapi;
public:
    Reprezentacija(char *imeNaTrener = "", int *golovi = NULL, char *ime = "", int brNastapi = 1) : FudbalskaEkipa(
            imeNaTrener, golovi) {
        strcpy(this->ime, ime);
        this->brNastapi = brNastapi;
    }

    int uspeh() {
        int total = 0;
        for (int i = 0; i < 10; i++) {
            total += golovi[i];
        }
        total *= 3;
        total += brNastapi * 50;

        return total;
    }

    char *getIme() {
        return ime;
    }

    char *getTrener() {
        return imeNaTrener;
    }

    friend ostream &operator<<(ostream &out, Reprezentacija &x) {
        out << x.ime << endl;
        out << x.imeNaTrener << endl;
        out << x.uspeh() << endl;
        return out;
    }

    Reprezentacija &operator+=(int x) {
        int tmp[10];
        for (int i = 0; i < 9; i++) {
            tmp[i] = golovi[i + 1];
        }
        for (int i = 0; i < 9; i++) {
            golovi[i] = tmp[i];
        }
        golovi[9] = x;

        return *this;
    }
};

void najdobarTrener(FudbalskaEkipa *x[], int n) {
    int index, max = 0;
    for (int i = 0; i < n; i++) {
        if (x[i]->uspeh() > max) {
            max = x[i]->uspeh();
            index = i;
        }
    }

    cout << x[index]->getIme() << endl;
    cout << x[index]->getTrener() << endl;
    cout << x[index]->uspeh() << endl;
}

int main() {
    int n;
    cin >> n;
    FudbalskaEkipa **ekipi = new FudbalskaEkipa *[n];
    char coach[100];
    int goals[10];
    char x[100];
    int tg;
    for (int i = 0; i < n; ++i) {
        int type;
        cin >> type;
        cin.getline(coach, 100);
        cin.getline(coach, 100);
        for (int j = 0; j < 10; ++j) {
            cin >> goals[j];
        }
        cin.getline(x, 100);
        cin.getline(x, 100);
        cin >> tg;
        if (type == 0) {
            ekipi[i] = new Klub(coach, goals, x, tg);
        } else if (type == 1) {
            ekipi[i] = new Reprezentacija(coach, goals, x, tg);
        }
    }
    cout << "===== SITE EKIPI =====" << endl;
    for (int i = 0; i < n; ++i) {
        cout << *ekipi[i];
    }
    cout << "===== DODADI GOLOVI =====" << endl;
    for (int i = 0; i < n; ++i) {
        int p;
        cin >> p;
        cout << "dodavam golovi: " << p << endl;
        *ekipi[i] += p;
    }
    cout << "===== SITE EKIPI =====" << endl;
    for (int i = 0; i < n; ++i) {
        cout << *ekipi[i];
    }
    cout << "===== NAJDOBAR TRENER =====" << endl;
    najdobarTrener(ekipi, n);
    for (int i = 0; i < n; ++i) {
        delete ekipi[i];
    }
    delete[] ekipi;
    return 0;
}