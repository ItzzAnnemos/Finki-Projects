#include <stdio.h>
#include <string.h>

#define MAX 80

void wtf() {
    FILE *f = fopen("podatoci.txt", "w");
    char c;
    while ((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    wtf();

    FILE *f;
    int len, i, start = 0, end;
    char z1, z2, c[MAX], final[MAX], shit;

    if ((f = fopen("podatoci.txt", "r")) == NULL) {
        printf("Cant open the file!");
        return -1;
    }

    scanf("%c", &shit);
    scanf("%c%c", &z1, &z2);

    while ((fgets(c, MAX, f)) != NULL) {
        len = strlen(c);
        start = 0;
        end = len - 1;

        for (i = 0; i < len; i++) {
            if (c[i] == z1) {
                start = i + 1;
            }
            if (c[i] == z2) {
                end = i - 1;
            }
        }
        strncpy(final, c + start, end - start + 1);
        final[end - start + 1] = '\0';
        printf("%s\n", final);
    }

    fclose(f);
    return 0;
}
