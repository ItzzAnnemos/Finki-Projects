#include <stdio.h>

int main() {
    int n,m,i,tmp,tmp2,cifra,cifra2,slicen;
    scanf("%d %d",&n,&m);

    for (i = n; ;i++) {
        tmp = i;
        tmp2 = m;
        while(tmp != 0) {
            cifra = tmp % 10;
            while (tmp2 != 0) {
                cifra2 = tmp2 % 10;
                if (cifra == cifra2) {
                    slicen = 1;
                }
                else {
                    slicen = 0;
                    break;
                }
                tmp2 /= 10;
            }
            if (slicen == 0) {
                break;
            }
            tmp /= 10;
        }
        if (slicen == 1) {
            printf("%d",i);
            break;
        }
    }
    return 0;
}
