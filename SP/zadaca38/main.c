#include <stdio.h>

int main() {
    int a1[100], a2[100];
    int n, i, indeks = 0, k;
    scanf("%d", &n);

    for (i = 0; i < n; i++) {

        scanf("%d", &a1[i]);
    }

    scanf("%d", &k);

    for (i = 0; i < n; i++) {

        if (a1[i] < k) {
            a2[indeks] = a1[i];
            indeks++;
        }
    }

    for (i = 0; i < n; i++) {
        if (a1[i] >= k) {
            a2[indeks] = a1[i];
            indeks++;
        }
    }


    for (i = 0; i < n; i++) {
        printf("%d ", a2[i]);
    }

    return 0;
}