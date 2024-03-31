#include <iostream>
#include <cmath>
using namespace std;

class Triagolnik {
private:
    int a;
    int b;
    int c;
public:
    Triagolnik(int a, int b, int c) {
        this->a = a;
        this->b = b;
        this->c = c;
    }
    ~Triagolnik(){
    }

    int perimetar() {
        return a + b + c;
    }

    float plostina() {
        float s = (a + b + c) / 2;
        return sqrt(s * (s - a) * (s - b) * (s - c));
    }

};

int main() {
    int a,b,c;
    cin >> a >> b >> c;
    Triagolnik t(a,b,c);

    cout << "Perimetarot e: " << t.perimetar() << endl;
    cout << "Plostinata e: " << t.plostina() << endl;

    return 0;
}
