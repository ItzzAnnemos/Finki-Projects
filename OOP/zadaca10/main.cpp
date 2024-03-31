#include <iostream>
using namespace std;

struct Igrac {
    char ime[50];
    int nivo;
    int poeni;
};

struct KompjuterskaIgra {
    char ime[20];
    struct Igrac igraci[30];
    int broj;
};

void najdobarIgrac(KompjuterskaIgra *lista ,int n) {
    int i;
    struct KompjuterskaIgra temp = lista[0];
    for (i = 1;i < n;i++) {
        if (temp.broj < lista[i].broj) {
            temp = lista[i];
        }
    }
    int m = temp.broj;
    struct Igrac tmp = temp.igraci[0];
    for (i = 1;i < m;i++) {
        if (tmp.poeni < temp.igraci[i].poeni) {
            tmp = temp.igraci[i];
        }
    }

    cout << "Najdobar igrac e igracot so korisnicko ime " << tmp.ime << " koj ja igra igrata " << temp.ime << endl;
}

int main() {
    int n,i,j,m;
    cin >> n;
    struct KompjuterskaIgra igri[n];

    for (i = 0;i < n;i++) {
        cin >> igri[i].ime;
        cin >> igri[i].broj;
        m = igri[i].broj;
        for (j = 0;j < m;j++) {
            cin >> igri[i].igraci[j].ime;
            cin >> igri[i].igraci[j].nivo;
            cin >> igri[i].igraci[j].poeni;
        }
    }

    najdobarIgrac(igri,n);

    return 0;
}
