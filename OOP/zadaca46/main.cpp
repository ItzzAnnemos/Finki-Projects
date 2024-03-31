#include <iostream>
#include <string.h>
#include <cstdlib>

using namespace std;

class NegativnaVrednost {
public:
    NegativnaVrednost() {
    }

    void showMessage() {
        cout << "Oglasot ima nevalidna vrednost za cenata i nema da bide evidentiran!" << endl;
    }
};

class Oglas {
private:
    char naslov[51];
    char kategorija[31];
    char opis[101];
    float cena;
public:
    Oglas(char *naslov = "", char *kategorija = "", char *opis = "", float cena = 0.0) {
        strcpy(this->naslov, naslov);
        strcpy(this->kategorija, kategorija);
        strcpy(this->opis, opis);
        this->cena = cena;
    }

    Oglas &operator=(const Oglas &x) {
        if (this != &x) {
            strcpy(this->naslov, x.naslov);
            strcpy(this->kategorija, x.kategorija);
            strcpy(this->opis, x.opis);
            this->cena = x.cena;
        }
        return *this;
    }

    bool operator>(const Oglas &x) {
        if (this->cena > x.cena)
            return true;
        else
            return false;
    }

    friend ostream &operator<<(ostream &out, Oglas &x) {
        out << x.naslov << endl;
        out << x.opis << endl;
        out << x.cena << " evra" << endl;
        return out;
    }

    float getCena() {
        return cena;
    }

    char *getKategorija() {
        return kategorija;
    }

    void print() {
        cout << naslov << endl;
        cout << opis << endl;
        cout << cena << " evra" << endl;
    }
};

class Oglasnik {
private:
    char ime[20];
    Oglas *oglasi;
    int n;
public:
    Oglasnik(char *ime = "") {
        strcpy(this->ime, ime);
        this->n = 0;
        this->oglasi = nullptr;
    }

    ~Oglasnik() {
        delete[] oglasi;
    }

    Oglasnik &operator+=(Oglas &x) {
        if (x.getCena() < 0) {
            throw NegativnaVrednost();
        }

        if (n == 0) {
            oglasi = new Oglas[1];
            oglasi[0] = x;
            n++;
        } else {
            Oglas *tmp = new Oglas[n + 1];
            for (int i = 0; i < n; i++) {
                tmp[i] = oglasi[i];
            }
            tmp[n] = x;
            delete[] oglasi;
            oglasi = new Oglas[n + 1];
            for (int i = 0; i < n + 1; i++) {
                oglasi[i] = tmp[i];
            }
            n++;
            delete[] tmp;
        }
        return *this;
    }

    friend ostream &operator<<(ostream &out, Oglasnik &x) {
        out << x.ime << endl;
        for (int i = 0; i < x.n; i++) {
            x.oglasi[i].print();
            out << endl;
        }
        return out;
    }

    void oglasiOdKategorija(const char *k) {
        for (int i = 0; i < n; i++) {
            if (strcmp(oglasi[i].getKategorija(), k) == 0) {
                oglasi[i].print();
                cout << endl;
            }
        }
    }

    void najniskaCena() {
        int index;
        float min = 99999.9;
        for (int i = 0; i < n; i++) {
            if (oglasi[i].getCena() < min) {
                min = oglasi[i].getCena();
                index = i;
            }
        }
        cout << oglasi[index];
    }
};

int main() {

    char naslov[50];
    char kategorija[30];
    char opis[100];
    float cena;
    char naziv[50];
    char k[30];
    int n;

    int tip;
    cin >> tip;

    if (tip == 1) {
        cout << "-----Test Oglas & operator <<-----" << endl;
        cin.get();
        cin.getline(naslov, 49);
        cin.getline(kategorija, 29);
        cin.getline(opis, 99);
        cin >> cena;
        Oglas o(naslov, kategorija, opis, cena);
        cout << o;
    } else if (tip == 2) {
        cout << "-----Test Oglas & operator > -----" << endl;
        cin.get();
        cin.getline(naslov, 49);
        cin.getline(kategorija, 29);
        cin.getline(opis, 99);
        cin >> cena;
        Oglas o1(naslov, kategorija, opis, cena);
        cin.get();
        cin.getline(naslov, 49);
        cin.getline(kategorija, 29);
        cin.getline(opis, 99);
        cin >> cena;
        Oglas o2(naslov, kategorija, opis, cena);
        if (o1 > o2) cout << "Prviot oglas e poskap." << endl;
        else cout << "Prviot oglas ne e poskap." << endl;
    } else if (tip == 3) {
        cout << "-----Test Oglasnik, operator +=, operator << -----" << endl;
        cin.get();
        cin.getline(naziv, 49);
        cin >> n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++) {
            cin.get();
            cin.getline(naslov, 49);
            cin.getline(kategorija, 29);
            cin.getline(opis, 99);
            cin >> cena;
            Oglas o(naslov, kategorija, opis, cena);
            try {
                ogl += o;
            } catch (NegativnaVrednost e) {
                e.showMessage();
            }
        }
        cout << ogl;
    } else if (tip == 4) {
        cout << "-----Test oglasOdKategorija -----" << endl;
        cin.get();
        cin.getline(naziv, 49);
        cin >> n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++) {
            cin.get();
            cin.getline(naslov, 49);
            cin.getline(kategorija, 29);
            cin.getline(opis, 99);
            cin >> cena;
            Oglas o(naslov, kategorija, opis, cena);
            try {
                ogl += o;
            } catch (NegativnaVrednost e) {
                e.showMessage();
            }
        }
        cin.get();
        cin.getline(k, 29);
        cout << "Oglasi od kategorijata: " << k << endl;
        ogl.oglasiOdKategorija(k);

    } else if (tip == 5) {
        cout << "-----Test Exception -----" << endl;
        cin.get();
        cin.getline(naziv, 49);
        cin >> n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++) {
            cin.get();
            cin.getline(naslov, 49);
            cin.getline(kategorija, 29);
            cin.getline(opis, 99);
            cin >> cena;
            Oglas o(naslov, kategorija, opis, cena);
            try {
                ogl += o;
            } catch (NegativnaVrednost e) {
                e.showMessage();
            }
        }
        cout << ogl;

    } else if (tip == 6) {
        cout << "-----Test najniskaCena -----" << endl;
        cin.get();
        cin.getline(naziv, 49);
        cin >> n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++) {
            cin.get();
            cin.getline(naslov, 49);
            cin.getline(kategorija, 29);
            cin.getline(opis, 99);
            cin >> cena;
            Oglas o(naslov, kategorija, opis, cena);
            try {
                ogl += o;
            } catch (NegativnaVrednost e) {
                e.showMessage();
            }
        }
        cout << "Oglas so najniska cena:" << endl;
        ogl.najniskaCena();

    } else if (tip == 7) {
        cout << "-----Test All -----" << endl;
        cin.get();
        cin.getline(naziv, 49);
        cin >> n;
        Oglasnik ogl(naziv);
        for (int i = 0; i < n; i++) {
            cin.get();
            cin.getline(naslov, 49);
            cin.getline(kategorija, 29);
            cin.getline(opis, 99);
            cin >> cena;
            Oglas o(naslov, kategorija, opis, cena);
            try {
                ogl += o;
            } catch (NegativnaVrednost e) {
                e.showMessage();
            }
        }
        cout << ogl;

        cin.get();
        cin.get();
        cin.getline(k, 29);
        cout << "Oglasi od kategorijata: " << k << endl;
        ogl.oglasiOdKategorija(k);

        cout << "Oglas so najniska cena:" << endl;
        ogl.najniskaCena();

    }

    return 0;
}
