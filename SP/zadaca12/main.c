#include <stdio.h>

int main() {
    int broj, t1, t2, tmp, i, cc;
    while (scanf("%d", &broj)) {
        if (broj > 9) {
            t1 = broj % 10;
            t2 = broj % 100 / 10;
            tmp = broj;
            cc = 1;

            if (t1 > t2) { i = 0; }
            else if (t1 < t2) { i = 1; }
            else cc = 0;

            tmp /= 10;

            while (tmp >= 10) {
                t1 = tmp % 10;
                t2 = tmp % 100 / 10;

                if ((t1 >= t2) && (i == 0)) {
                    cc = 0;
                    break;
                }
                if ((t1 <= t2) && (i == 1)) {
                    cc = 0;
                    break;
                }

                i =!i;
                tmp /= 10;
            }
            if (cc == 1) {
                printf("%d\n",broj);
            }

        }
    }
    return 0;
}
