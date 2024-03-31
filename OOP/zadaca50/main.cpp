#include <iostream>
#include <cstring>

using namespace std;

class SMS {
protected:
    float cena;
    char pretBroj[20];
    static const float procent;
public:
    SMS(char *pretBroj = "", float cena = 0.0) {
        this->cena = cena;
        strcpy(this->pretBroj, pretBroj);
    }

    virtual float SMS_cena() = 0;

    friend ostream &operator<<(ostream &out, SMS &x) {
        out << "Tel: " << x.pretBroj << " - cena: " << x.SMS_cena() << "den." << endl;
        return out;
    }
};

const float SMS::procent = 0.18;

class RegularSMS : public SMS {
private:
    char msg[1000];
    bool roaming;
    static int rProcent;
public:
    RegularSMS(char *pretBroj = "", float cena = 0.0, char *msg = "", bool roaming = true) : SMS(pretBroj, cena) {
        strcpy(this->msg, msg);
        this->roaming = roaming;
    }

    int calc() {
        int counter = 0;
        for (int copy = strlen(msg); copy > 0; copy = copy - 160)
            counter++;
        return counter;
    }

    float SMS_cena() {
        float sum = cena;
        if (roaming) {
            sum = sum + sum * (rProcent / 100.0);
        } else {
            sum = sum + sum * procent;
        }
        return sum * calc();
    }

    static void set_rProcent(int x) {
        rProcent = x;
    }
};

int RegularSMS::rProcent = 300;

class SpecialSMS : public SMS {
private:
    bool humanitarna;
    static int sProcent;
public:
    SpecialSMS(char *pretBroj = "", float cena = 0.0, bool humanitarna = true) : SMS(pretBroj, cena) {
        this->humanitarna = humanitarna;
    }

    float SMS_cena() {
        float sum = cena;
        if (humanitarna) {
            return sum;
        } else {
            return sum + sum * (sProcent / 100.0);
        }
    }

    static void set_sProcent(int x) {
        sProcent = x;
    }
};

int SpecialSMS::sProcent = 150;

void vkupno_SMS(SMS **poraka, int n) {
    int reg = 0, spec = 0;
    float sumReg = 0, sumSpec = 0;
    for (int i = 0; i < n; i++) {
        RegularSMS *r = dynamic_cast <RegularSMS *>(poraka[i]);
        SpecialSMS *s = dynamic_cast <SpecialSMS *>(poraka[i]);
        if (r != 0) {
            reg++;
            sumReg += r->SMS_cena();
        }
        if (s != 0) {
            spec++;
            sumSpec += s->SMS_cena();
        }
    }
    cout << "Vkupno ima " << reg << " regularni SMS poraki i nivnata cena e: " << sumReg << endl;
    cout << "Vkupno ima " << spec << " specijalni SMS poraki i nivnata cena e: " << sumSpec << endl;
}

int main() {

    char tel[20], msg[1000];
    float cena;
    float price;
    int p;
    bool roam, hum;
    SMS **sms;
    int n;
    int tip;

    int testCase;
    cin >> testCase;

    if (testCase == 1) {
        cout << "====== Testing RegularSMS class ======" << endl;
        cin >> n;
        sms = new SMS *[n];

        for (int i = 0; i < n; i++) {
            cin >> tel;
            cin >> cena;
            cin.get();
            cin.getline(msg, 1000);
            cin >> roam;
            cout << "CONSTRUCTOR" << endl;
            sms[i] = new RegularSMS(tel, cena, msg, roam);
            cout << "OPERATOR <<" << endl;
            cout << *sms[i];
        }
        for (int i = 0; i < n; i++) delete sms[i];
        delete[] sms;
    }
    if (testCase == 2) {
        cout << "====== Testing SpecialSMS class ======" << endl;
        cin >> n;
        sms = new SMS *[n];

        for (int i = 0; i < n; i++) {
            cin >> tel;
            cin >> cena;
            cin >> hum;
            cout << "CONSTRUCTOR" << endl;
            sms[i] = new SpecialSMS(tel, cena, hum);
            cout << "OPERATOR <<" << endl;
            cout << *sms[i];
        }
        for (int i = 0; i < n; i++) delete sms[i];
        delete[] sms;
    }
    if (testCase == 3) {
        cout << "====== Testing method vkupno_SMS() ======" << endl;
        cin >> n;
        sms = new SMS *[n];

        for (int i = 0; i < n; i++) {

            cin >> tip;
            cin >> tel;
            cin >> cena;
            if (tip == 1) {

                cin.get();
                cin.getline(msg, 1000);
                cin >> roam;

                sms[i] = new RegularSMS(tel, cena, msg, roam);

            } else {
                cin >> hum;

                sms[i] = new SpecialSMS(tel, cena, hum);
            }
        }

        vkupno_SMS(sms, n);
        for (int i = 0; i < n; i++) delete sms[i];
        delete[] sms;
    }
    if (testCase == 4) {
        cout << "====== Testing RegularSMS class with a changed percentage======" << endl;
        SMS *sms1, *sms2;
        cin >> tel;
        cin >> cena;
        cin.get();
        cin.getline(msg, 1000);
        cin >> roam;
        sms1 = new RegularSMS(tel, cena, msg, roam);
        cout << *sms1;

        cin >> tel;
        cin >> cena;
        cin.get();
        cin.getline(msg, 1000);
        cin >> roam;
        cin >> p;
        RegularSMS::set_rProcent(p);
        sms2 = new RegularSMS(tel, cena, msg, roam);
        cout << *sms2;

        delete sms1, sms2;
    }
    if (testCase == 5) {
        cout << "====== Testing SpecialSMS class with a changed percentage======" << endl;
        SMS *sms1, *sms2;
        cin >> tel;
        cin >> cena;
        cin >> hum;
        sms1 = new SpecialSMS(tel, cena, hum);
        cout << *sms1;

        cin >> tel;
        cin >> cena;
        cin >> hum;
        cin >> p;
        SpecialSMS::set_sProcent(p);
        sms2 = new SpecialSMS(tel, cena, hum);
        cout << *sms2;

        delete sms1, sms2;
    }

    return 0;
}

