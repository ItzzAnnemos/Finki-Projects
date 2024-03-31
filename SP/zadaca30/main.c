#include <stdio.h>

int main() {
    int n, cifra,vlez,pol;
    scanf("%d", &n);
    if ((n < 100000) && (n > 9999)) {
        cifra = n / 10000;
        if (n % 2 == 0) {
            pol = 1;
        } else
            pol = 0;
        switch (cifra) {
            case 1:
            case 2:
            case 3: {
                vlez = 1;
                break;
            }
            case 4:
            case 5:
            case 6: {
                vlez = 2;
                break;
            }
            case 7:
            case 8:
            case 9: {
                vlez = 3;
                break;
            }
        }
        if (pol == 1) {
            printf("Vlez %d na severnata tribina", vlez);
        } else
            printf("Vlez %d na juznata tribina", vlez);
    }
    else
        printf("greska");
    return 0;
}
