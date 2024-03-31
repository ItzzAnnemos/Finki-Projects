#include <stdio.h>

int main () {
    int n,i,tmp,cif,sreken;
    scanf("%d",&n);
    for (i = n;i < 99999 ;i++) {
        tmp = i;
        while (tmp != 0) {
            cif = tmp % 10;
            if ((cif == 7) || ( cif == 9)) {
                sreken = 1;
            }
            else {
                sreken = 0;
                break;
            }
            tmp /= 10;
        }
        if (sreken == 1) {
            printf("%d",i);
            break;
        }
    }


    return 0;
}