#include <iostream>
#include <cstring>

#define MAX 50

using namespace std;

enum typeC {
    standard, loyal, vip
};

class UserExistsException {
public:
    UserExistsException() {
    }

    void showMessage() {
        cout << "The user already exists in the list!" << endl;
    }
};

class Customer {
private:
    char ime[MAX];
    char email[MAX];
    typeC tip;
    static float osnovenProcent;
    const static float dopolnitelenProcent;
    int kupeniProizvodi;
public:
    Customer() {
    }

    Customer(char *ime, char *email, typeC tip, int kupeniProizvodi) {
        strcpy(this->ime, ime);
        strcpy(this->email, email);
        this->tip = tip;
        this->kupeniProizvodi = kupeniProizvodi;
    }

    Customer(const Customer &x) {
        strcpy(this->ime, x.ime);
        strcpy(this->email, x.email);
        this->tip = x.tip;
        this->kupeniProizvodi = x.kupeniProizvodi;
    }

    Customer &operator=(const Customer &x) {
        if (this != &x) {
            strcpy(this->ime, x.ime);
            strcpy(this->email, x.email);
            this->tip = x.tip;
            this->kupeniProizvodi = x.kupeniProizvodi;
        }
        return *this;
    }

    float popust() {
        if (tip == 1) return osnovenProcent;
        if (tip == 2) return osnovenProcent + dopolnitelenProcent;
        else return 0;
    }

    friend ostream &operator<<(ostream &out, Customer &x) {
        out << x.ime << endl;
        out << x.email << endl;
        out << x.kupeniProizvodi << endl;
        if (x.tip == 0) {
            out << "standard ";
        } else if (x.tip == 1) {
            out << "loyal ";
        } else {
            out << "vip ";
        }
        out << x.popust() * 100 << endl;
        return out;
    }

    void setTip(int x) {
        this->tip = (typeC) x;
    }

    int getTip() {
        return tip;
    }

    char *getEmail() {
        return email;
    }

    int getKupeniProizvodi() {
        return kupeniProizvodi;
    }

    static void setDiscount1(float x) {
        osnovenProcent = x / 100;
    }
};

float Customer::osnovenProcent = 0.1;
const float Customer::dopolnitelenProcent = 0.2;

class FINKI_bookstore {
private:
    Customer *kupuvaci;
    int n;
public:
    FINKI_bookstore(Customer *kupuvaci = NULL, int n = 0) {
        this->n = n;
        this->kupuvaci = new Customer[n];
        for (int i = 0; i < n; i++) {
            this->kupuvaci[i] = kupuvaci[i];
        }
    }

    FINKI_bookstore(const FINKI_bookstore &x) {
        this->n = x.n;
        this->kupuvaci = new Customer[x.n];
        for (int i = 0; i < x.n; i++) {
            this->kupuvaci[i] = x.kupuvaci[i];
        }
    }

    FINKI_bookstore &operator=(const FINKI_bookstore &x) {
        if (this != &x) {
            delete[] kupuvaci;
            this->n = x.n;
            this->kupuvaci = new Customer[x.n];
            for (int i = 0; i < x.n; i++) {
                this->kupuvaci[i] = x.kupuvaci[i];
            }
        }
        return *this;
    }

    ~FINKI_bookstore() {
        delete[] kupuvaci;
    }

    void setCustomers(Customer *customers, int m) {
        this->n = m;
        this->kupuvaci = new Customer[m];
        for (int i = 0; i < m; i++) {
            this->kupuvaci[i] = customers[i];
        }
    }

    FINKI_bookstore &operator+=(Customer &x) {
        int flag = 0;
        for (int i = 0; i < n; i++) {
            if (strcmp(kupuvaci[i].getEmail(), x.getEmail()) == 0) {
                flag = 1;
                if (flag)
                    throw UserExistsException();
            }
        }
        if (!flag) {
            Customer *tmp = new Customer[n + 1];
            for (int i = 0; i < n; i++) {
                tmp[i] = kupuvaci[i];
            }
            tmp[n++] = x;
            delete[] kupuvaci;
            kupuvaci = tmp;
        }
        return *this;
    }

    FINKI_bookstore update() {
        for (int i = 0; i < n; i++) {
            if (kupuvaci[i].getTip() == 1) {
                if (kupuvaci[i].getKupeniProizvodi() > 10) {
                    kupuvaci[i].setTip(2);
                }
            }
            if (kupuvaci[i].getTip() == 0) {
                if (kupuvaci[i].getKupeniProizvodi() > 5) {
                    kupuvaci[i].setTip(1);
                }
            }
        }
        return *this;
    }

    friend ostream &operator<<(ostream &out, FINKI_bookstore &x) {
        for (int i = 0; i < x.n; i++) {
            out << x.kupuvaci[i];
        }
        return out;
    }
};

int main() {
    int testCase;
    cin >> testCase;

    char name[MAX];
    char email[MAX];
    int tC;
    int discount;
    int numProducts;


    if (testCase == 1) {
        cout << "===== Test Case - Customer Class ======" << endl;
        cin.get();
        cin.getline(name, MAX);
        cin.getline(email, MAX);
        cin >> tC;
        cin >> numProducts;
        cout << "===== CONSTRUCTOR ======" << endl;
        Customer c(name, email, (typeC) tC, numProducts);
        cout << c;

    }

    if (testCase == 2) {
        cout << "===== Test Case - Static Members ======" << endl;
        cin.get();
        cin.getline(name, MAX);
        cin.getline(email, MAX);
        cin >> tC;
        cin >> numProducts;
        cout << "===== CONSTRUCTOR ======" << endl;
        Customer c(name, email, (typeC) tC, numProducts);
        cout << c;

        c.setDiscount1(5);

        cout << c;
    }

    if (testCase == 3) {
        cout << "===== Test Case - FINKI-bookstore ======" << endl;
        FINKI_bookstore fc;
        int n;
        cin >> n;
        Customer customers[MAX];
        for (int i = 0; i < n; ++i) {
            cin.get();
            cin.getline(name, MAX);
            cin.getline(email, MAX);
            cin >> tC;
            cin >> numProducts;
            Customer c(name, email, (typeC) tC, numProducts);
            customers[i] = c;
        }

        fc.setCustomers(customers, n);

        cout << fc << endl;
    }

    if (testCase == 4) {
        cout << "===== Test Case - operator+= ======" << endl;
        FINKI_bookstore fc;
        int n;
        cin >> n;
        Customer customers[MAX];
        for (int i = 0; i < n; ++i) {
            cin.get();
            cin.getline(name, MAX);
            cin.getline(email, MAX);
            cin >> tC;
            cin >> numProducts;
            Customer c(name, email, (typeC) tC, numProducts);
            customers[i] = c;
        }

        fc.setCustomers(customers, n);
        cout << "OPERATOR +=" << endl;
        cin.get();
        cin.getline(name, MAX);
        cin.getline(email, MAX);
        cin >> tC;
        cin >> numProducts;
        Customer c(name, email, (typeC) tC, numProducts);
        fc += c;

        cout << fc;
    }

    if (testCase == 5) {
        cout << "===== Test Case - operator+= (exception) ======" << endl;
        FINKI_bookstore fc;
        int n;
        cin >> n;
        Customer customers[MAX];
        for (int i = 0; i < n; ++i) {
            cin.get();
            cin.getline(name, MAX);
            cin.getline(email, MAX);
            cin >> tC;
            cin >> numProducts;
            Customer c(name, email, (typeC) tC, numProducts);
            customers[i] = c;
        }

        fc.setCustomers(customers, n);
        cout << "OPERATOR +=" << endl;
        cin.get();
        cin.getline(name, MAX);
        cin.getline(email, MAX);
        cin >> tC;
        cin >> numProducts;
        Customer c(name, email, (typeC) tC, numProducts);

        try {
            fc += c;
        }
        catch (UserExistsException e) {
            e.showMessage();
        }
        cout << fc;
    }

    if (testCase == 6) {
        cout << "===== Test Case - update method  ======" << endl << endl;
        FINKI_bookstore fc;
        int n;
        cin >> n;
        Customer customers[MAX];
        for (int i = 0; i < n; ++i) {
            cin.get();
            cin.getline(name, MAX);
            cin.getline(email, MAX);
            cin >> tC;
            cin >> numProducts;
            Customer c(name, email, (typeC) tC, numProducts);
            customers[i] = c;
        }

        fc.setCustomers(customers, n);

        cout << "Update:" << endl;
        fc.update();
        cout << fc;
    }
    return 0;

}
