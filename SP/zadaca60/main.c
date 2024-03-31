#include <stdio.h>

#define MAX 100

int main() {
    int n, i, j;
    int a[MAX][MAX], b[MAX][MAX];
    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        for (j = 0; j < n * 2; j++) {
            scanf("%d", &a[i][j]);
        }
    }

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            b[i][j] = a[i][j];
        }
    }

    for (i = n; i < n * 2; i++) {
        for (j = 0; j < n; j++) {
            b[i][j] = a[i - n][j + n];
        }
    }

    for (i = 0; i < n * 2; i++) {
        for (j = 0; j < n; j++) {
            printf("%d ", b[i][j]);
        }
        printf("\n");
    }

    return 0;
}
