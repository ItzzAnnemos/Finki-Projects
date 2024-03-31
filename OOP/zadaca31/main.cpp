#include <iostream>
#include <cstring>

using namespace std;

class HotelskaRezervacija {
protected:
    int denovi;
    int lica;
    char ime[50];
    char prezime[50];
public:
    HotelskaRezervacija(char *ime = "", char *prezime = "", int denovi = 0, int lica = 0) {
        strcpy(this->ime, ime);
        strcpy(this->prezime, prezime);
        this->denovi = denovi;
        this->lica = lica;
    }

    virtual int vratiCena() {
        return (lica * denovi + 25);
    }

    virtual int vratiCena(int uplata) {
        if (uplata >= vratiCena()) {
            return uplata - vratiCena();
        } else {
            cout << "Za vashata rezervacija treba da platite: " << vratiCena() << endl;
            return -1;
        }
    }
};

class PolupansionskaHotelskaRezervacija : public HotelskaRezervacija {
public:
    PolupansionskaHotelskaRezervacija(char *ime = "", char *prezime = "", int denovi = 0,
                                      int lica = 0) : HotelskaRezervacija(ime, prezime, denovi, lica) {
    }

    int vratiCena(int uplata) {
        int cena = HotelskaRezervacija::vratiCena() + lica*5;
        if (uplata >= cena) {
            return uplata - cena;
        } else {
            cout << "Za vashata rezervacija treba da platite: " << cena << endl;
            return -1;
        }
    }
};

class Hotel{
private:
    char ime[50];
    int saldo;
public:
    Hotel(char * ime="", int saldo = 0) {
        strcpy(this->ime,ime);
        this->saldo = saldo;
    }

    int uplatiZaRezervacija(HotelskaRezervacija &hr, int uplata) {
        int kusur = hr.vratiCena(uplata);
        if (kusur != -1) {
            saldo += uplata;
        }
        return kusur;
    }
};

int main() {
    Hotel h("Bristol");
    HotelskaRezervacija *hr1=new HotelskaRezervacija("Petko","Petkovski",5,5);
    int cena = h.uplatiZaRezervacija(*hr1,1000);
    if (cena!=-1)
        cout<<"Kusur : "<<cena<<endl;
    PolupansionskaHotelskaRezervacija *hr2=
            new PolupansionskaHotelskaRezervacija("Risto","Ristovski",5,5);
    cena=h.uplatiZaRezervacija(*hr2,1000);
    if (cena!=-1)
        cout<<"Kusur : "<<cena<<endl;
    //покажувач кон основна класа покажува кон објект од изведена
    HotelskaRezervacija *hr3=new PolupansionskaHotelskaRezervacija("Ana","Anovska",4,2);
    cena=h.uplatiZaRezervacija(*hr3,100);
    if (cena!=-1)
        cout<<"Kusur : "<<cena<<endl;
    PolupansionskaHotelskaRezervacija hr4("Tome","Tomovski",5,3);
    cena=h.uplatiZaRezervacija(hr4,1000);
    if (cena!=-1)
        cout<<"Kusur : "<<cena<<endl;
    return 0;
}
