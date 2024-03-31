#include <stdio.h>

int main() {
    char c;
    int suma = 0;
    while (c = getchar()) {
        if (c == '.') {
            break;
        }
        if (((c >= '0') && (c <= '9')) || ((c >= 'A') && (c <= 'F')) || ((c >= 'a') && (c <= 'f'))) {
            switch (c) {
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    suma += (c - '0');
                    break;
                case 'A':
                    suma += 10;
                    break;
                case 'B':
                    suma += 11;
                    break;
                case 'C':
                    suma += 12;
                    break;
                case 'D':
                    suma += 13;
                    break;
                case 'E':
                    suma += 14;
                    break;
                case 'F':
                    suma += 15;
                    break;
                case 'a':
                    suma += 10;
                    break;
                case 'b':
                    suma += 11;
                    break;
                case 'c':
                    suma += 12;
                    break;
                case 'd':
                    suma += 13;
                    break;
                case 'e':
                    suma += 14;
                    break;
                case 'f':
                    suma += 15;
                    break;
            }
        }
    }
    if ((suma % 16 == 0) && ((suma % 10 == 6) && (suma % 100 / 10 == 1))) {
        printf("Poln pogodok");
    }
    else if (suma % 16 == 0) {
        printf("Pogodok");
    } else
        printf("%d", suma);

    return 0;
}
