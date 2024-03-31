#include <iostream>
#include <cstring>

using namespace std;

class Transport {
protected:
    char destinacija[20];
    int cena;
    int rastojanie;
public:
    Transport(char *destinacija = "", int cena = 0, int rastojanie = 0) {
        strcpy(this->destinacija, destinacija);
        this->cena = cena;
        this->rastojanie = rastojanie;
    }

    Transport(const Transport &x) {
        strcpy(this->destinacija, x.destinacija);
        this->cena = x.cena;
        this->rastojanie = x.rastojanie;
    }

    int getRastojanie() {
        return rastojanie;
    }

    int getCena() {
        return cena;
    }

    virtual int cenaTransport() {
        return cena;
    }

    virtual bool operator<(Transport &x) {
        return rastojanie < x.rastojanie;
    }

    virtual void print() {
        cout << destinacija << " " << rastojanie << " " << cenaTransport() << endl;
    }
};

class AvtomobilTransport : public Transport {
private:
    bool shofer;
public:
    AvtomobilTransport(char *destinacija = "", int cena = 0, int rastojanie = 0, bool shofer = false)
            : Transport(destinacija, cena, rastojanie) {
        this->shofer = shofer;
    }

    AvtomobilTransport(const AvtomobilTransport &x)
            : Transport(x) {
        this->shofer = x.shofer;
    }

    int cenaTransport() {
        if (shofer) {
            return cena * 1.2;
        } else {
            return cena;
        }
    }

    bool operator<(Transport &x) {
        return rastojanie < x.getRastojanie();
    }

    void print() {
        cout << destinacija << " " << rastojanie << " " << cenaTransport() << endl;
    }
};

class KombeTransport : public Transport {
private:
    int lugje;
public:
    KombeTransport(char *destinacija = "", int cena = 0, int rastojanie = 0, int lugje = 0)
            : Transport(destinacija, cena, rastojanie) {
        this->lugje = lugje;
    }

    KombeTransport(const KombeTransport &x)
            : Transport(x) {
        this->lugje = x.lugje;
    }

    int cenaTransport() {
        return cena - lugje * 200;
    }

    bool operator<(Transport &x) {
        return rastojanie < x.getRastojanie();
    }

    void print() {
        cout << destinacija << " " << rastojanie << " " << cenaTransport() << endl;
    }
};

void bubbleSort(Transport **ponudi, int n) {
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (ponudi[j]->getCena() > ponudi[j + 1]->getCena()) {
                Transport *tmp = ponudi[j];
                ponudi[j] = ponudi[j + 1];
                ponudi[j + 1] = tmp;
            }
        }
    }
}

void pecatiPoloshiPonudi(Transport *ponudi[], int n, AvtomobilTransport &T) {
    Transport **tmp = new Transport *[n];
    int j = 0;
    for (int i = 0; i < n; i++) {
        if (ponudi[i]->getCena() > T.getCena()) {
            AvtomobilTransport *a = dynamic_cast<AvtomobilTransport *>(ponudi[i]);
            KombeTransport *k = dynamic_cast<KombeTransport *>(ponudi[i]);
            if (a != nullptr) {
                tmp[j] = new AvtomobilTransport(*a);
                j++;
            }
            if (k != nullptr) {
                tmp[j] = new KombeTransport(*k);
                j++;
            }
        }
    }
    bubbleSort(tmp, j);
    for (int i = 0; i < j; i++) {
        tmp[i]->print();
    }
    delete[] tmp;
}

int main() {
    char destinacija[20];
    int tip, cena, rastojanie, lugje;
    bool shofer;
    int n;
    cin >> n;
    Transport **ponudi = new Transport *[n];

    for (int i = 0; i < n; i++) {
        cin >> tip >> destinacija >> cena >> rastojanie;
        if (tip == 1) {
            cin >> shofer;
            ponudi[i] = new AvtomobilTransport(destinacija, cena, rastojanie, shofer);
        } else {
            cin >> lugje;
            ponudi[i] = new KombeTransport(destinacija, cena, rastojanie, lugje);
        }
    }

    AvtomobilTransport nov("Ohrid", 2000, 600, false);
    pecatiPoloshiPonudi(ponudi, n, nov);

    for (int i = 0; i < n; i++) {
        delete ponudi[i];
    }
    delete[] ponudi;
    return 0;
}
