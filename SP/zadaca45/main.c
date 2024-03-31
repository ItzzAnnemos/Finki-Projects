#include <stdio.h>

int najgolem(int n) {
    int cif = n % 10;
    if (n == 0) return cif;
    if (cif > najgolem(n / 10)) {
        return cif;
    }
}

int main() {
    int broj;
    while(scanf("%d",&broj)) {

        printf("%d\n",najgolem(broj));
    }
    return 0;
}
