#include <stdio.h>

int main() {
    int a[100][100];
    int i, j, n;

    scanf("%d", &n);
    if (n % 2 == 0) {
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                scanf("%d", &a[i][j]);
            }
        }
    } else {
        printf("GRESKA");
        return 0;
    }
    for (i = 0; i < n / 2; i++) {
        for (j = 0; j < n / 2; j++) {
            a[i][j] = a[i][j] + a[i][n - 1 - j] + a[n - 1 - i][j] + a[n - 1 - i][n - 1 - j];

        }
    }
    for (i = 0; i < n / 2; i++) {
        for (j = 0; j < n / 2; j++) {
            printf("%d ", a[i][j]);
        }
        printf("\n");
    }
    return 0;
}
