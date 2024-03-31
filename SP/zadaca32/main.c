#include <stdio.h>

int main() {
    int n,i,broj,tmp,cif,naj,j = 0,podreden,suma = 0;
    scanf("%d",&n);
    for (i = 0;i < n;i++) {
        scanf("%d",&broj);
        tmp = broj;
        podreden = 1;
        naj = 10;
        while (tmp != 0) {
            cif = tmp % 10;
            if (cif <= naj) {
                naj = cif;
            } else {
                podreden = 0;
                break;
            }
            tmp /= 10;
        }
        if (podreden == 1) {
            j++;
            suma+=broj;
            printf("%d\n",broj);
        }
    }
    printf("%d\n",suma);
    printf("%d",j);
    return 0;
}
