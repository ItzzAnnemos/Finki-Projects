#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAX 80

int main() {
    int n, i, j, l, len, check,znak, naj = 0;
    char red[MAX], final[MAX], garbage;

    scanf("%d", &n);
    scanf("%c", &garbage);

    for (i = 0; i < n; i++) {
        fgets(red, MAX, stdin);
        len = strlen(red);
        l = len - 2;
        check = 1;
        znak = 0;
        for (j = 0; j < len / 2; j++) {
            if (!(isalpha(red[j])) && (!(isdigit(red[j])))) {
                znak = 1;
            }
            if (red[j] != red[l]) {
                check = 0;
            } else {
                l--;
            }
        }
        if (check && znak) {
            if (len > naj) {
                naj = len;
                strncpy(final, red, MAX);
            }
        }
    }

    if (naj) {
        printf("%s\n", final);
    } else {
        printf("Nema!");
    }

    return 0;
}
