#include <stdio.h>
#include <math.h>

#define MAX 100

int main() {
    int n, m, i, j, suma1 = 0, suma2 = 0;
    int a[MAX][MAX];
    scanf("%d %d", &n, &m);

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            scanf("%d", &a[i][j]);
        }
    }

    for (i = 0; i < n; i++) {
        suma1 = 0;
        suma2 = 0;
        for (j = 0; j < m; j++) {
            if (m % 2) {
                if (j <= m / 2) {
                    suma1 += a[i][j];
                }
                if (j >= m / 2) {
                    suma2 += a[i][j];
                }
            } else {
                if (j < m / 2) {
                    suma1 += a[i][j];
                }
                if (j >= m / 2) {
                    suma2 += a[i][j];
                }
            }
        }

        if (m % 2) {
            for (j = 0; j < m; j++) {
                if (j == m / 2) {
                    a[i][j] = fabs(suma1 - suma2);
                }
            }
        } else {
            for (j = 0; j < m; j++) {
                if (j == m / 2) {
                    a[i][j] = fabs(suma1 - suma2);
                    a[i][j - 1] = fabs(suma1 - suma2);
                }
            }
        }
    }

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            printf("%d ", a[i][j]);
        }
        printf("\n");
    }

    return 0;
}
