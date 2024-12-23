#include<iostream>
#include <cstring>

using namespace std;

struct Tanc {
    char ime[15];
    char zemja[15];
};

struct Tancer {
    char ime[20];
    char prezime[20];
    Tanc niza[5];
};

void tancuvanje(Tancer *t, int n, char *zemja) {
    int i, j, flag;
    char tmp[15];
    for (i = 0; i < n; i++) {
        flag = 0;
        for (j = 0; j < 5; j++) {
            if (strcmp(t[i].niza[j].zemja, zemja) == 0) {
                flag++;
                strcpy(tmp, t[i].niza[j].ime);
                break;
            }
        }
        if (flag) {
            cout << t[i].ime << " " << t[i].prezime << ", " << tmp << endl;
        }
    }
}

int main() {
    int i, j, n;
    char zemja[15];
    Tancer tanceri[5];
    cin >> n;
    for (i = 0; i < n; i++) {
        cin >> tanceri[i].ime;
        cin >> tanceri[i].prezime;
        for (j = 0; j < 3; j++) {
            cin >> tanceri[i].niza[j].ime;
            cin >> tanceri[i].niza[j].zemja;
        }
    }
    cin >> zemja;
    tancuvanje(tanceri, n, zemja);
    return 0;
}