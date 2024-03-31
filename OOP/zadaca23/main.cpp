#include <iostream>
#include <cstring>

using namespace std;

enum Tip {
    Linux, Unix, Windows
};

class OperativenSistem {
private:
    char *name;
    float version;
    Tip type;
    float size;
public:
    OperativenSistem() {
        name = nullptr;
        version = 0.0;
        size = 0.0;
    }

    OperativenSistem(char *name, float version, Tip type, float size) {
        this->name = new char[strlen(name) + 1];
        strcpy(this->name, name);
        this->version = version;
        this->type = type;
        this->size = size;
    }

    OperativenSistem(const OperativenSistem &x) {
        this->name = new char[strlen(x.name) + 1];
        strcpy(this->name, x.name);
        this->version = x.version;
        this->type = x.type;
        this->size = x.size;
    }

    OperativenSistem operator=(const OperativenSistem &x) {
        if (this != &x) {
            delete[] name;
            this->name = new char[strlen(x.name) + 1];
            strcpy(this->name, x.name);
            this->version = x.version;
            this->type = x.type;
            this->size = x.size;
        }
        return *this;
    }

    ~OperativenSistem() {
        delete[] name;
    }

    void pecati() {
        cout << "Ime: " << name << " Verzija: " << version << " Tip: " << type << " Golemina:" << size << "GB" << endl;
    }

    bool ednakviSe(const OperativenSistem &os) {
        if ((strcmp(this->name, os.name) == 0) && this->version == os.version && this->type == os.type &&
            this->size == os.size) {
            return true;
        } else
            return false;
    }

    int sporediVerzija(const OperativenSistem &os) {
        if (this->version == os.version) {
            return 0;
        } else if (this->version < os.version) {
            return -1;
        } else {
            return 1;
        }
    }

    bool istaFamilija(const OperativenSistem &sporedba) {
        if ((strcmp(this->name, sporedba.name) == 0) && this->type == sporedba.type) {
            return true;
        } else
            return false;
    }
};

class Repozitorium {
private:
    char name[20];
    OperativenSistem *niza;
    int n;
public:
    Repozitorium(const char *ime) {
        strcpy(this->name, ime);
        niza = nullptr;
        n = 0;
    }

    ~Repozitorium() {
        delete[] niza;
    }

    void pecatiOperativniSistemi() {
        cout << "Repozitorium: " << name << endl;
        for (int i = 0; i < n; i++) {
            niza[i].pecati();
        }
    }

    void izbrishi(const OperativenSistem &operativenSistem) {
        int i, j = 0, flag = 0;
        for (i = 0; i < n; i++) {
            if (niza[i].ednakviSe(operativenSistem)) {
                flag = 1;
            }
        }
        if (flag) {
            OperativenSistem temp[n];
            for (i = 0; i < n; i++) {
                temp[i] = niza[i];
            }
            delete[] niza;
            niza = new OperativenSistem[n - 1];
            for (i = 0; i < n; i++) {
                if (!(temp[i].ednakviSe(operativenSistem))) {
                    niza[j] = temp[i];
                    j++;
                }
            }
            n--;
        }
    }

    void dodadi(const OperativenSistem &nov) {
        int i, flag = 0, newOs;
        for (i = 0; i < n; i++) {
            if (niza[i].istaFamilija(nov)) {
                if (niza[i].sporediVerzija(nov) == -1) {
                    flag = 1;
                    newOs = i;
                }
            }
        }
        if (flag) {
            OperativenSistem temp[n];
            for (i = 0; i < n; i++) {
                temp[i] = niza[i];
            }
            delete[] niza;
            niza = new OperativenSistem[n];
            for (i = 0; i < n; i++) {
                if (newOs != i) {
                    niza[i] = temp[i];
                } else {
                    niza[i] = nov;
                }
            }
        } else {
            OperativenSistem tmp[n];
            for (i = 0; i < n; i++) {
                tmp[i] = niza[i];
            }
            delete[] niza;
            niza = new OperativenSistem[n + 1];
            for (i = 0; i < n; i++) {
                niza[i] = tmp[i];
            }
            niza[n] = nov;
            n++;
        }
    }
};

int main() {
    char repoName[20];
    cin >> repoName;
    Repozitorium repozitorium = Repozitorium(repoName);
    int brojOperativniSistemi = 0;
    cin >> brojOperativniSistemi;
    char ime[20];
    float verzija;
    int tip;
    float golemina;
    for (int i = 0; i < brojOperativniSistemi; i++) {
        cin >> ime;
        cin >> verzija;
        cin >> tip;
        cin >> golemina;
        OperativenSistem os = OperativenSistem(ime, verzija, (Tip) tip, golemina);
        repozitorium.dodadi(os);
    }

    repozitorium.pecatiOperativniSistemi();
    cin >> ime;
    cin >> verzija;
    cin >> tip;
    cin >> golemina;
    OperativenSistem os = OperativenSistem(ime, verzija, (Tip) tip, golemina);
    cout << "=====Brishenje na operativen sistem=====" << endl;
    repozitorium.izbrishi(os);
    repozitorium.pecatiOperativniSistemi();
    return 0;
}