#include <stdio.h>

int sum_pos(int *a, int *p, int *p2) {
    int i, m, sum = 0;
    if (p > p2) return 0;

    for (i = *p;i < (m = *p2); i++) {
        sum += a[i];
    }

    printf("%d",sum);
}

int main() {
    int a[100];
    int ind, n, i;
    int * p = &ind, *p2 = &n;


    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }

    scanf("%d", &ind);

    sum_pos(a, p, p2);

    return 0;
}
