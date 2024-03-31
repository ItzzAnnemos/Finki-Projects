#include <stdio.h>

int print(int n, int a) {
    if (n > a) {
        printf("\n");
        return 0;
    } else {
        printf("%d ", n);
        print(++n, a);
    }
}

int triagolnik(int n) {
    if (n < 1) {
        return 0;
    } else {
        int count = 1;
        print(count, n);
        triagolnik(n - 1);
    }
}

int main() {
    int n;
    scanf("%d", &n);

    triagolnik(n);

    return 0;
}
