#include <stdio.h>

int main() {
    int br, tmp, cif, naj, i, j0 = 0, j1 = 0, j2 = 0, j3 = 0, j4 = 0,poz;
    while (scanf("%d", &br)) {
        tmp = br;
        i = 0;
        naj = 0;

        while (tmp != 0) {
            cif = tmp % 10;
            if (cif > naj) {
                poz = i;
                naj = cif;
            }
            tmp /= 10;
            i++;
        }
        switch (poz) {
            case 0: {
                j0++;
                break;
            }
            case 1: {
                j1++;
                break;
            }
            case 2: {
                j2++;
                break;
            }
            case 3: {
                j3++;
                break;
            }
            case 4: {
                j4++;
                break;
            }
            default:
                continue;

        }

    }
    printf("0: %d\n", j0);
    printf("1: %d\n", j1);
    printf("2: %d\n", j2);
    printf("3: %d\n", j3);
    printf("4: %d\n", j4);

    return 0;
}
