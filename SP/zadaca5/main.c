#include <stdio.h>

int main() {
    int cif, broj = 0, suma = 0, bool = 0;
    char c = 'a';

    while (c = getchar()) {

        if (('0' <= c) && (c <= '9')) {
            bool = 1;
            cif = c - '0';
            broj = broj * 10 + cif;

        } else if (bool) {
            suma += broj;
            broj = bool = 0;

        }

        if (c == '!') {
            break;
        }

    }
    printf("%d", suma);

    return 0;
}
