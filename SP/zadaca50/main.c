#include <stdio.h>
#include <stdlib.h>
#include <math.h>

#define MAX 100

int main() {
    int a[MAX][MAX];
    int n, m, i, j, sum, naj;
    float prosek,raz,najodd;

    scanf("%d %d", &n, &m);
    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            scanf("%d", &a[i][j]);
        }
    }

    int b[n];

    for (i = 0; i < n; i++) {
        sum = 0;
        for (j = 0; j < m; j++) {
            sum += a[i][j];
        }

        prosek = (float) sum / (float) m;
        najodd = 0.0;
        naj = a[i][0];
        for (j = 0; j < m; j++) {
            raz = fabsf(prosek - (float) a[i][j]);
            if (raz > najodd) {
                najodd = raz;
                naj = a[i][j];
            }
        }
        b[i] = naj;
    }

    for (i = 0; i < n; i++) {
        printf("%d ", b[i]);
    }

    return 0;
}
