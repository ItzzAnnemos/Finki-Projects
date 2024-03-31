#include <stdio.h>

int main() {
    int a[100][100];
    int i, j, n, m, b1, b2;
    int sum1, sum2, sum3, sum4;

    scanf("%d%d", &n, &m);
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            scanf("%d", &a[i][j]);
        }
    }
    scanf("%d%d", &b1, &b2);

    sum1 = sum2 = sum3 = sum4 = 0;

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            if ((b1 > i) && (b2 <= j)) {
                sum1 += a[i][j];
            }
            if ((b1 > i) && (b2 > j)) {
                sum2 += a[i][j];
            }
            if ((b1 <= i) && (b2 > j)) {
                sum3 += a[i][j];
            }
            if ((b1 <= i) && (b2 <= j)) {
                sum4 += a[i][j];
            }
        }
    }
    printf("%d %d %d %d",sum1,sum2,sum3,sum4);

    return 0;
}
