#include <stdio.h>

int BrojPozitivni(int niza[], int n) {
    if (n == 1) {
        if (niza[n - 1] < 0) {
            return 0;
        }
        if (niza[n - 1] > 0) {
            return 1;
        }
    }
    if (niza[n - 1] < 0) {
        return BrojPozitivni(niza, n - 1);
    }
    else {
        return 1 + BrojPozitivni(niza, n - 1);
    }
}

int main() {
    int niza[100], n, i;
    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        scanf("%d", &niza[i]);
    }

    printf("%d",BrojPozitivni(niza, n));

    return 0;
}
