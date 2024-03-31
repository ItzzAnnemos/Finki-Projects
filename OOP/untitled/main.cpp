#include <iostream>

using namespace std;

struct student {
    char ime[20];
    char prezime[30];
    int indeks;
    float prosek;
} student1 = {"Nikola", "Serafimov", 223125, 8.0},
        student2 = {"Dushan", "Cimbajlevikj", 223061, 7.5};

int main() {
    int x;
    cout << "Za koj student sakate da poglednete podatoci?" << endl;
    cin >> x;
    if (x == 1) {
        cout << student1.ime << endl << student1.prezime << endl << student1.indeks << endl << student1.prosek << endl;
    } else if (x == 2) {
        cout << student2.ime << endl << student2.prezime << endl << student2.indeks << endl << student2.prosek << endl;
    } else {
        struct student student3{"Lazo", "Stoilovski", 223225, 9.25};
        cout << "Vnesi ime za student: " << endl;
        cin >> student3.ime;
        cout << "Vnesi prezime: " << endl;
        cin >> student3.prezime;
        cout << "Vnesi indeks: " << endl;
        cin >> student3.indeks;
        cout << "Vnesi prosek: " << endl;
        cin >> student3.prosek;
        cout << "Dali sakate da gi poglednete podatocite za ovoj student?" << endl;
        cin >> x;
        if (x) {
            cout << student3.ime << endl << student3.prezime << endl << student3.indeks << endl << student3.prosek
                 << endl;
        } else {
            cout << "Vo red" << endl;
        }
    }
    return 0;
}
