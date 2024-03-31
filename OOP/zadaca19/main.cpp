#include <iostream>
#include <cstring>
using namespace std;

class DisciplinaryAction{
private:
    char * ime;
    int indeks;
    char * pricina;
public:
    DisciplinaryAction(){
        ime = nullptr;
        pricina = nullptr;
        indeks = 0;
    }
    DisciplinaryAction(char * ime, int indeks, char * pricina) {
        this->ime = new char [strlen(ime) + 1];
        this->pricina = new char [strlen(pricina) + 1];
        strcpy(this->ime,ime);
        this->indeks = indeks;
        strcpy(this->pricina,pricina);
    }

    DisciplinaryAction(const DisciplinaryAction & x) {
        ime = new char [strlen(x.ime) + 1];
        pricina = new char [strlen(x.pricina) + 1];
        strcpy(ime,x.ime);
        strcpy(pricina,x.pricina);
        indeks = x.indeks;
    }

    DisciplinaryAction operator = (const DisciplinaryAction & x) {
        if (this != &x) {
            delete[] ime;
            delete [] pricina;
            ime = new char [strlen(x.ime) + 1];
            pricina = new char [strlen(x.pricina) + 1];
            strcpy(ime,x.ime);
            strcpy(pricina,x.pricina);
            indeks = x.indeks;
        }
        return * this;
    }

    ~DisciplinaryAction() {
        delete [] ime;
        delete [] pricina;
    }

    void setIndex(int x) {
        indeks = x;
    }

    int getIndex() {
        return indeks;
    }

    void print() {
        cout << "Student: " << ime << endl;
        cout << "Student's index: " << indeks << endl;
        cout << "Reason: " << pricina << endl;
    }
};

int main() {
    int n;
    cin >> n;

    /// Testing Default constructor and equal operator
    /// Array input

    DisciplinaryAction arr[n];

    for(int i = 0; i < n; i++) {
        char name[100];
        char reason[100];
        int index;

        cin >> name >> index >> reason;

        arr[i] = DisciplinaryAction(name, index, reason);
    }

    cout << "-- Testing operator = & print() --\n";
    arr[0].print();

    /// Testing copy constructor & set index

    DisciplinaryAction merka(arr[0]);
    merka.setIndex(112233);

    cout << "\n-- Testing copy constructor & set index --\n";
    cout << "-------------\n";
    cout << "Source:\n";
    cout << "-------------\n";
    arr[0].print();

    cout << "\n-------------\n";
    cout << "Copied and edited:\n";
    cout << "-------------\n";
    merka.print();

    /// Testing if array is OK

    cout << "\n-- Testing if array was inputted correctly --\n";

    for(int i = 0; i < n; i++)
        arr[i].print();

    return 0;
}
