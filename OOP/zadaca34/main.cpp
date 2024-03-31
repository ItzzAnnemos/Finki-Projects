#include <cstring>
#include <iostream>

using namespace std;

enum Size {
    mala, golema, familijarna
};

class Pizza {
protected:
    char ime[20];
    char sostojki[100];
    float cena;
public:
    Pizza() {
        strcpy(this->ime, "None");
        strcpy(this->sostojki, "None");
        cena = 0;
    }

    Pizza(const char *ime, const char *sostojki, float cena = 0.0) {
        strcpy(this->ime, ime);
        strcpy(this->sostojki, sostojki);
        this->cena = cena;
    }

    virtual float price() = 0;

    bool operator<(Pizza &x) {
        if (this->price() < x.price())
            return true;
        else
            return false;
    }

};

class FlatPizza : public Pizza {
private:
    Size golemina;
    int num;
public:
    FlatPizza() {
        this->golemina = mala;
    }

    FlatPizza(const char *ime, const char *sostojki, float cena) :
            Pizza(ime, sostojki, cena) {
    }

    FlatPizza(char *ime, char *sostojki, float cena = 0.0, Size golemina = static_cast<Size>(0), int num = 0) :
            Pizza(ime, sostojki, cena) {
        this->golemina = golemina;
        this->num = num;
    }


    float price() {
        if (this->golemina == mala)
            return this->cena + this->cena * 0.1;
        else if (this->golemina == golema)
            return this->cena + this->cena * 0.5;
        else
            return this->cena + this->cena * 0.3;
    }

    friend ostream &operator<<(ostream &os, FlatPizza &x) {
        if (x.num == 6) {
            if (!(strcmp(x.ime, "Capricciosa")))
                os << x.ime << ": " << "tomato sauce, cheese, ham, fresh mushrooms, orega, ";
            else if (!(strcmp(x.ime, "Veggie")))
                os << x.ime << ": " << "tomato sauce, cheese, tomatoes, peppers, onion, o, ";
            else if (!(strcmp(x.ime, "Caprese")))
                os << x.ime << ": " << "tomato sauce, cheese, mozzarella, tomatoes, pesto, ";
            else
                os << x.ime << ": " << x.sostojki << ", ";
        } else
            os << x.ime << ": " << x.sostojki << ", ";
        if (x.golemina == mala)
            os << "small";
        else if (x.golemina == golema)
            os << "big";
        else if (x.golemina == familijarna)
            os << "family";
        os << " - " << x.price() << "\n";

        return os;
    }

};

class FoldedPizza : public Pizza {
private:
    bool wf;
public:
    FoldedPizza(char *ime, char *sostojki, float cena = 0.0, bool wf = true) : Pizza(ime, sostojki, cena) {
        this->wf = wf;
    }

    float price() {
        if (wf) {
            return cena * 1.1;
        } else
            return cena * 1.3;
    }

    friend ostream &operator<<(ostream &out, FoldedPizza &x) {
        out << x.ime << ": " << x.sostojki << ",";
        if (x.wf) {
            out << " wf";
        } else {
            out << " nwf";
        }
        out << " - " << x.price() << endl;
        return out;
    }

    void setWhiteFlour(bool x) {
        this->wf = x;
    }
};

void expensivePizza(Pizza *p[], int n) {
    int index;
    float max = 0.0;
    for (int i = 0; i < n; i++) {
        if (p[i]->price() > max) {
            max = p[i]->price();
            index = i;
        }
    }

    FlatPizza *fp1 = dynamic_cast <FlatPizza *>(p[index]);
    FoldedPizza *fp2 = dynamic_cast <FoldedPizza *>(p[index]);

    if (fp1) {
        cout << *fp1;
    }
    if (fp2) {
        cout << *fp2;
    }
}

int main() {
    int test_case;
    char name[20];
    char ingredients[100];
    float inPrice;
    Size size;
    bool whiteFlour;

    cin >> test_case;
    if (test_case == 1) {
        // Test Case FlatPizza - Constructor, operator <<, price
        cin.get();
        cin.getline(name, 20);
        cin.getline(ingredients, 100);
        cin >> inPrice;
        FlatPizza fp(name, ingredients, inPrice);
        cout << fp;
    } else if (test_case == 2) {
        // Test Case FlatPizza - Constructor, operator <<, price
        cin.get();
        cin.getline(name, 20);
        cin.getline(ingredients, 100);
        cin >> inPrice;
        int s;
        cin >> s;
        FlatPizza fp(name, ingredients, inPrice, (Size) s);
        cout << fp;

    } else if (test_case == 3) {
        // Test Case FoldedPizza - Constructor, operator <<, price
        cin.get();
        cin.getline(name, 20);
        cin.getline(ingredients, 100);
        cin >> inPrice;
        FoldedPizza fp(name, ingredients, inPrice);
        cout << fp;
    } else if (test_case == 4) {
        // Test Case FoldedPizza - Constructor, operator <<, price
        cin.get();
        cin.getline(name, 20);
        cin.getline(ingredients, 100);
        cin >> inPrice;
        FoldedPizza fp(name, ingredients, inPrice);
        fp.setWhiteFlour(false);
        cout << fp;

    } else if (test_case == 5) {
        // Test Cast - operator <, price
        int s;

        cin.get();
        cin.getline(name, 20);
        cin.getline(ingredients, 100);
        cin >> inPrice;
        cin >> s;
        FlatPizza *fp1 = new FlatPizza(name, ingredients, inPrice, (Size) s);
        cout << *fp1;

        cin.get();
        cin.getline(name, 20);
        cin.getline(ingredients, 100);
        cin >> inPrice;
        cin >> s;
        FlatPizza *fp2 = new FlatPizza(name, ingredients, inPrice, (Size) s);
        cout << *fp2;

        cin.get();
        cin.getline(name, 20);
        cin.getline(ingredients, 100);
        cin >> inPrice;
        FoldedPizza *fp3 = new FoldedPizza(name, ingredients, inPrice);
        cout << *fp3;

        cin.get();
        cin.getline(name, 20);
        cin.getline(ingredients, 100);
        cin >> inPrice;
        FoldedPizza *fp4 = new FoldedPizza(name, ingredients, inPrice);
        fp4->setWhiteFlour(false);
        cout << *fp4;

        cout << "Lower price: " << endl;
        if (*fp1 < *fp2)
            cout << fp1->price() << endl;
        else cout << fp2->price() << endl;

        if (*fp1 < *fp3)
            cout << fp1->price() << endl;
        else cout << fp3->price() << endl;

        if (*fp4 < *fp2)
            cout << fp4->price() << endl;
        else cout << fp2->price() << endl;

        if (*fp3 < *fp4)
            cout << fp3->price() << endl;
        else cout << fp4->price() << endl;

    } else if (test_case == 6) {
        // Test Cast - expensivePizza
        int num_p;
        int pizza_type;

        cin >> num_p;
        Pizza **pi = new Pizza *[num_p];
        for (int j = 0; j < num_p; ++j) {

            cin >> pizza_type;
            if (pizza_type == 1) {
                cin.get();
                cin.getline(name, 20);

                cin.getline(ingredients, 100);
                cin >> inPrice;
                int s;
                cin >> s;
                FlatPizza *fp = new FlatPizza(name, ingredients, inPrice, (Size) s);
                cout << (*fp);
                pi[j] = fp;
            }
            if (pizza_type == 2) {

                cin.get();
                cin.getline(name, 20);
                cin.getline(ingredients, 100);
                cin >> inPrice;
                FoldedPizza *fp =
                        new FoldedPizza(name, ingredients, inPrice);
                if (j % 2)
                    (*fp).setWhiteFlour(false);
                cout << (*fp);
                pi[j] = fp;

            }
        }

        cout << endl;
        cout << "The most expensive pizza:\n";
        expensivePizza(pi, num_p);


    }
    return 0;
}
