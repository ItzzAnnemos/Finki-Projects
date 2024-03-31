#include <stdio.h>

int main() {
    int a[100][100];
    int n, m, i, j, count,count2, si = 0;

    scanf("%d%d", &n, &m);
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            scanf("%d", &a[i][j]);
        }
    }

    count2 = 1;
    for (i = 0; i < n; i++) {
        count = 1;
        for (j = 0; j < m - 1; j++) {
            if ((a[i][j] == 1) && (a[i][j + 1] == 1)) {
                count++;
            } else {
                if (count >= 3) {
                    si++;
                }
                count = 1;
            }
        }
        if (count >= 3) {
            si++;
        }
    }
    for (j = 0; j < m; j++) {
        count = 1;
        for (i = 0; i < n - 1; i++) {
            if ((a[i][j] == 1) && (a[i + 1][j] == 1)) {
                count++;
            } else {
                if (count >= 3) {
                    si++;
                }
                count = 1;
            }
        }
        if (count >= 3) {
            si++;
        }
    }
    printf("%d", si);
    return 0;
}