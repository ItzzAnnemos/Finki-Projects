#include<iostream>
#include<cstring>

using namespace std;

class OutOfBoundException {
public:
    OutOfBoundException() {
    }

    void showMessage() {
        cout << "Brojot na pin kodovi ne moze da go nadmine dozvolenoto" << endl;
    }
};

class Karticka {
protected:
    char smetka[16];
    int pin;
    bool povekjePin;
public:
    Karticka() {
    }

    Karticka(char *smetka, int pin) {
        strcpy(this->smetka, smetka);
        this->pin = pin;
        this->povekjePin = false;
    }

    Karticka(char *smetka, int pin, bool povekjePin) {
        strcpy(this->smetka, smetka);
        this->pin = pin;
        this->povekjePin = povekjePin;
    }

    // дополниете ја класата
    virtual int tezinaProbivanje() {
        int i = 0, tmp = pin;
        while (tmp > 0) {
            tmp /= 10;
            i++;
        }
        return i;
    }

    friend ostream &operator<<(ostream &out, Karticka &x) {
        out << x.smetka << ": " << x.tezinaProbivanje() << endl;
        return out;
    }

    bool getDopolnitelenPin() {
        return povekjePin;
    }

    char *getSmetka() {
        return smetka;
    }
};

class SpecijalnaKarticka : public Karticka {
private:
    int *niza;
    int n;
    static const int p;
public:
    SpecijalnaKarticka() {
        n = 1;
    }

    SpecijalnaKarticka(char *smetka, int pin) : Karticka(smetka, pin) {
        this->n = 0;
        this->niza = new int[4];
    }

    ~SpecijalnaKarticka() {
        delete[] niza;
    }

    int tezinaProbivanje() {
        return this->Karticka::tezinaProbivanje() + n;
    }

    SpecijalnaKarticka &operator+=(int x) {
        if (n == SpecijalnaKarticka::p)
            throw OutOfBoundException();

        niza[n] = x;
        ++n;

        return *this;
    }
};

const int SpecijalnaKarticka::p = 4;


class Banka {
private:
    char naziv[30];
    Karticka *karticki[20];
    int broj;
    static int LIMIT;
public:
    Banka(char *naziv, Karticka **karticki, int broj) {
        strcpy(this->naziv, naziv);
        for (int i = 0; i < broj; i++) {
            //ako kartickata ima dopolnitelni pin kodovi
            if (karticki[i]->getDopolnitelenPin()) {
                this->karticki[i] = new SpecijalnaKarticka(*dynamic_cast<SpecijalnaKarticka *>(karticki[i]));
            } else this->karticki[i] = new Karticka(*karticki[i]);
        }
        this->broj = broj;
    }

    ~Banka() {
        for (int i = 0; i < broj; i++) delete karticki[i];
    }

    //да се дополни класата
    static void setLIMIT(int x) {
        LIMIT = x;
    }

    void pecatiKarticki() {
        cout << "Vo bankata " << naziv << " moze da se probijat kartickite:" << endl;
        for (int i = 0; i < broj; i++) {
            if (karticki[i]->tezinaProbivanje() < Banka::LIMIT)
                cout << (*karticki[i]);
        }
    }

    void dodadiDopolnitelenPin(char *smetka, int nov) {
        for (int i = 0; i < broj; i++) {
            if (strcmp(karticki[i]->getSmetka(), smetka) == 0) {
                SpecijalnaKarticka *s = dynamic_cast<SpecijalnaKarticka *>(karticki[i]);
                if (s != 0) {
                    *s += nov;
                }
            }
        }
    }
};

int Banka::LIMIT = 7;


int main() {

    Karticka **niza;
    int n, m, pin;
    char smetka[16];
    bool daliDopolnitelniPin;
    cin >> n;
    niza = new Karticka *[n];
    for (int i = 0; i < n; i++) {
        cin >> smetka;
        cin >> pin;
        cin >> daliDopolnitelniPin;
        if (!daliDopolnitelniPin)
            niza[i] = new Karticka(smetka, pin);
        else
            niza[i] = new SpecijalnaKarticka(smetka, pin);
    }

    Banka komercijalna("Komercijalna", niza, n);
    for (int i = 0; i < n; i++) delete niza[i];
    delete[] niza;
    cin >> m;
    for (int i = 0; i < m; i++) {
        cin >> smetka >> pin;
        try {
            komercijalna.dodadiDopolnitelenPin(smetka, pin);
        } catch (OutOfBoundException e) {
            e.showMessage();
        }
    }

    Banka::setLIMIT(5);

    komercijalna.pecatiKarticki();

}
