#include <iostream>
#include <cstring>

using namespace std;

class Delo {
private:
    char ime[50];
    int godina;
    char poteklo[50];
public:
    Delo() {
        strcpy(ime, "");
        this->godina = 0;
        strcpy(poteklo, "");
    }

    Delo(char *ime, int godina, char *poteklo) {
        strcpy(this->ime, ime);
        this->godina = godina;
        strcpy(this->poteklo, poteklo);
    }

    Delo(const Delo &d) {
        strcpy(this->ime, d.ime);
        this->godina = d.godina;
        strcpy(this->poteklo, d.poteklo);
    }

    Delo &operator=(const Delo &d) {
        if (this != &d) {
            strcpy(this->ime, d.ime);
            this->godina = d.godina;
            strcpy(this->poteklo, d.poteklo);
        }
        return *this;
    }

    int getGodina() {
        return godina;
    }

    char *getPoteklo() {
        return poteklo;
    }

    char *getIme() {
        return ime;
    }

    bool operator==(Delo &x) {
        if (strcmp(this->ime, x.ime) == 0) {
            return true;
        } else
            return false;
    }
};

class Pretstava {
protected:
    Delo delo;
    int brProdKarti;
    char data[15];
public:
    Pretstava() {
    }

    Pretstava(Delo delo, int brProdKarti, char *data) {
        this->delo = delo;
        this->brProdKarti = brProdKarti;
        strcpy(this->data, data);
    }

    Delo getDelo() {
        return delo;
    }

    int brojProdadeniKarti() {
        return brProdKarti;
    }

    virtual int cena() {
        int n, m;
        if (delo.getGodina() >= 1900) {
            m = 50;
        } else if (delo.getGodina() >= 1800 && delo.getGodina() < 1900) {
            m = 75;
        } else {
            m = 100;
        }
        if (strcmp(delo.getPoteklo(), "Italija") == 0) {
            n = 100;
        } else if (strcmp(delo.getPoteklo(), "Rusija") == 0) {
            n = 150;
        } else {
            n = 80;
        }

        return n + m;
    }
};

class Opera : public Pretstava {
public:
    Opera(Delo d, int brProdKarti, char *data) : Pretstava(d, brProdKarti, data) {
    }
};

class Balet : public Pretstava {
private:
    static int cenaBalet;
public:
    Balet(Delo d, int brProdKarti, char *data) : Pretstava(d, brProdKarti, data) {
    }

    int cena() {
        return Pretstava::cena() + cenaBalet;
    }

    static void setCenaBalet(int &x) {
        Balet::cenaBalet = x;
    }
};

int Balet::cenaBalet = 150;

int prihod(Pretstava *p[], int n) {
    int vkupno = 0;
    for (int i = 0; i < n; i++) {
        vkupno += p[i]->brojProdadeniKarti() * p[i]->cena();
    }
    return vkupno;
}

int brojPretstaviNaDelo(Pretstava *p[], int n, Delo d) {
    int count = 0;
    for (int i = 0; i < n; i++) {
        if (p[i]->getDelo() == d) {
            count++;
        }
    }
    return count;
}

Delo readDelo() {
    char ime[50];
    int godina;
    char zemja[50];
    cin >> ime >> godina >> zemja;
    return Delo(ime, godina, zemja);
}

//citanje na pretstava
Pretstava *readPretstava() {
    int tip; //0 za Balet , 1 za Opera
    cin >> tip;
    Delo d = readDelo();
    int brojProdadeni;
    char data[15];
    cin >> brojProdadeni >> data;
    if (tip == 0) return new Balet(d, brojProdadeni, data);
    else return new Opera(d, brojProdadeni, data);
}

int main() {
    int test_case;
    cin >> test_case;

    switch (test_case) {
        case 1:
            //Testiranje na klasite Opera i Balet
        {
            cout << "======TEST CASE 1=======" << endl;
            Pretstava *p1 = readPretstava();
            cout << p1->getDelo().getIme() << endl;
            Pretstava *p2 = readPretstava();
            cout << p2->getDelo().getIme() << endl;
        }
            break;

        case 2:
            //Testiranje na  klasite Opera i Balet so cena
        {
            cout << "======TEST CASE 2=======" << endl;
            Pretstava *p1 = readPretstava();
            cout << p1->cena() << endl;
            Pretstava *p2 = readPretstava();
            cout << p2->cena() << endl;
        }
            break;

        case 3:
            //Testiranje na operator ==
        {
            cout << "======TEST CASE 3=======" << endl;
            Delo f1 = readDelo();
            Delo f2 = readDelo();
            Delo f3 = readDelo();

            if (f1 == f2) cout << "Isti se" << endl; else cout << "Ne se isti" << endl;
            if (f1 == f3) cout << "Isti se" << endl; else cout << "Ne se isti" << endl;

        }
            break;

        case 4:
            //testiranje na funkcijata prihod
        {
            cout << "======TEST CASE 4=======" << endl;
            int n;
            cin >> n;
            Pretstava **pole = new Pretstava *[n];
            for (int i = 0; i < n; i++) {
                pole[i] = readPretstava();

            }
            cout << prihod(pole, n);
        }
            break;

        case 5:
            //testiranje na prihod so izmena na cena za 3d proekcii
        {
            cout << "======TEST CASE 5=======" << endl;
            int cenaBalet;
            cin >> cenaBalet;
            Balet::setCenaBalet(cenaBalet);
            int n;
            cin >> n;
            Pretstava **pole = new Pretstava *[n];
            for (int i = 0; i < n; i++) {
                pole[i] = readPretstava();
            }
            cout << prihod(pole, n);
        }
            break;

        case 6:
            //testiranje na brojPretstaviNaDelo
        {
            cout << "======TEST CASE 6=======" << endl;
            int n;
            cin >> n;
            Pretstava **pole = new Pretstava *[n];
            for (int i = 0; i < n; i++) {
                pole[i] = readPretstava();
            }

            Delo f = readDelo();
            cout << brojPretstaviNaDelo(pole, n, f);
        }
            break;

    };


    return 0;
}

