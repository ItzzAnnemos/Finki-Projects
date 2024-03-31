#include <stdio.h>

int main() {
    int broj, cifra, vip = 0, vlez, ostatok;
    scanf("%d", &broj);
    if ((broj < 1000) && (broj > 99)) {
        cifra = broj / 100;
        if (cifra == 1) {
            vip = 1;
        } else {
            vip = 0;
        }
        ostatok = broj % 3;

        switch (ostatok) {
            case 0: {
                vlez = 1;
                break;
            }
            case 1: {
                vlez = 2;
                break;
            }
            case 2: {
                vlez = 3;
                break;
            }
        }
    } else
        printf("greska");

    if (vip == 1)
    printf("VIP posetitel na vlez %d",vlez);
    else
        printf("Regularen posetitel na vlez %d",vlez);

    return 0;
}
