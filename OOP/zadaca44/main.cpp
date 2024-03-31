#include <iostream>
#include <cstring>

using namespace std;

class Koncert {
protected:
    char naziv[20];
    char lokacija[20];
    static float popust;
    float cenaBilet;
public:
    Koncert(char *naziv = "", char *lokacija = "", float cenaBilet = 0.0) {
        strcpy(this->naziv, naziv);
        strcpy(this->lokacija, lokacija);
        this->cenaBilet = cenaBilet;
    }

    virtual float cena() {
        return cenaBilet * (1 - popust);
    }

    static void setSezonskiPopust(float x) {
        popust = x;
    }

    static float getSezonskiPopust() {
        return popust;
    }

    char *getNaziv() {
        return naziv;
    }
};

float Koncert::popust = 0.2;

class ElektronskiKoncert : public Koncert {
private:
    char *dj;
    float vremetraenje;
    bool zabava;
public:
    ElektronskiKoncert(char *naziv = "", char *lokacija = "", float cenaBilet = 0.0, char *dj = "",
                       float vremetraenje = 0.0, bool zabava = true) : Koncert(naziv, lokacija, cenaBilet) {
        this->dj = new char[strlen(dj) + 1];
        strcpy(this->dj, dj);
        this->vremetraenje = vremetraenje;
        this->zabava = zabava;
    }

    ~ElektronskiKoncert() {
        delete[] dj;
    }

    float cena() {
        float temp = Koncert::cena();

        if (vremetraenje > 7) {
            temp += 360;
        } else if (vremetraenje > 5) {
            temp += 150;
        }

        if (zabava) {
            temp -= 50;
        } else {
            temp += 100;
        }

        return temp;
    }
};

void najskapKoncert(Koncert **koncerti, int n) {
    int index, count = 0;
    float max = 0;
    for (int i = 0; i < n; i++) {
        if (koncerti[i]->cena() > max) {
            max = koncerti[i]->cena();
            index = i;
        }

        ElektronskiKoncert *e = dynamic_cast <ElektronskiKoncert *>(koncerti[i]);
        if (e != 0)
            count++;
    }

    cout << "Najskap koncert: " << koncerti[index]->getNaziv() << " " << max << endl;
    cout << "Elektronski koncerti: " << count << " od vkupno " << n << endl;
}

bool prebarajKoncert(Koncert **koncerti, int n, char *naziv, bool elektronski) {
    if (elektronski) {
        for (int i = 0; i < n; i++) {
            ElektronskiKoncert *e = dynamic_cast <ElektronskiKoncert *>(koncerti[i]);
            if (e != 0) {
                if (strcmp(koncerti[i]->getNaziv(), naziv) == 0) {
                    cout << koncerti[i]->getNaziv() << " " << koncerti[i]->cena() << endl;
                    return true;
                }
            }
        }
    } else {
        for (int i = 0; i < n; i++) {
            if (strcmp(koncerti[i]->getNaziv(), naziv) == 0) {
                cout << koncerti[i]->getNaziv() << " " << koncerti[i]->cena() << endl;
                return true;
            }
        }
    }

    return false;
}

int main() {

    int tip, n, novaCena;
    char naziv[100], lokacija[100], imeDJ[40];
    bool dnevna;
    float cenaBilet, novPopust;
    float casovi;

    cin >> tip;
    if (tip == 1) {//Koncert
        cin >> naziv >> lokacija >> cenaBilet;
        Koncert k1(naziv, lokacija, cenaBilet);
        cout << "Kreiran e koncert so naziv: " << k1.getNaziv() << endl;
    } else if (tip == 2) {//cena - Koncert
        cin >> naziv >> lokacija >> cenaBilet;
        Koncert k1(naziv, lokacija, cenaBilet);
        cout << "Osnovna cena na koncertot so naziv " << k1.getNaziv() << " e: " << k1.cena() << endl;
    } else if (tip == 3) {//ElektronskiKoncert
        cin >> naziv >> lokacija >> cenaBilet >> imeDJ >> casovi >> dnevna;
        ElektronskiKoncert s(naziv, lokacija, cenaBilet, imeDJ, casovi, dnevna);
        cout << "Kreiran e elektronski koncert so naziv " << s.getNaziv() << " i sezonskiPopust "
             << s.getSezonskiPopust() << endl;
    } else if (tip == 4) {//cena - ElektronskiKoncert
        cin >> naziv >> lokacija >> cenaBilet >> imeDJ >> casovi >> dnevna;
        ElektronskiKoncert s(naziv, lokacija, cenaBilet, imeDJ, casovi, dnevna);
        cout << "Cenata na elektronskiot koncert so naziv " << s.getNaziv() << " e: " << s.cena() << endl;
    } else if (tip == 5) {//najskapKoncert

    } else if (tip == 6) {//prebarajKoncert
        Koncert **koncerti = new Koncert *[5];
        int n;
        koncerti[0] = new Koncert("Area", "BorisTrajkovski", 350);
        koncerti[1] = new ElektronskiKoncert("TomorrowLand", "Belgium", 8000, "Afrojack", 7.5, false);
        koncerti[2] = new ElektronskiKoncert("SeaDance", "Budva", 9100, "Tiesto", 5, true);
        koncerti[3] = new Koncert("Superhiks", "PlatoUkim", 100);
        koncerti[4] = new ElektronskiKoncert("CavoParadiso", "Mykonos", 8800, "Guetta", 3, true);
        char naziv[100];
        najskapKoncert(koncerti, 5);
    } else if (tip == 7) {//prebaraj
        Koncert **koncerti = new Koncert *[5];
        int n;
        koncerti[0] = new Koncert("Area", "BorisTrajkovski", 350);
        koncerti[1] = new ElektronskiKoncert("TomorrowLand", "Belgium", 8000, "Afrojack", 7.5, false);
        koncerti[2] = new ElektronskiKoncert("SeaDance", "Budva", 9100, "Tiesto", 5, true);
        koncerti[3] = new Koncert("Superhiks", "PlatoUkim", 100);
        koncerti[4] = new ElektronskiKoncert("CavoParadiso", "Mykonos", 8800, "Guetta", 3, true);
        char naziv[100];
        bool elektronski;
        cin >> elektronski;
        if (prebarajKoncert(koncerti, 5, "Area", elektronski))
            cout << "Pronajden" << endl;
        else cout << "Ne e pronajden" << endl;

        if (prebarajKoncert(koncerti, 5, "Area", !elektronski))
            cout << "Pronajden" << endl;
        else cout << "Ne e pronajden" << endl;

    } else if (tip == 8) {//smeni cena
        Koncert **koncerti = new Koncert *[5];
        int n;
        koncerti[0] = new Koncert("Area", "BorisTrajkovski", 350);
        koncerti[1] = new ElektronskiKoncert("TomorrowLand", "Belgium", 8000, "Afrojack", 7.5, false);
        koncerti[2] = new ElektronskiKoncert("SeaDance", "Budva", 9100, "Tiesto", 5, true);
        koncerti[3] = new Koncert("Superhiks", "PlatoUkim", 100);
        koncerti[2]->setSezonskiPopust(0.9);
        najskapKoncert(koncerti, 4);
    }

    return 0;
}
