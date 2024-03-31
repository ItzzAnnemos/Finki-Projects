#include <stdio.h>

int main() {
    int broj,tmp,cifra,najgolem = 1,naj,mal,najmal = 9,i = 1,poz,poz2;
    while(scanf("%d",&broj)) {
        if (broj % 10 > najgolem) {
            naj = broj;
            najgolem = broj % 10;
            poz = i;
        }
        tmp = broj;
        cifra = 0;
        while(tmp != 0) {
            cifra = tmp % 10;
            tmp /= 10;
        }
        if (cifra < najmal) {
            mal = broj;
            najmal = cifra;
            poz2 = i;
        }
        i++;
    }
    printf("Najgolema najneznacajna cifra ima brojot %d na pozicija %d\n", naj,poz);
    printf("Najmala najznacajna cifra ima brojot %d na pozicija %d", mal,poz2);
    return 0;
}
