#include <iostream>
#include <cstring>

using namespace std;

class Gitara {
private:
    char seriskiBroj[25];
    double cena;
    int godinaNaProizvodstvo;
    char tip[40];
public:
    Gitara() {
    }

    Gitara(char *tip, char *seriskiBroj, int godinaNaProivodstvo, double cena) {
        strcpy(this->tip, tip);
        strcpy(this->seriskiBroj, seriskiBroj);
        this->godinaNaProizvodstvo = godinaNaProivodstvo;
        this->cena = cena;
    }

    Gitara(const Gitara &x) {
        strcpy(tip, x.tip);
        strcpy(seriskiBroj, x.seriskiBroj);
        godinaNaProizvodstvo = x.godinaNaProizvodstvo;
        cena = x.cena;
    }

    ~Gitara() {
    }

    bool daliIsti(Gitara &g) {
        return strcmp(this->seriskiBroj, g.seriskiBroj) == 0;
    }

    void print() {
        cout << seriskiBroj << " " << tip << " " << cena << endl;
    }

    char *getTip() {
        return tip;
    }

    char *getSeriski() {
        return seriskiBroj;
    }

    int getGodina() {
        return godinaNaProizvodstvo;
    }

    double getNabavna() {
        return cena;
    }
};

class Magacin {
private:
    char ime[30];
    char lokacija[60];
    Gitara *gitari;
    int n;
    int godinaNaOtvaranje;
public:
    Magacin(char *ime = "ime", char *lokacija = "lokacija", int godina = 0) {
        strcpy(this->ime, ime);
        strcpy(this->lokacija, lokacija);
        this->godinaNaOtvaranje = godina;
        gitari = NULL;
        n = 0;
    }

    Magacin(const Magacin &x) {
        strcpy(ime, x.ime);
        strcpy(lokacija, x.lokacija);
        n = x.n;
        godinaNaOtvaranje = x.godinaNaOtvaranje;
        gitari = new Gitara[n];
        for (int i = 0; i < n; i++) {
            gitari[i] = x.gitari[i];
        }
    }

    Magacin operator=(const Magacin &x) {
        if (this != &x) {
            strcpy(ime, x.ime);
            strcpy(lokacija, x.lokacija);
            n = x.n;
            godinaNaOtvaranje = x.godinaNaOtvaranje;
            delete[] gitari;
            gitari = new Gitara[n];
            for (int i = 0; i < n; i++) {
                gitari[i] = x.gitari[i];
            }
        }
        return *this;
    }

    ~Magacin() {
        delete[] gitari;
    }

    double vrednost() {
        double sum = 0.0;
        for (int i = 0; i < n; i++) {
            sum += gitari[i].getNabavna();
        }
        return sum;
    }

    void dodadi(Gitara d) {
        Gitara *tmp = new Gitara[n + 1];
        for (int i = 0; i < n; i++) {
            tmp[i] = gitari[i];
        }
        tmp[n] = d;
        gitari = tmp;
        n++;
    }

    void prodadi(Gitara d) {
        int newI = 0;
        for (int i = 0; i < n; i++) {
            if (!gitari[i].daliIsti(d)) {
                newI++;
            }
        }
        Gitara *tmp = new Gitara[newI];
        int j = 0;
        for (int i = 0; i < n; i++) {
            if (!gitari[i].daliIsti(d)) {
                tmp[j] = gitari[i];
                j++;
            }
        }
        delete[] gitari;
        gitari = tmp;
        n = newI;
    }

    void pecati(bool daliNovi) {
        cout << ime << " " << lokacija << endl;
        if (daliNovi) {
            for (int i = 0; i < n; i++) {
                if (gitari[i].getGodina() > godinaNaOtvaranje) {
                    gitari[i].print();
                }
            }
        } else {
            for (int i = 0; i < n; i++) {
                gitari[i].print();
            }
        }
    }
};

int main() {
    // se testira zadacata modularno
    int testCase;
    cin >> testCase;

    int n, godina;
    float cena;
    char seriski[50], tip[50];

    if (testCase == 1) {
        cout << "===== Testiranje na klasata Gitara ======" << endl;
        cin >> tip;
        cin >> seriski;
        cin >> godina;
        cin >> cena;
        Gitara g(tip, seriski, godina, cena);
        cout << g.getTip() << endl;
        cout << g.getSeriski() << endl;
        cout << g.getGodina() << endl;
        cout << g.getNabavna() << endl;
    } else if (testCase == 2) {
        cout << "===== Testiranje na klasata Magacin so metodot print() ======" << endl;
        Magacin kb("Magacin1", "Lokacija1");
        kb.pecati(false);
    } else if (testCase == 3) {
        cout << "===== Testiranje na klasata Magacin so metodot dodadi() ======" << endl;
        Magacin kb("Magacin1", "Lokacija1", 2005);
        cin >> n;
        for (int i = 0; i < n; i++) {
            cin >> tip;
            cin >> seriski;
            cin >> godina;
            cin >> cena;
            Gitara g(tip, seriski, godina, cena);
            cout << "gitara dodadi" << endl;
            kb.dodadi(g);
        }
        kb.pecati(true);
    } else if (testCase == 4) {
        cout << "===== Testiranje na klasata Magacin so metodot prodadi() ======" << endl;
        Magacin kb("Magacin1", "Lokacija1", 2012);
        cin >> n;
        Gitara brisi;
        for (int i = 0; i < n; i++) {
            cin >> tip;
            cin >> seriski;
            cin >> godina;
            cin >> cena;

            Gitara g(tip, seriski, godina, cena);
            if (i == 2)
                brisi = g;
            cout << "gitara dodadi" << endl;
            kb.dodadi(g);
        }
        kb.pecati(false);
        kb.prodadi(brisi);
        kb.pecati(false);
    } else if (testCase == 5) {
        cout << "===== Testiranje na klasata Magacin so metodot prodadi() i pecati(true) ======" << endl;
        Magacin kb("Magacin1", "Lokacija1", 2011);
        cin >> n;
        Gitara brisi;
        for (int i = 0; i < n; i++) {
            cin >> tip;
            cin >> seriski;
            cin >> godina;
            cin >> cena;

            Gitara g(tip, seriski, godina, cena);
            if (i == 2)
                brisi = g;
            cout << "gitara dodadi" << endl;
            kb.dodadi(g);
        }
        kb.pecati(true);
        kb.prodadi(brisi);
        cout << "Po brisenje:" << endl;
        Magacin kb3;
        kb3 = kb;
        kb3.pecati(true);
    } else if (testCase == 6) {
        cout << "===== Testiranje na klasata Magacin so metodot vrednost()======" << endl;
        Magacin kb("Magacin1", "Lokacija1", 2011);
        cin >> n;
        Gitara brisi;
        for (int i = 0; i < n; i++) {
            cin >> tip;
            cin >> seriski;
            cin >> godina;
            cin >> cena;

            Gitara g(tip, seriski, godina, cena);
            if (i == 2)
                brisi = g;
            kb.dodadi(g);
        }
        cout << kb.vrednost() << endl;
        kb.prodadi(brisi);
        cout << "Po brisenje:" << endl;
        cout << kb.vrednost();
        Magacin kb3;
        kb3 = kb;
    }
    return 0;
}
