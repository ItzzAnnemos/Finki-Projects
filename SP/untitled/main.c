#include <stdio.h>

int main() {
    int b, n, x, y, cif;
    scanf("%d", &n);
    if (n > 9) {
        for (int i = n - 1; i >= 9; --i) {
            y = 0;
            cif = 0;
            b = i;
            while (b != 0) {
                x = b % 10;
                y = y * 10 + x;
                b /= 10;
                cif++;
            }
            if (y % cif == 0 || i == 9) {
                printf("%d", i);
                break;
            }
        }
    } else {
        printf("Brojot ne e validen");
    }

}
