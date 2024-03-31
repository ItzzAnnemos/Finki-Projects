#include <stdio.h>

#define max 100

void scale(int *a, int n) {
    int i, tmp = 0;
    float prosek;
    for (i = 0; i < n; i++) {
        if (a[i] > tmp) {
            tmp = a[i];
        }
    }

    prosek = (float)100 / (float)tmp;

    for (i = 0; i < n; i++) {
        printf("%.2f ", a[i] * prosek);
    }
}

int main() {
    int n, i;
    int a[max];

    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }

    scale(a, n);

    //for (i = 0; i < n; i++) {
    //    printf("%f ", a[i]);
    //}

    return 0;
}
