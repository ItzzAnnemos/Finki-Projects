#include <stdio.h>

int main() {
    int N, X, tmp, tmp2, cifra, count;
    scanf("%d %d", &N, &X);
    for (int i = (N - 1); ; i--) {
        tmp = i;
        count = 1;
        while (tmp != 0) {
            tmp2 = X;
            cifra = tmp % 10;

            while (tmp2 != 0) {
                if (cifra == (tmp2 % 10)) {
                    count = 0;
                    break;
                }
                tmp2 /= 10;
            }
            tmp /= 10;
        }

        if (count == 1) {
            printf("%d", i);
            break;
        }
    }

    return 0;
}
