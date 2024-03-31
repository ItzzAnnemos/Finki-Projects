#include <stdio.h>

int factoriel(int n) {
    if (n == 1) return 1;
    return n * factoriel(n - 1);
}

int sum(int n) {
    if (n == 0) return 0;
    return n + sum(n - 1);
}

int count(int n) {
    if (n < 10) return n;
    return n % 10 + count(n / 10);
}

int main() {
    int broj;

    scanf("%d", &broj);

    //printf("%d\n", factoriel(broj));
    //printf("%d\n", sum(broj));
    printf("%d\n", count(broj));

    return 0;
}
