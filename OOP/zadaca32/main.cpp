#include <iostream>

using namespace std;

class GeometriskoTelo {
protected:
    double h;
public:
    GeometriskoTelo(double h) {
        this->h = h;
    }

    virtual void pecati() {
        cout << h;
    }

    virtual double getVolumen() = 0;

    virtual double getVisina() {
        return h;
    }
};

class Cilindar : public GeometriskoTelo {
private:
    double osnova;
    double radius;
public:
    Cilindar(double h = 0.0, double osnova = 0.0, double radius = 0.0) : GeometriskoTelo(h) {
        this->osnova = osnova;
        this->radius = radius;
    }

    void pecati() {
        cout << "Cilindar so visina ";
        GeometriskoTelo::pecati();
        cout << " i so radius na osnova: " << radius << endl;
    }

    double getVolumen() {
        return 3.14 * radius * radius * getVisina();
    }

    double getRadius() {
        return radius;
    }
};

class Konus : public GeometriskoTelo {
private:
    double osnova;
    double radius;
public:
    Konus(double h = 0.0, double osnova = 0.0, double radius = 0.0) : GeometriskoTelo(h) {
        this->osnova = osnova;
        this->radius = radius;
    }

    void pecati() {
        cout << "Konus so visina ";
        GeometriskoTelo::pecati();
        cout << " i so radius na osnova: " << radius << endl;
    }

    double getVolumen() {
        return 3.14 * radius * radius * getVisina() / 3.0;
    }

    double getRadius() {
        return radius;
    }
};

class Kvadar : public GeometriskoTelo {
private:
    double a;
    double b;
public:
    Kvadar(double a = 0.0, double b = 0.0, double h = 0.0) : GeometriskoTelo(h) {
        this->a = a;
        this->b = b;
    }

    void pecati() {
        cout << "Kvadar so visina ";
        GeometriskoTelo::pecati();
        cout << " i so strani a=" << a << " i b=" << b << " na osnova" << endl;
    }

    double getVolumen() {
        return a * b * getVisina();
    }
};

void teloSoNajgolemVolumen(GeometriskoTelo *niza[], int n) {
    double max = 0.0;
    int i, index;
    for (i = 0; i < n; i++) {
        if (niza[i]->getVolumen() > max) {
            max = niza[i]->getVolumen();
            index = i;
        }
    }
    cout << "Teloto so najgolem volumen e: " << endl;
    niza[index]->pecati();
}

double getRadius(GeometriskoTelo *g) {
    Cilindar *c = dynamic_cast <Cilindar *>(g);
    if (c != 0) {
        return c->getRadius();
    }
    Konus *k = dynamic_cast <Konus *>(g);
    if (k != 0) {
        return k->getRadius();
    }
    return -1;

}

int main() {
    GeometriskoTelo** mnozestvoTela; //динамички алоцирано поле од покажувачи кон GeomTelo
    int n;
    cin>>n; //број на тела во множеството
    mnozestvoTela = new GeometriskoTelo*[n]; //се алоцира меморија за полето од покажувачи
    for (int i = 0 ; i < n ; i++){
        int r,a,b,h,type;
        cout<<"Kakvo telo: 1-cilinder 2-konus 3-kvadar "<<endl;
        cin >> type;
        if (type==1) { //ако корисникот внесува цилиндер
            cin >> r >> h; mnozestvoTela[i]= new Cilindar(r,h); }
        if (type==2) { //ако корисникот внесува конус
            cin >> r >> h; mnozestvoTela[i]= new Konus(r,h); }
        if (type==3) { //ако корисникот внесува квадар
            cin >> a >> b >> h; mnozestvoTela[i]= new Kvadar(a,b,h); }
    }
    //барање 1
    teloSoNajgolemVolumen(mnozestvoTela,n);
    //барање 2
    int brojac=0;
    for (int i = 0 ; i < n ; i++)
        if (getRadius(mnozestvoTela[i]) == -1)
            brojac++;
    cout<<"Brojot na tela koi nemaat osnova krug e "<<brojac;
}
