#include <iostream>
#include <cstring>

using namespace std;

class Exception {
public:
    Exception() {
    }

    void showMessage() {
        cout << "Ne moze da se vnese dadeniot trud" << endl;
    }
};

class Trud {
private:
    char vid;
    int godina;
public:
    Trud(char vid = 'c', int godina = 2000) {
        this->vid = vid;
        this->godina = godina;
    }

    friend istream &operator>>(istream &in, Trud &x) {
        in >> x.vid >> x.godina;
        return in;
    }

    char getVid() {
        return vid;
    }

    int getGodina() {
        return godina;
    }
};

class Student {
private:
    char ime[30];
    int index;
    int godinaUpis;
    int oceni[50];
    int brOceni;
public:
    Student(char *ime = "", int index = 223125, int godinaUpis = 2000, int *oceni = nullptr, int brOceni = 0) {
        strcpy(this->ime, ime);
        this->index = index;
        this->godinaUpis = godinaUpis;
        this->brOceni = brOceni;
        for (int i = 0; i < brOceni; i++) {
            this->oceni[i] = oceni[i];
        }
    }

    virtual float rang() {
        float sum = 0.0;
        for (int i = 0; i < brOceni; i++) {
            sum += oceni[i];
        }
        return sum / brOceni;
    }

    friend ostream &operator<<(ostream &out, Student &x) {
        out << x.index << " " << x.ime << " " << x.godinaUpis << " " << x.rang() << endl;
        return out;
    }

    int getGodinaUpis() {
        return godinaUpis;
    }

    int getIndex() {
        return index;
    }
};

class PhDStudent : public Student {
private:
    Trud *niza;
    int n;
    static int conf;
    static int journal;
public:
    PhDStudent(char *ime = "", int index = 223125, int godinaUpis = 2000, int *oceni = nullptr, int brOceni = 0,
               Trud *niza = nullptr, int n = 0)
            : Student(ime, index, godinaUpis, oceni, brOceni) {
        this->n = n;
        this->niza = new Trud[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            try {
                if (niza[i].getGodina() < getGodinaUpis()) {
                    throw Exception();
                } else {
                    this->niza[j] = niza[i];
                    j++;
                }
            }
            catch (Exception &e) {
                e.showMessage();
            }
        }
        this->n = j;
    }

    float rang() {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            if ((niza[i].getVid() == 'c') || (niza[i].getVid() == 'C'))
                sum += conf;
            else if ((niza[i].getVid() == 'j') || (niza[i].getVid() == 'J'))
                sum += journal;
        }
        return Student::rang() + sum;
    }

    PhDStudent &operator+=(Trud x) {
        if (x.getGodina() <= this->getGodinaUpis()) {
            throw Exception();
        }
        Trud *tmp = new Trud[n];
        for (int i = 0; i < n; i++) {
            tmp[i] = niza[i];
        }
        delete[] niza;
        niza = new Trud[n + 1];
        for (int i = 0; i < n; i++) {
            niza[i] = tmp[i];
        }
        niza[n] = x;
        n++;

        return *this;
    }

    static void setConf(int x) {
        conf = x;
    }

    static void setJournal(int x) {
        journal = x;
    }
};

int PhDStudent::conf = 1;
int PhDStudent::journal = 3;

int main() {
    int testCase;
    cin >> testCase;

    int god, indeks, n, god_tr, m, n_tr;
    int izbor; //0 za Student, 1 za PhDStudent
    char ime[30];
    int oceni[50];
    char tip;
    Trud trud[50];

    if (testCase == 1) {
        cout << "===== Testiranje na klasite ======" << endl;
        cin >> ime;
        cin >> indeks;
        cin >> god;
        cin >> n;
        for (int j = 0; j < n; j++)
            cin >> oceni[j];
        Student s(ime, indeks, god, oceni, n);
        cout << s;

        cin >> ime;
        cin >> indeks;
        cin >> god;
        cin >> n;
        for (int j = 0; j < n; j++)
            cin >> oceni[j];
        cin >> n_tr;
        for (int j = 0; j < n_tr; j++) {
            cin >> tip;
            cin >> god_tr;
            Trud t(tip, god_tr);
            trud[j] = t;
        }
        PhDStudent phd(ime, indeks, god, oceni, n, trud, n_tr);
        cout << phd;
    }
    if (testCase == 2) {
        cout << "===== Testiranje na operatorot += ======" << endl;
        Student **niza;
        cin >> m;
        niza = new Student *[m];
        for (int i = 0; i < m; i++) {
            cin >> izbor;
            cin >> ime;
            cin >> indeks;
            cin >> god;
            cin >> n;
            for (int j = 0; j < n; j++)
                cin >> oceni[j];

            if (izbor == 0) {
                niza[i] = new Student(ime, indeks, god, oceni, n);
            } else {
                cin >> n_tr;
                for (int j = 0; j < n_tr; j++) {
                    cin >> tip;
                    cin >> god_tr;
                    Trud t(tip, god_tr);
                    trud[j] = t;
                }
                niza[i] = new PhDStudent(ime, indeks, god, oceni, n, trud, n_tr);
            }
        }
        // pecatenje na site studenti
        cout << "\nLista na site studenti:\n";
        for (int i = 0; i < m; i++)
            cout << *niza[i];

        // dodavanje nov trud za PhD student spored indeks
        Trud t;
        cin >> indeks;
        cin >> t;

        // vmetnete go kodot za dodavanje nov trud so pomos na operatorot +=
        int flag = 0;
        for (int i = 0; i < m; i++) {
            if (niza[i]->getIndex() == indeks) {
                PhDStudent *p = dynamic_cast<PhDStudent *>(niza[i]);
                if (p != 0) {
                    flag = 1;
                    try {
                        (*p) += t;
                    } catch (Exception e) {
                        e.showMessage();
                    }
                }
            }
        }
        if (flag == 0) {
            cout << "Ne postoi PhD student so indeks " << indeks << endl;
        }

        // pecatenje na site studenti
        cout << "\nLista na site studenti:\n";
        for (int i = 0; i < m; i++)
            cout << *niza[i];
    }
    if (testCase == 3) {
        cout << "===== Testiranje na operatorot += ======" << endl;
        Student **niza;
        cin >> m;
        niza = new Student *[m];
        for (int i = 0; i < m; i++) {
            cin >> izbor;
            cin >> ime;
            cin >> indeks;
            cin >> god;
            cin >> n;
            for (int j = 0; j < n; j++)
                cin >> oceni[j];

            if (izbor == 0) {
                niza[i] = new Student(ime, indeks, god, oceni, n);
            } else {
                cin >> n_tr;
                for (int j = 0; j < n_tr; j++) {
                    cin >> tip;
                    cin >> god_tr;
                    Trud t(tip, god_tr);
                    trud[j] = t;
                }
                niza[i] = new PhDStudent(ime, indeks, god, oceni, n, trud, n_tr);
            }
        }
        // pecatenje na site studenti
        cout << "\nLista na site studenti:\n";
        for (int i = 0; i < m; i++)
            cout << *niza[i];

        // dodavanje nov trud za PhD student spored indeks
        Trud t;
        cin >> indeks;
        cin >> t;

        // vmetnete go kodot za dodavanje nov trud so pomos na operatorot += od Testcase 2
        int flag = 0;
        for (int i = 0; i < m; i++) {
            if (niza[i]->getIndex() == indeks) {
                PhDStudent *p = dynamic_cast<PhDStudent *>(niza[i]);
                if (p != 0) {
                    try {
                        (*p) += t;
                    } catch (Exception e) {
                        e.showMessage();
                    }
                }
            }
        }
        if (flag == 0) {
            cout << "Ne postoi PhD student so indeks " << indeks << endl;
        }

        // pecatenje na site studenti
        cout << "\nLista na site studenti:\n";
        for (int i = 0; i < m; i++)
            cout << *niza[i];
    }
    if (testCase == 4) {
        cout << "===== Testiranje na isklucoci ======" << endl;
        cin >> ime;
        cin >> indeks;
        cin >> god;
        cin >> n;
        for (int j = 0; j < n; j++)
            cin >> oceni[j];
        cin >> n_tr;
        for (int j = 0; j < n_tr; j++) {
            cin >> tip;
            cin >> god_tr;
            Trud t(tip, god_tr);
            trud[j] = t;
        }
        PhDStudent phd(ime, indeks, god, oceni, n, trud, n_tr);
        cout << phd;
    }
    if (testCase == 5) {
        cout << "===== Testiranje na isklucoci ======" << endl;
        Student **niza;
        cin >> m;
        niza = new Student *[m];
        for (int i = 0; i < m; i++) {
            cin >> izbor;
            cin >> ime;
            cin >> indeks;
            cin >> god;
            cin >> n;
            for (int j = 0; j < n; j++)
                cin >> oceni[j];

            if (izbor == 0) {
                niza[i] = new Student(ime, indeks, god, oceni, n);
            } else {
                cin >> n_tr;
                for (int j = 0; j < n_tr; j++) {
                    cin >> tip;
                    cin >> god_tr;
                    Trud t(tip, god_tr);
                    trud[j] = t;
                }
                niza[i] = new PhDStudent(ime, indeks, god, oceni, n, trud, n_tr);
            }
        }
        // pecatenje na site studenti
        cout << "\nLista na site studenti:\n";
        for (int i = 0; i < m; i++)
            cout << *niza[i];

        // dodavanje nov trud za PhD student spored indeks
        Trud t;
        cin >> indeks;
        cin >> t;

        // vmetnete go kodot za dodavanje nov trud so pomos na operatorot += i spravete se so isklucokot
        for (int i = 0; i < m; i++) {
            if (niza[i]->getIndex() == indeks) {
                PhDStudent *p = dynamic_cast<PhDStudent *>(niza[i]);
                if (p != 0) {
                    try {
                        (*p) += t;
                    } catch (Exception e) {
                        e.showMessage();
                    }
                }
            }
        }

        // pecatenje na site studenti
        cout << "\nLista na site studenti:\n";
        for (int i = 0; i < m; i++)
            cout << *niza[i];
    }
    if (testCase == 6) {
        cout << "===== Testiranje na static clenovi ======" << endl;
        Student **niza;
        cin >> m;
        niza = new Student *[m];
        for (int i = 0; i < m; i++) {
            cin >> izbor;
            cin >> ime;
            cin >> indeks;
            cin >> god;
            cin >> n;
            for (int j = 0; j < n; j++)
                cin >> oceni[j];

            if (izbor == 0) {
                niza[i] = new Student(ime, indeks, god, oceni, n);
            } else {
                cin >> n_tr;
                for (int j = 0; j < n_tr; j++) {
                    cin >> tip;
                    cin >> god_tr;
                    Trud t(tip, god_tr);
                    trud[j] = t;
                }
                niza[i] = new PhDStudent(ime, indeks, god, oceni, n, trud, n_tr);
            }
        }
        // pecatenje na site studenti
        cout << "\nLista na site studenti:\n";
        for (int i = 0; i < m; i++)
            cout << *niza[i];

        int conf, journal;
        cin >> conf;
        cin >> journal;

        //postavete gi soodvetnite vrednosti za statickite promenlivi

        PhDStudent::setConf(conf);
        PhDStudent::setJournal(journal);

        // pecatenje na site studenti
        cout << "\nLista na site studenti:\n";
        for (int i = 0; i < m; i++)
            cout << *niza[i];
    }

    return 0;
}
