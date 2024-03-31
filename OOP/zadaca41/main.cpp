#include <iostream>
#include <cstring>

using namespace std;

class ExistingGame {
public:
    ExistingGame() {}

    void message() {
        cout << "The game is already in the collection" << endl;
    }
};

class Game {
protected:
    char ime[100];
    float cena;
    bool kupenaNaRasprodazba;
public:
    Game(char *ime = "", float cena = 0.0, bool kupenaNaRasprodazba = false) {
        strcpy(this->ime, ime);
        this->cena = cena;
        this->kupenaNaRasprodazba = kupenaNaRasprodazba;
    }

    Game(const Game &x) {
        strcpy(this->ime, x.ime);
        this->cena = x.cena;
        this->kupenaNaRasprodazba = x.kupenaNaRasprodazba;
    }

    Game operator=(const Game &x) {
        if (this != &x) {
            strcpy(this->ime, x.ime);
            this->cena = x.cena;
            this->kupenaNaRasprodazba = x.kupenaNaRasprodazba;
        }
        return *this;
    }

    virtual float get_price() {
        if (kupenaNaRasprodazba) {
            return cena * 0.3;
        } else
            return cena;
    }

    friend ostream &operator<<(ostream &out, Game &x) {
        out << "Game: " << x.ime << ", regular price: $" << x.cena;
        if (x.kupenaNaRasprodazba) {
            out << ", bought on sale";
        }
        return out;
    }

    friend istream &operator>>(istream &in, Game &x) {
        in.get();
        in.getline(x.ime, 100);
        in >> x.cena >> x.kupenaNaRasprodazba;
        return in;
    }

    bool operator==(Game &x) {
        if (strcmp(this->ime, x.ime) == 0)
            return true;
        else
            return false;
    }
};

class SubscriptionGame : public Game {
private:
    float mesecenNadomest;
    int mesec;
    int godina;
public:
    SubscriptionGame(char *ime = "", float cena = 0.0, bool kupenaNaRaspodazba = false, float mesecenNadomest = 0.0,
                     int mesec = 1, int godina = 2000) : Game(ime, cena, kupenaNaRaspodazba) {
        this->mesecenNadomest = mesecenNadomest;
        this->mesec = mesec;
        this->godina = godina;
    }

    SubscriptionGame(const SubscriptionGame &x) : Game(x) {
        this->mesecenNadomest = x.mesecenNadomest;
        this->mesec = x.mesec;
        this->godina = x.godina;
    }

    SubscriptionGame operator=(const SubscriptionGame &x) {
        if (this != &x) {
            strcpy(this->ime, x.ime);
            this->cena = x.cena;
            this->kupenaNaRasprodazba = x.kupenaNaRasprodazba;
            this->mesecenNadomest = x.mesecenNadomest;
            this->mesec = x.mesec;
            this->godina = x.godina;
        }
        return *this;
    }

    float get_price() {
        float total = Game::get_price();
        int months = 0;
        if (godina < 2018) {
            months = (12 - this->mesec) + (2017 - godina) * 12 + 5;
        } else {
            months = 5 - this->mesec;
        }

        total += months * mesecenNadomest;

        return total;
    }

    friend ostream &operator<<(ostream &out, SubscriptionGame &x) {
        out << "Game: " << x.ime << ", regular price: $" << x.cena;
        if (x.kupenaNaRasprodazba) {
            out << ", bought on sale";
        }
        out << ", monthly fee: $" << x.mesecenNadomest << ", purchased: " << x.mesec << "-" << x.godina;
        return out;
    }

    friend istream &operator>>(istream &in, SubscriptionGame &x) {
        in.get();
        in.getline(x.ime, 100);
        in >> x.cena >> x.kupenaNaRasprodazba >> x.mesecenNadomest >> x.mesec >> x.godina;
        return in;
    }

    bool operator==(SubscriptionGame &x) {
        if (strcmp(this->ime, x.ime) == 0)
            return true;
        else
            return false;
    }
};

class User {
private:
    char username[100];
    Game **games;
    int n;
public:
    User(char *username = "") {
        strcpy(this->username, username);
        this->n = 0;
        games = nullptr;
    }

    User(const User &x) {
        strcpy(this->username, x.username);
        this->n = x.n;
        for (int i = 0; i < x.n; i++) {
            this->games[i] = x.games[i];
        }
    }

    User operator=(const User &x) {
        if (this != &x) {
            delete[] games;
            strcpy(this->username, x.username);
            this->n = x.n;
            for (int i = 0; i < x.n; i++) {
                this->games[i] = x.games[i];
            }
        }
        return *this;
    }

    ~User() {
        delete[] games;
    }

    User &operator+=(Game &x) {

        Game **new_games = new Game *[this->n + 1];

        for (int i = 0; i < (this->n); ++i) {
            if ((*(this->games[i])) == x) {
                throw ExistingGame();
            }

            new_games[i] = games[i];
        }

        for (int i = 0; i < (this->n); ++i) {
            new_games[i] = games[i];
        }

        SubscriptionGame *sg = dynamic_cast< SubscriptionGame * >(&x);

        if (sg) {

            new_games[n] = new SubscriptionGame(*sg);
        } else {

            new_games[n] = new Game(x);
        }

        delete[] this->games;
        this->games = new_games;
        this->n++;

        return *this;
    }

    Game &get_game(int x) {
        return (*(games[x]));
    }

    char *get_name() {
        return username;
    }

    int get_games_number() {
        return n;
    }

    float total_spent() {
        float total = 0.0;
        for (int i = 0; i < n; i++) {
            total += games[i]->get_price();
        }
        return total;
    }

    friend ostream &operator<<(ostream &out, User &x) {
        out << endl;
        out << "User: " << x.username << endl;
        for (int i = 0; i < x.n; i++) {
            Game *g;
            SubscriptionGame *s;
            g = &(x.get_game(i));

            s = dynamic_cast<SubscriptionGame *> (g);

            if (s) {
                out << "- " << (*s);
            } else {
                out << "- " << (*g);
            }
            out << endl;
        }
        out << endl;
        return out;
    }

};

int main() {
    int test_case_num;

    cin >> test_case_num;

    // for Game
    char game_name[100];
    float game_price;
    bool game_on_sale;

    // for SubscritionGame
    float sub_game_monthly_fee;
    int sub_game_month, sub_game_year;

    // for User
    char username[100];
    int num_user_games;

    if (test_case_num == 1) {
        cout << "Testing class Game and operator<< for Game" << std::endl;
        cin.get();
        cin.getline(game_name, 100);
        //cin.get();
        cin >> game_price >> game_on_sale;

        Game g(game_name, game_price, game_on_sale);

        cout << g;
    } else if (test_case_num == 2) {
        cout << "Testing class SubscriptionGame and operator<< for SubscritionGame" << std::endl;
        cin.get();
        cin.getline(game_name, 100);

        cin >> game_price >> game_on_sale;

        cin >> sub_game_monthly_fee;
        cin >> sub_game_month >> sub_game_year;

        SubscriptionGame sg(game_name, game_price, game_on_sale, sub_game_monthly_fee, sub_game_month, sub_game_year);
        cout << sg;
    } else if (test_case_num == 3) {
        cout << "Testing operator>> for Game" << std::endl;
        Game g;

        cin >> g;

        cout << g;
    } else if (test_case_num == 4) {
        cout << "Testing operator>> for SubscriptionGame" << std::endl;
        SubscriptionGame sg;

        cin >> sg;

        cout << sg;
    } else if (test_case_num == 5) {
        cout << "Testing class User and operator+= for User" << std::endl;
        cin.get();
        cin.getline(username, 100);
        User u(username);

        int num_user_games;
        int game_type;
        cin >> num_user_games;

        try {

            for (int i = 0; i < num_user_games; ++i) {

                cin >> game_type;

                Game *g;
                // 1 - Game, 2 - SubscriptionGame
                if (game_type == 1) {
                    cin.get();
                    cin.getline(game_name, 100);

                    cin >> game_price >> game_on_sale;
                    g = new Game(game_name, game_price, game_on_sale);
                } else if (game_type == 2) {
                    cin.get();
                    cin.getline(game_name, 100);

                    cin >> game_price >> game_on_sale;

                    cin >> sub_game_monthly_fee;
                    cin >> sub_game_month >> sub_game_year;
                    g = new SubscriptionGame(game_name, game_price, game_on_sale, sub_game_monthly_fee, sub_game_month,
                                             sub_game_year);
                }

                //cout<<(*g);


                u += (*g);
            }
        } catch (ExistingGame &ex) {
            ex.message();
        }

        cout << u;

//    cout<<"\nUser: "<<u.get_username()<<"\n";

//    for (int i=0; i < u.get_games_number(); ++i){
//        Game * g;
//        SubscriptionGame * sg;
//        g = &(u.get_game(i));

//        sg = dynamic_cast<SubscriptionGame *> (g);

//        if (sg){
//          cout<<"- "<<(*sg);
//        }
//        else {
//          cout<<"- "<<(*g);
//        }
//        cout<<"\n";
//    }

    } else if (test_case_num == 6) {
        cout << "Testing exception ExistingGame for User" << std::endl;
        cin.get();
        cin.getline(username, 100);
        User u(username);

        int num_user_games;
        int game_type;
        cin >> num_user_games;

        for (int i = 0; i < num_user_games; ++i) {

            cin >> game_type;

            Game *g;
            // 1 - Game, 2 - SubscriptionGame
            if (game_type == 1) {
                cin.get();
                cin.getline(game_name, 100);

                cin >> game_price >> game_on_sale;
                g = new Game(game_name, game_price, game_on_sale);
            } else if (game_type == 2) {
                cin.get();
                cin.getline(game_name, 100);

                cin >> game_price >> game_on_sale;

                cin >> sub_game_monthly_fee;
                cin >> sub_game_month >> sub_game_year;
                g = new SubscriptionGame(game_name, game_price, game_on_sale, sub_game_monthly_fee, sub_game_month,
                                         sub_game_year);
            }

            //cout<<(*g);

            try {
                u += (*g);
            }
            catch (ExistingGame &ex) {
                ex.message();
            }
        }

        cout << u;

//      for (int i=0; i < u.get_games_number(); ++i){
//          Game * g;
//          SubscriptionGame * sg;
//          g = &(u.get_game(i));

//          sg = dynamic_cast<SubscriptionGame *> (g);

//          if (sg){
//            cout<<"- "<<(*sg);
//          }
//          else {
//            cout<<"- "<<(*g);
//          }
//          cout<<"\n";
//      }
    } else if (test_case_num == 7) {
        cout << "Testing total_spent method() for User" << std::endl;
        cin.get();
        cin.getline(username, 100);
        User u(username);

        int num_user_games;
        int game_type;
        cin >> num_user_games;

        for (int i = 0; i < num_user_games; ++i) {

            cin >> game_type;

            Game *g;
            // 1 - Game, 2 - SubscriptionGame
            if (game_type == 1) {
                cin.get();
                cin.getline(game_name, 100);

                cin >> game_price >> game_on_sale;
                g = new Game(game_name, game_price, game_on_sale);
            } else if (game_type == 2) {
                cin.get();
                cin.getline(game_name, 100);

                cin >> game_price >> game_on_sale;

                cin >> sub_game_monthly_fee;
                cin >> sub_game_month >> sub_game_year;
                g = new SubscriptionGame(game_name, game_price, game_on_sale, sub_game_monthly_fee, sub_game_month,
                                         sub_game_year);
            }

            //cout<<(*g);


            u += (*g);
        }

        cout << u;

        cout << "Total money spent: $" << u.total_spent() << endl;
    }
}

