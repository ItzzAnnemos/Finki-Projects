#include <iostream>
#include <cstring>

using namespace std;

/*input:
0
6
1
Game1 7
0
Game2 8
1
Game3 2
0
Game4 15
1
Game5 7
0
Game6 9
1*/

class Game {
protected:
    char title[50];
public:
    Game(char *title = "") {
        strcpy(this->title, title);
    }

    virtual void displayInfo() {
        cout << "Game: " << title << endl;
    }

    virtual int complexity() = 0;

    void print() {
        cout << "Game: " << title << endl;
    }
};

class BoardGame : virtual public Game {
protected:
    int maxPlayers;
public:
    BoardGame(char *title = "", int maxPlayers = 0) : Game(title) {
        this->maxPlayers = maxPlayers;
    }

    int complexity() {
        if (maxPlayers > 6) {
            return 10;
        } else if (maxPlayers > 3 && maxPlayers < 6) {
            return 5;
        } else {
            return 3;
        }
    }

    void displayInfo() {
        Game::displayInfo();
        cout << "Max players: " << maxPlayers << endl;
        cout << "Complexity: " << complexity() << endl;
    }
};

class CardGame : virtual public Game {
protected:
    int numCards;
public:
    CardGame(char *title = "", int numCards = 0) : Game(title) {
        this->numCards = numCards;
    }

    int complexity() {
        if (numCards > 20) {
            return 10;
        } else if (numCards > 10 && numCards < 20) {
            return 5;
        } else {
            return 3;
        }
    }

    void displayInfo() {
        Game::displayInfo();
        cout << "Number of cards: " << numCards << endl;
        cout << "Complexity: " << complexity() << endl;
    }
};

class BoardCardGame : public BoardGame, public CardGame {
public:
    BoardCardGame(char *title = "", int maxPlayers = 0, int numCards = 0) : Game(title), BoardGame(title, maxPlayers),
                                                                            CardGame(title, numCards) {
    }

    int complexity() {
        return (BoardGame::complexity() + CardGame::complexity()) / 2.0;
    }

    void displayInfo() {
        Game::print();
        cout << "Complexity: " << complexity() << endl;
    }
};

Game * mostComplexCardGame(Game *games[], int n) {
    int index, max = 0;
    for (int i = 0;i < n;i++) {
        CardGame * tmp = dynamic_cast <CardGame *>(games[i]);
        if (tmp != 0) {
            if (tmp->complexity() > max) {
                max = tmp->complexity();
                index = i;
            }
        }
    }
    return games[index];
}

int main() {
    char title[50];
    int maxPlayers;
    int numCards;
    int n;
    int choice;

    cin >> choice;
    if (choice == 1) {
        cin >> title;
        BoardCardGame boardCardGame(title, 8, 15);
        boardCardGame.displayInfo();
    } else {

        cin >> n;

        Game **g = new Game *[n];
        for (int i = 0; i < n; i++) {
            cin >> choice;
            if (choice == 1) {
                cin >> title >> maxPlayers;

                g[i] = new BoardGame(title, maxPlayers);
            } else {
                cin >> title >> numCards;

                g[i] = new CardGame(title, numCards);
            }

        }

        mostComplexCardGame(g, n)->displayInfo();

    }


    return 0;
}
