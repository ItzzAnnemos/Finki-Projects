#include <stdio.h>

#define MAX 100

int main() {
    float a[MAX][MAX],b[MAX][MAX];{0;};
    int n, i, j;
    float x = 0,y = 0;

    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            scanf("%f",&a[i][j]);
        }
    }

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            if (j < i) {
                x += a[i][j];
            }
            if (i + j >= n) {
                y += a[i][j];
            }
        }
    }

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            if (i == j) {
                b[i][j] = x;
            }
            if (i + j == n - 1) {
                b[i][j] += y;
            }
        }
    }

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            printf("%.1f ",b[i][j]);
        }
        printf("\n");
    }

    return 0;
}
