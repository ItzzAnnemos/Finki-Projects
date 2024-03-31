#include<iostream>
#include <cstring>

using namespace std;

struct Voz {
    char relacija[50];
    float km;
    int patnici;
};

struct ZeleznickaStanica {
    char grad[20];
    Voz vozovi[30];
    int broj;
};

void najkratkaRelacija(ZeleznickaStanica *zs, int n, char *grad) {
    int i, j;
    Voz tmp = {" ", 999, 0};
    for (i = 0; i < n; i++) {
        for (j = 0; j < zs[i].broj; j++) {
            if (strstr(zs[i].vozovi[j].relacija, grad) != NULL) {
                if (tmp.km >= zs[i].vozovi[j].km)
                    tmp = zs[i].vozovi[j];
            }
        }
    }
    cout << "Najkratka relacija: " << tmp.relacija << " (" << tmp.km << " km)" << endl;
}

int main() {
    char linija[50], gradd[20];
    int n, broj, patnici;
    float km;
    cin >> n; //se cita brojot na zelezlnichki stanici

    ZeleznickaStanica zStanica[100];
    for (int i = 0; i < n; i++) {
        cin >> gradd;
        cin >> broj;
        for (int j = 0; j < broj; j++) {
            cin >> linija >> km >> patnici;
            strcpy(zStanica[i].vozovi[j].relacija, linija);
            zStanica[i].vozovi[j].km = km;
            zStanica[i].vozovi[j].patnici = patnici;
        }
        strcpy(zStanica[i].grad, gradd);
        zStanica[i].broj = broj;
    }

    char grad[25];
    cin >> grad;

    najkratkaRelacija(zStanica, n, grad);
    return 0;
}
