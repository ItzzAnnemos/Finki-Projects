#include <stdio.h>

int main() {
    int a, tmp, tmp2, kolega, i, m, cif;
    float procent1, procent;
    scanf("%d", &a);
    tmp = a;
    i = 0;
    m = 1;
    kolega = 0;
    while (tmp != 0) {
        cif = tmp % 10;
        if ((cif) == 5) {
            i++;
            kolega += 6 * m;
        } else {
            kolega += cif * m;
        }
        m *= 10;
        tmp /= 10;

    }
    tmp2 = a;
    procent1 = tmp2 / 100.0f;
    if (i >= 2) {
        if (kolega > a) {
            procent = (kolega - tmp2) / procent1;
        } else if (kolega < a) {
            procent = (tmp2 - kolega) / procent1;
        }
        printf("%0.4f%%", procent);
    } else
        printf("Error");

}
