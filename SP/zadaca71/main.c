#include <stdio.h>

#define MAX 100

void premesti(int a[], int n) {
    int i, j = 0, b[MAX];
    for (i = 0; i < n; i++) {
        if (a[i] >= 0) {
            b[j] = a[i];
            j++;
        }
    }
    for (i = 0; i < n; i++) {
        if (a[i] < 0) {
            b[j] = a[i];
            j++;
        }
    }
    for (i = 0; i < n; i++) {
        a[i] = b[i];
    }
}

int main() {
    int n, i;
    int a[MAX];

    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }

    premesti(a, n);

    for (i = 0; i < n; i++) {
        printf("%d ", a[i]);
    }

    return 0;
}
