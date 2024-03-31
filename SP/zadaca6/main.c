#include <stdio.h>

int main() {
    int br1, br2, tmp1, tmp2, cif, i = 0;
    scanf("%d %d", &br1, &br2);

    if ((br1 > 0) && (br2 > 0)) {
        tmp1 = br1;
        tmp2 = br2;
        while (tmp1 != 0) {
            if (br1 > br2) {
                cif = tmp1 % 100 / 10;
                if (cif == (tmp2 % 10)) {
                    i = 1;
                } else { i = 2; break;}
            }
            if (br1 < br2) {
                cif = tmp2 % 100 / 10;
                if (cif == (tmp1 % 10)) {
                    i = 1;
                } else { i = 2; break;}
            }
            tmp1 /= 100;
            tmp2 /= 10;
        }
        if (i == 1)
            printf("PAREN");
        if (i == 2)
            printf("NE");
    } else
        printf("Invalid input");

    return 0;
}
