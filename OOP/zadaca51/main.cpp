#include<iostream>
#include<cstring>

using namespace std;

class BadInputException {
public:
    BadInputException() {
    }

    void showMessage() {
        cout << "Greshna opisna ocenka" << endl;
    }
};

class StudentKurs {
protected:
    char ime[30];
    int ocenka;
    bool daliUsno;
    static int MAX;
    const static int MINOCENKA;
public:
    StudentKurs(char *ime, int finalenIspit) {
        strcpy(this->ime, ime);
        this->ocenka = finalenIspit;
        this->daliUsno = false;
    }

    //дополни ја класата
    static void setMAX(int x) {
        MAX = x;
    }

    bool getDaliUsno() {
        return daliUsno;
    }

    virtual void setDaliUsno(bool x) {
        daliUsno = x;
    }

    virtual int ocena() {
        return ocenka;
    }

    char *getIme() {
        return ime;
    }

    friend ostream &operator<<(ostream &out, StudentKurs &x) {
        out << x.ime << " --- " << x.ocena() << endl;
        return out;
    }
};

class StudentKursUsno : public StudentKurs {
private:
    char *opis;
    char *usnaOcena;
public:
    StudentKursUsno(char *ime, int finalenIspit) : StudentKurs(ime, finalenIspit) {
        setDaliUsno(true);
    }

    ~StudentKursUsno() {
        delete[] usnaOcena;
    }

    int ocena() {
        int tmp = StudentKurs::ocena();
        if (strcmp(usnaOcena, "odlicen") == 0) {
            return tmp + 2;
        } else if (strcmp(usnaOcena, "dobro") == 0) {
            return tmp + 1;
        } else if (strcmp(usnaOcena, "losho") == 0) {
            return tmp - 1;
        } else
            return tmp;
    }

    friend ostream &operator<<(ostream &out, StudentKursUsno &x) {
        out << x.ime << " --- " << x.ocena() << endl;
        return out;
    }

    StudentKursUsno &operator+=(char *o) {
        this->usnaOcena = new char[strlen(o) + 1];
        char *q = new char[strlen(o)];
        int n = strlen(o) + 1;
        bool t = false;
        for (int i = 0, j = 0; i < n - 1; i++) {
            if (o[i] >= 97 && o[i] <= 122)
                q[j++] = o[i];
            else {
                throw BadInputException();

            }
        }
        strcpy(this->usnaOcena, q);

        return *this;
    }
};

int StudentKurs::MAX = 10;
const int StudentKurs::MINOCENKA = 6;

class KursFakultet {
private:
    char naziv[30];
    StudentKurs *studenti[20];
    int broj;

public:
    KursFakultet(char *naziv, StudentKurs **studenti, int broj) {
        strcpy(this->naziv, naziv);
        for (int i = 0; i < broj; i++) {
            //ako studentot ima usno isprashuvanje
            if (studenti[i]->getDaliUsno()) {
                this->studenti[i] = new StudentKursUsno(*dynamic_cast<StudentKursUsno *>(studenti[i]));
            } else this->studenti[i] = new StudentKurs(*studenti[i]);
        }
        this->broj = broj;
    }

    /*~KursFakultet() {
        for (int i = 0; i < broj; i++) delete studenti[i];
    }*/

    void pecatiStudenti() {
        cout << "Kursot " << naziv << " go polozile:" << endl;
        for (int i = 0; i < broj; i++) {
            if (studenti[i]->ocena() >= 6)
                cout << *studenti[i];
        }
    }

    KursFakultet postaviOpisnaOcenka(char *ime, char *opisnaOcenka) {
        for (int i = 0; i < broj; i++) {
            if (strcmp(studenti[i]->getIme(), ime) == 0) {
                StudentKursUsno *s = dynamic_cast <StudentKursUsno *>(studenti[i]);
                if (s != 0) {
                    try {
                        *s += (opisnaOcenka);
                    } catch (BadInputException e) {
                        e.showMessage();
                    }
                }
            }
        }
        return *this;
    }
};

int main() {

    StudentKurs **niza;
    int n, m, ocenka;
    char ime[30], opisna[10];
    bool daliUsno;
    cin >> n;
    niza = new StudentKurs *[n];
    for (int i = 0; i < n; i++) {
        cin >> ime;
        cin >> ocenka;
        cin >> daliUsno;
        if (!daliUsno)
            niza[i] = new StudentKurs(ime, ocenka);
        else
            niza[i] = new StudentKursUsno(ime, ocenka);
    }

    KursFakultet programiranje("OOP", niza, n);
    for (int i = 0; i < n; i++) delete niza[i];
    delete[] niza;
    cin >> m;

    for (int i = 0; i < m; i++) {
        cin >> ime >> opisna;
        programiranje.postaviOpisnaOcenka(ime, opisna);
    }

    StudentKurs::setMAX(9);

    programiranje.pecatiStudenti();

}
