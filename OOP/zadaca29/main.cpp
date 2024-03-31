#include <iostream>
#include <cstring>

using namespace std;

class NBAPlayer {
protected:
    char *name;
    char team[40];
    double avgPoints;
    double avgAssists;
    double avgRebounds;
public:
    NBAPlayer() {
        name = nullptr;
        avgPoints = 0.0;
        avgAssists = 0.0;
        avgRebounds = 0.0;
    }

    NBAPlayer(char *name, char *team, double avgPoints, double avgAssists, double avgRebounds) {
        this->name = new char[strlen(name) + 1];
        strcpy(this->name, name);
        strcpy(this->team, team);
        this->avgPoints = avgPoints;
        this->avgAssists = avgAssists;
        this->avgRebounds = avgRebounds;
    }

    NBAPlayer(const NBAPlayer &x) {
        this->name = new char[strlen(x.name) + 1];
        strcpy(this->name, x.name);
        strcpy(this->team, x.team);
        this->avgPoints = x.avgPoints;
        this->avgAssists = x.avgAssists;
        this->avgRebounds = x.avgRebounds;
    }

    NBAPlayer operator=(const NBAPlayer &x) {
        if (this != &x) {
            delete[] name;
            this->name = new char[strlen(x.name) + 1];
            strcpy(this->name, x.name);
            strcpy(this->team, x.team);
            this->avgPoints = x.avgPoints;
            this->avgAssists = x.avgAssists;
            this->avgRebounds = x.avgRebounds;
        }
        return *this;
    }

    ~NBAPlayer() {
        delete[] name;
    }

    double rating() {
        return 0.45 * avgPoints + 0.30 * avgAssists + 0.25 * avgRebounds;
    }

    void print() {
        cout << name << " - " << team << endl;
        cout << "Points: " << avgPoints << endl;
        cout << "Assists: " << avgAssists << endl;
        cout << "Rebounds: " << avgRebounds << endl;
        cout << "Rating: " << rating() << endl;
    }
};

class AllStarPlayer : public NBAPlayer {
private:
    double avgPointsAllStar;
    double avgAssistsAllStar;
    double avgRebountsAllStar;
public:
    AllStarPlayer() {
    }

    AllStarPlayer(const NBAPlayer &x, double avgPointsAllStar, double avgAssistsAllStar, double avgReboundsAllStar)
            : NBAPlayer(x) {
        this->avgPointsAllStar = avgPointsAllStar;
        this->avgAssistsAllStar = avgAssistsAllStar;
        this->avgRebountsAllStar = avgReboundsAllStar;
    }

    AllStarPlayer(char *name, char *team, double avgPoints, double avgAssists, double avgRebounds,
                  double avgPointsAllStar, double avgAssistsAllStar, double avgReboundsAllStar) :
            NBAPlayer(name, team, avgPoints, avgAssists, avgRebounds) {
        this->avgPointsAllStar = avgPointsAllStar;
        this->avgAssistsAllStar = avgAssistsAllStar;
        this->avgRebountsAllStar = avgReboundsAllStar;
    }

    AllStarPlayer(const AllStarPlayer &x) : NBAPlayer(x) {
        this->avgPointsAllStar = x.avgPointsAllStar;
        this->avgAssistsAllStar = x.avgAssistsAllStar;
        this->avgRebountsAllStar = x.avgRebountsAllStar;
    }

    AllStarPlayer operator=(const AllStarPlayer &x) {
        if (this != &x) {
            this->name = new char[strlen(x.name) + 1];
            strcpy(this->name, x.name);
            strcpy(this->team, x.team);
            this->avgPoints = x.avgPoints;
            this->avgAssists = x.avgAssists;
            this->avgRebounds = x.avgRebounds;
            this->avgPointsAllStar = x.avgPointsAllStar;
            this->avgAssistsAllStar = x.avgAssistsAllStar;
            this->avgRebountsAllStar = x.avgRebountsAllStar;
        }
        return *this;
    }

    ~AllStarPlayer() {
    }

    double allStarRating() {
        return 0.30 * avgPointsAllStar + 0.40 * avgAssistsAllStar + 0.30 * avgRebountsAllStar;
    }

    double rating() {
        return (NBAPlayer::rating() + allStarRating()) / 2.0;
    }

    void print() {
        NBAPlayer::print();
        cout << "All Star Rating: " << allStarRating() << endl;
        cout << "New Rating: " << rating() << endl;
    }
};

int main() {

    char name[50];
    char team[40];
    double points;
    double assists;
    double rebounds;
    double allStarPoints;
    double allStarAssists;
    double allStarRebounds;

    NBAPlayer *players = new NBAPlayer[5];
    AllStarPlayer *asPlayers = new AllStarPlayer[5];
    int n;
    cin >> n;

    if (n == 1) {

        cout << "NBA PLAYERS:" << endl;
        cout << "=====================================" << endl;
        for (int i = 0; i < 5; ++i) {
            cin >> name >> team >> points >> assists >> rebounds;
            players[i] = NBAPlayer(name, team, points, assists, rebounds);
            players[i].print();
        }
    } else if (n == 2) {

        for (int i = 0; i < 5; ++i) {
            cin >> name >> team >> points >> assists >> rebounds;
            cin >> allStarPoints >> allStarAssists >> allStarRebounds;
            players[i] = NBAPlayer(name, team, points, assists, rebounds);
            asPlayers[i] = AllStarPlayer(players[i], allStarPoints, allStarAssists, allStarRebounds);
        }

        cout << "NBA PLAYERS:" << endl;
        cout << "=====================================" << endl;
        for (int i = 0; i < 5; ++i)
            players[i].print();

        cout << "ALL STAR PLAYERS:" << endl;
        cout << "=====================================" << endl;
        for (int i = 0; i < 5; ++i)
            asPlayers[i].print();

    } else if (n == 3) {

        for (int i = 0; i < 5; ++i) {
            cin >> name >> team >> points >> assists >> rebounds;
            cin >> allStarPoints >> allStarAssists >> allStarRebounds;
            asPlayers[i] = AllStarPlayer(name, team, points, assists, rebounds,
                                         allStarPoints, allStarAssists, allStarRebounds);
        }
        cout << "ALL STAR PLAYERS:" << endl;
        cout << "=====================================" << endl;
        for (int i = 0; i < 5; ++i)
            asPlayers[i].print();

    }

    delete[] players;
    delete[] asPlayers;
}