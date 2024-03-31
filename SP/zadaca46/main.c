#include <stdio.h>

void sort(int *x, int *y, int *z) {
    int temp;
    if (*x < *z) {
        temp = *z;
        *z = *x;
        *x = temp;
    }
    if (*y < *z) {
        temp = *z;
        *z = *y;
        *y = temp;
    }
    if (*x < *y) {
        temp = *x;
        *x = *y;
        *y = temp;
    }
}

int main() {

    int a, b, c;
    scanf("%d %d %d", &a, &b, &c);

    sort(&a, &b, &c);

    printf("%d %d %d", a, b, c);

    return 0;
}
