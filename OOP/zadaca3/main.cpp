#include <iostream>
#include <cstring>
using namespace std;

class BasketballPlayer {
private:
    char ime[20];
    char prezime[20];
    int broj;
public:
    int poeni1;
    int poeni2;
    int poeni3;
    float average;
    BasketballPlayer(){
    }
    BasketballPlayer(char * name, char * surname, int number, int points1, int points2, int points3) {
        strcpy(ime,name);
        strcpy(prezime,surname);
        broj = number;
    }
    void setIme(char * name){
        strcpy(ime,name);
    }
    void setPrezime(char * surname){
        strcpy(prezime,surname);
    }
    void setBroj(int number){
        broj = number;
    }
    void getInfo() {
        printf("Player: %s %s with number: %d has %.2f points on average",ime,prezime,broj,average);
    }
};

int main() {
    BasketballPlayer igrac;
    char name[20],surname[20];
    int number,points1,points2,points3;
    float average;

    scanf("%s",name);
    scanf("%s",surname);
    scanf("%d",&number);
    scanf("%d",&points1);
    scanf("%d",&points2);
    scanf("%d",&points3);

    average = (points1 + points2 + points3) / 3.0;

    igrac.setIme(name);
    igrac.setPrezime(surname);
    igrac.setBroj(number);
    igrac.poeni1 = points1;
    igrac.poeni2 = points2;
    igrac.poeni3 = points3;
    igrac.average = average;

    igrac.getInfo();

    return 0;
}
