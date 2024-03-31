#include <iostream>
#include <cstring>

using namespace std;

class Book {
protected:
    char isbn[20];
    char naslov[50];
    char avtor[30];
    double cena;
public:
    Book(char *isbn = "", char *naslov = "", char *avtor = "", double cena = 0.0) {
        strcpy(this->isbn, isbn);
        strcpy(this->naslov, naslov);
        strcpy(this->avtor, avtor);
        this->cena = cena;
    }

    Book(const Book &x) {
        strcpy(this->isbn, x.isbn);
        strcpy(this->naslov, x.naslov);
        strcpy(this->avtor, x.avtor);
        this->cena = x.cena;
    }

    virtual double bookPrice() = 0;

    friend ostream &operator<<(ostream &out, Book &x) {
        out << x.isbn << ": " << x.naslov << ", " << x.avtor << " " << x.bookPrice() << endl;
        return out;
    }

    virtual bool operator>(const Book &x) {
        return (this->cena > x.cena);
    }
};

class OnlineBook : public Book {
private:
    char *url;
    int golemina;
public:
    OnlineBook(char *isbn = "", char *naslov = "", char *avtor = "", double cena = 0.0, char *url = "",
               int golemina = 0) :
            Book(isbn, naslov, avtor, cena) {
        this->url = new char[strlen(url) + 1];
        strcpy(this->url, url);
        this->golemina = golemina;
    }

    OnlineBook(const OnlineBook &x) {
        strcpy(this->isbn, x.isbn);
        strcpy(this->naslov, x.naslov);
        strcpy(this->avtor, x.avtor);
        this->cena = x.cena;
        this->url = new char[strlen(x.url) + 1];
        strcpy(this->url, x.url);
        this->golemina = x.golemina;
    }

    OnlineBook operator=(const OnlineBook &x) {
        if (this != &x) {
            delete url;
            strcpy(this->isbn, x.isbn);
            strcpy(this->naslov, x.naslov);
            strcpy(this->avtor, x.avtor);
            this->cena = x.cena;
            this->url = new char[strlen(x.url) + 1];
            strcpy(this->url, x.url);
            this->golemina = x.golemina;
        }
        return *this;
    }

    ~OnlineBook() {
        delete[] url;
    }

    double bookPrice() {
        if (golemina > 20) {
            return cena * 1.2;
        } else {
            return cena;
        }
    }

    friend ostream &operator<<(ostream &out, OnlineBook &x) {
        out << x.isbn << ": " << x.naslov << ", " << x.avtor << " ";
        if (x.golemina >= 20) {
            out << x.cena * 1.2 << endl;
        } else {
            out << x.cena << endl;
        }
        return out;
    }

    bool operator>(OnlineBook *x) {
        return (this->bookPrice() > x->bookPrice());
    }

    void setISBN(char *isbn) {
        strcpy(this->isbn, isbn);
    }
};

class PrintBook : public Book {
private:
    double masa;
    bool zaliha;
public:
    PrintBook(char *isbn = "", char *naslov = "", char *avtor = "", double cena = 0.0, double masa = 0.0,
              bool zaliha = true) :
            Book(isbn, naslov, avtor, cena) {
        this->masa = masa;
        this->zaliha = zaliha;
    }

    PrintBook(const PrintBook &x) {
        strcpy(this->isbn, x.isbn);
        strcpy(this->naslov, x.naslov);
        strcpy(this->avtor, x.avtor);
        this->cena = x.cena;
        this->masa = x.masa;
        this->zaliha = x.zaliha;
    };

    PrintBook operator=(const PrintBook &x) {
        if (this != &x) {
            strcpy(this->isbn, x.isbn);
            strcpy(this->naslov, x.naslov);
            strcpy(this->avtor, x.avtor);
            this->cena = x.cena;
            this->masa = x.masa;
            this->zaliha = x.zaliha;
        }
        return *this;
    }

    ~PrintBook() {
    }

    double bookPrice() {
        if (masa > 0.7) {
            return cena * 1.15;
        } else {
            return cena;
        }
    }

    friend ostream &operator<<(ostream &out, PrintBook &x) {
        out << x.isbn << ": " << x.naslov << ", " << x.avtor << " ";
        if (x.masa > 0.7) {
            out << x.cena * 1.15 << endl;
        } else {
            out << x.cena << endl;
        }
        return out;
    }

    bool operator>(PrintBook *x) {
        return (this->bookPrice() > x->bookPrice());
    }
};

void mostExpensiveBook(Book **books, int n) {
    int temp1 = 0, temp2 = 0, index;
    double naj = books[0]->bookPrice();
    for (int i = 0; i < n; i++) {
        OnlineBook *c = dynamic_cast<OnlineBook *>(books[i]);
        if (c != 0) {
            temp1++;
        }
        PrintBook *v = dynamic_cast<PrintBook *>(books[i]);
        if (v != 0) {
            temp2++;
        }
    }
    for (int i = 1; i < n - 1; i++) {
        if (books[i]->bookPrice() > naj) {
            naj = books[i]->bookPrice();
            index = i;
        }
    }
    cout << "FINKI-Education" << endl;
    cout << "Total number of online books: " << temp1 << endl;
    cout << "Total number of print books: " << temp2 << endl;
    cout << "The most expensive book is: " << endl;
    cout << *books[index];
}

int main() {

    char isbn[20], title[50], author[30], url[100];
    int size, tip;
    float price, weight;
    bool inStock;
    Book **books;
    int n;

    int testCase;
    cin >> testCase;

    if (testCase == 1) {
        cout << "====== Testing OnlineBook class ======" << endl;
        cin >> n;
        books = new Book *[n];

        for (int i = 0; i < n; i++) {
            cin >> isbn;
            cin.get();
            cin.getline(title, 50);
            cin.getline(author, 30);
            cin >> price;
            cin >> url;
            cin >> size;
            cout << "CONSTRUCTOR" << endl;
            books[i] = new OnlineBook(isbn, title, author, price, url, size);
            cout << "OPERATOR <<" << endl;
            cout << *books[i];
        }
        cout << "OPERATOR >" << endl;
        cout << "Rezultat od sporedbata e: " << endl;
        if (*books[0] > *books[1])
            cout << *books[0];
        else
            cout << *books[1];
    }
    if (testCase == 2) {
        cout << "====== Testing OnlineBook CONSTRUCTORS ======" << endl;
        cin >> isbn;
        cin.get();
        cin.getline(title, 50);
        cin.getline(author, 30);
        cin >> price;
        cin >> url;
        cin >> size;
        cout << "CONSTRUCTOR" << endl;
        OnlineBook ob1(isbn, title, author, price, url, size);
        cout << ob1 << endl;
        cout << "COPY CONSTRUCTOR" << endl;
        OnlineBook ob2(ob1);
        cin >> isbn;
        ob2.setISBN(isbn);
        cout << ob1 << endl;
        cout << ob2 << endl;
        cout << "OPERATOR =" << endl;
        ob1 = ob2;
        cin >> isbn;
        ob2.setISBN(isbn);
        cout << ob1 << endl;
        cout << ob2 << endl;
    }
    if (testCase == 3) {
        cout << "====== Testing PrintBook class ======" << endl;
        cin >> n;
        books = new Book *[n];

        for (int i = 0; i < n; i++) {
            cin >> isbn;
            cin.get();
            cin.getline(title, 50);
            cin.getline(author, 30);
            cin >> price;
            cin >> weight;
            cin >> inStock;
            cout << "CONSTRUCTOR" << endl;
            books[i] = new PrintBook(isbn, title, author, price, weight, inStock);
            cout << "OPERATOR <<" << endl;
            cout << *books[i];
        }
        cout << "OPERATOR >" << endl;
        cout << "Rezultat od sporedbata e: " << endl;
        if (*books[0] > *books[1])
            cout << *books[0];
        else
            cout << *books[1];
    }
    if (testCase == 4) {
        cout << "====== Testing method mostExpensiveBook() ======" << endl;
        cin >> n;
        books = new Book *[n];

        for (int i = 0; i < n; i++) {

            cin >> tip >> isbn;
            cin.get();
            cin.getline(title, 50);
            cin.getline(author, 30);
            cin >> price;
            if (tip == 1) {

                cin >> url;
                cin >> size;

                books[i] = new OnlineBook(isbn, title, author, price, url, size);

            } else {
                cin >> weight;
                cin >> inStock;

                books[i] = new PrintBook(isbn, title, author, price, weight, inStock);
            }
        }

        mostExpensiveBook(books, n);
    }

    for (int i = 0; i < n; i++) delete books[i];
    delete[] books;
    return 0;
}
