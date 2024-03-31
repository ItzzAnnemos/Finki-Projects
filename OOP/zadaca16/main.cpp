#include <iostream>
#include <cstring>

using namespace std;

class Film {
private:
    char name[100];
    double rating;
    double revenue;
public:
    Film() {
    }

    Film(char *name, double rating, double revenue) {
        strcpy(this->name, name);
        this->rating = rating;
        this->revenue = revenue;
    }

    ~Film() {
    }

    void setName(char *x) {
        strcpy(this->name, x);
    }

    void setRating(double y) {
        this->rating = y;
    }

    void setRevenue(double z) {
        this->revenue = z;
    }

    double getRevenue() {
        return revenue;
    }

    void display() {
        cout << "Name of film: " << name << endl;
        cout << "Rating: " << rating << endl;
        cout << "Revenue: " << revenue << endl;
    }
};

void printMostPopularFilm(Film *films, int n) {
    int i;
    Film temp = films[0];
    for (i = 0; i < n; i++) {
        if (temp.getRevenue() < films[i].getRevenue()) {
            temp = films[i];
        }
    }
    temp.display();
}

int main() {
    int n;
    cin >> n;
    Film films[100];

    char name[100];
    double rating;
    double revenue;

    for (int i = 0; i < n - 1; i++) {
        cin >> name;
        cin >> rating;
        cin >> revenue;

        // testing constructor with arguments
        films[i] = Film(name, rating, revenue);

    }


    // testing set methods and display for last element
    cin >> name;
    cin >> rating;
    cin >> revenue;
    films[n - 1].setName(name);
    films[n - 1].setRating(rating);
    films[n - 1].setRevenue(revenue);

    cout << "-->Testing set methods and display()" << endl;
    films[n - 1].display();
    cout << endl;

    cout << "-->Testing printMostPopularFilm()" << endl;
    printMostPopularFilm(films, n);
    return 0;
}