#include <stdio.h>

int main() {
    int broj, t1, t2, tmp, i, cc;
    while (scanf("%d", &broj)) {
        if (broj > 9) {
            tmp = broj;
            cc = 1;
            t1 = tmp % 10;

            if (t1 >= 5) { i = 0; }
            if (t1 < 5) { i = 1; }

            tmp /= 10;
            while (tmp != 0) {
                t1 = tmp % 10;
                if (i == 0) {
                    if (t1 < 5) {
                        i = !i;
                    } else {
                        cc = 0;
                        break;
                    }
                } else if (i == 1) {
                    if (t1 >= 5) {
                        i = !i;
                    } else {
                        cc = 0;
                        break;
                    }
                }
                tmp /= 10;
            }


            if (cc == 1) {
                printf("%d\n", broj);
            }
        }
    }
    return 0;
}

