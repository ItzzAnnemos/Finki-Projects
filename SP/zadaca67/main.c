#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAX 50

int main() {
    int n, i, j, len, check = 0;
    char niza[MAX];

    scanf("%d", &n);

    for (i = 0; i < n + 1; i++) {
        fgets(niza, MAX, stdin);
        len = strlen(niza);
        check = 0;
        for (j = 1; j < len - 1; j++) {
            if (((niza[j - 1] == 'A') || (niza[j - 1] == 'a')) && (niza[j] == '1') &&
                ((niza[j + 1] == 'c') || (niza[j + 1] == 'C'))) {
                check++;
            }
        }
        if (check > 1) {
            for (j = 0; j < len; j++) {
                printf("%c", tolower(niza[j]));
            }
        }
    }

    return 0;
}
