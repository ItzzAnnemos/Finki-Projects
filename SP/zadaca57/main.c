#include <stdio.h>
#include <ctype.h>
#include <string.h>

#define MAX 100

void wtf() {
    FILE *f = fopen("input.txt", "w");
    char c;
    while ((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}

int swap(char *a, char *b) {
    char tmp;

    tmp = *a;
    *a = *b;
    *b = tmp;

    return a && b;
}

void bubble_sort(char *a, int n) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n - i - 1; j++) {
            if (a[j] > a[j + 1]) {
                swap(&a[j], &a[j + 1]);
            }
        }
    }
}

int main() {
    wtf();

    char c[MAX], final[MAX];
    int i, j, len, count;
    FILE *f;
    if ((f = fopen("input.txt", "r")) == NULL) {
        printf("Cant open the file!");
        return -1;
    }

    while (fgets(c, MAX, f) != NULL) {
        len = strlen(c);
        count = 0;
        j = 0;
        for (i = 0; i < len; i++) {
            if (isdigit(c[i])) {
                count++;
                final[j] = c[i];
                j++;
            }
        }
        bubble_sort(final,j);
        printf("%d:%s\n", count, final);
        memset(final, '\0',sizeof(final));
    }

    fclose(f);
    return 0;
}
