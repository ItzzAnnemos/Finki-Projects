#include <iostream>

using namespace std;

//5
//4.5
//4
//10
//17.5
//8.9
//1 2
//1.5 2.5
//3 4.5
//7 7
//8 9
//2

class Kvadrat {
protected:
    double a;
public:
    Kvadrat() {
    }

    Kvadrat(double a) {
        this->a = a;
    }

    Kvadrat(const Kvadrat &x) {
        this->a = x.a;
    }

    double perimetar() {
        return 4 * a;
    }

    double plostina() {
        return a * a;
    }

    void pecati() {
        cout << "Kvadrat so dolzina a=" << a << " ima plostina P=" << plostina() << " i perimetar L=" << perimetar()
             << endl;
    }
};

class Pravoagolnik : public Kvadrat {
private:
    double x;
    double y;
public:
    Pravoagolnik() {
    }

    Pravoagolnik(const Kvadrat &k, double x, double y) : Kvadrat(k) {
        this->x = a + x;
        this->y = a + y;
        if (x == y) {
            a += x;
        }
    }

    Pravoagolnik(const Pravoagolnik &p) {
        this->x = p.x;
        this->y = p.y;
    }

    double perimetar() {
        if (x == y) {
            Kvadrat::perimetar();
        } else
            return 2 * (x + y);
    }

    double plostina() {
        if (x == y) {
            Kvadrat::plostina();
        } else
            return x * y;
    }

    void pecati() {
        if (x == y) {
            Kvadrat::pecati();
        } else {
            cout << "Pravoagolnik so strani: " << x << " i " << y << " ima plostina P=" << plostina()
                 << " i perimetar L="
                 << perimetar() << endl;
        }
    }
};

int main() {
    int n;
    double a, x, y;
    Kvadrat *kvadrati;
    Pravoagolnik *pravoagolnici;

    cin >> n;

    kvadrati = new Kvadrat[n];
    pravoagolnici = new Pravoagolnik[n];

    for (int i = 0; i < n; i++) {
        cin >> a;

        kvadrati[i] = Kvadrat(a);
    }

    for (int i = 0; i < n; i++) {
        cin >> x >> y;

        pravoagolnici[i] = Pravoagolnik(kvadrati[i], x, y);
    }

    int testCase;
    cin >> testCase;

    if (testCase == 1) {
        cout << "===Testiranje na klasata Kvadrat===" << endl;
        for (int i = 0; i < n; i++)
            kvadrati[i].pecati();
    } else {
        cout << "===Testiranje na klasata Pravoagolnik===" << endl;
        for (int i = 0; i < n; i++)
            pravoagolnici[i].pecati();
    }
    return 0;
}