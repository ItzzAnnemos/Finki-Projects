#include <stdio.h>

int main() {
    int n,tmp,tmp2,suma,cif,naj = 0,cifra;
    while(scanf("%d",&n)) {
        tmp = n;
        tmp2 = n;
        suma = 0;
        while (tmp != 0) {
            cif = tmp % 10;
            suma += cif;
            tmp /= 10;
        }
        suma += naj;
        naj = 0;
        while (tmp2 != 0) {
            cifra = tmp2 % 10;
            if (cifra > naj) {
                naj = cifra;
            }
            tmp2 /= 10;
        }
        printf("%d: %d\n",n,suma);

    }
    return 0;
}
