#include <iostream>

using namespace std;

class List {
private:
    int *number;
    int n;
public:
    List(int *number, int n = 0) {
        this->n = n;
        this->number = new int[n];
        for (int i = 0; i < n; i++) {
            this->number[i] = number[i];
        }
    }

    List(int n = 0) {
        this->n = n;
        this->number = new int[n];
        for (int i = 0; i < n; i++) {
            this->number[i] = number[i];
        }
    }

    List(const List &x) {
        n = x.n;
        number = new int[n];
        for (int i = 0; i < n; i++) {
            number[i] = x.number[i];
        }
    }

    List &operator=(const List &x) {
        if (this != &x) {
            delete[] number;
            n = x.n;
            number = new int[n];
            for (int i = 0; i < n; i++) {
                number[i] = x.number[i];
            }
        }
        return *this;
    }

    ~List() {
        delete[] number;
    }

    int getN() {
        return n;
    }

    void pecati() {
        cout << n << ": ";
        for (int i = 0; i < n; i++) {
            cout << number[i] << " ";
        }
        cout << "sum: " << sum() << " average: " << average() << endl;
    }

    int sum() {
        int suma = 0;
        for (int i = 0; i < n; i++) {
            suma += number[i];
        }
        return suma;
    }

    double average() {
        return (double) sum() / (double) n;
    }
};

class ListContainer {
private:
    List *listi;
    int n;
    int dodadi = 0;
    int failed = 0;
public:
    ListContainer(int n = 0) {
        listi = new List[n];
        this->n = n;
        dodadi = 0;
        for (int i = 0; i < n; i++) {
            this->listi[i] = listi[i];
        }
    }

    ListContainer(const ListContainer &x) {
        n = x.n;
        dodadi = x.dodadi;
        listi = new List[n];
        for (int i = 0; i < n; i++) {
            listi[i] = x.listi[i];
        }
    }

    ListContainer(List *listi, int n = 0, int dodadi = 0) {
        this->n = n;
        this->dodadi = dodadi;
        this->listi = new List[n];
        for (int i = 0; i < n; i++) {
            this->listi[i] = listi[i];
        }
    }

    ListContainer operator=(const ListContainer &x) {
        if (this != &x) {
            delete[] listi;
            n = x.n;
            dodadi = x.dodadi;
            failed = x.failed;
            listi = new List[n];
            for (int i = 0; i < n; i++) {
                listi[i] = x.listi[i];
            }
        }
        return *this;
    }

    ~ListContainer() {
        delete[] listi;
    }

    void print() {
        if (n == 0) {
            cout << "The list is empty" << endl;
            return;
        }
        int i = 0;
        for (i = 0; i < n; i++) {
            cout << "List number: " << i + 1 << " List info: ";
            listi[i].pecati();
        }
        cout << "Sum: " << sum() << " Average: " << average() << endl;
        cout << "Successful attempts: " << dodadi << " Failed attempts: " << failed << endl;
    }

    int sum() {
        int i, suma = 0;
        for (i = 0; i < n; i++) {
            suma += listi[i].sum();
        }
        return suma;
    }

    double average() {
        int tmp = 0;
        for (int i = 0; i < n; i++) {
            tmp += listi[i].getN();
        }
        return sum() / (double) tmp;
    }

    void addNewList(List x) {
        int i;
        for (i = 0; i < n; i++) {
            if (x.sum() == listi[i].sum()) {
                failed++;
                return;
            }
        }
        List *tmp = new List[n + 1];
        for (i = 0; i < n; i++) {
            tmp[i] = listi[i];
        }
        tmp[n] = x;
        delete[] listi;
        listi = tmp;
        n++;
        dodadi++;
    }
};

int main() {

    ListContainer lc;
    int N;
    cin >> N;

    for (int i = 0; i < N; i++) {
        int n;
        int niza[100];

        cin >> n;

        for (int j = 0; j < n; j++) {
            cin >> niza[j];

        }

        List l = List(niza, n);

        lc.addNewList(l);
    }


    int testCase;
    cin >> testCase;

    if (testCase == 1) {
        cout << "Test case for operator =" << endl;
        ListContainer lc1;
        lc1.print();
        cout << lc1.sum() << " " << lc.sum() << endl;
        lc1 = lc;
        lc1.print();
        cout << lc1.sum() << " " << lc.sum() << endl;
        lc1.sum();
        lc1.average();

    } else {
        lc.print();
    }
}