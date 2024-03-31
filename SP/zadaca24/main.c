#include <stdio.h>
#include <math.h>

int main() {
    char c;
    int br = 0,broj = 0,cif,cifra,i = 0;
    while(scanf("%c",&c)) {
        if (c == '.') {
            break;
        }
        if ((c >= '0') && (c <= '7')) {
           printf("%d",c - '0');
           cifra = c - '0';
            br *= 10;
            br += cifra;
        }
    }

    while(br != 0) {
        cif = br % 10;
        broj = broj + (cif * pow(8,i));
        i++;
        br /= 10;
    }
    printf(" %d",broj);
    return 0;
}
