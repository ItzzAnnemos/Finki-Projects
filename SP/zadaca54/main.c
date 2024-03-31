#include <stdio.h>
#include <string.h>
#include <ctype.h>

#define MAX 100

// ne menuvaj ovde
void wtf() {
    FILE *f = fopen("dat.txt", "w");
    char c;
    while ((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    wtf();

    FILE *f;
    char c[MAX], c1[MAX], final[MAX];
    int len, naj = 0, i, count, start = 0, end = 0, flag,start1 = 0,end1 = 0;
    if ((f = fopen("dat.txt", "r")) == NULL) {
        printf("Cant open the file!");
        return -1;
    }

    while (fgets(c, MAX, f) != NULL) {
        len = strlen(c);
        count = 0;
        flag = 0;
        end = 0;
        if (len >= naj) {
            naj = len;
            for (i = 0; i < len; i++) {
                if (isdigit(c[i])) {
                    count++;
                    if (!flag) {
                        start = i;
                        flag = 1;
                    }
                    end = i;
                }
            }
            if (start!=end) {
                strcpy(c1,c);
                start1 = start;
                end1 = end;
            }
        }
    }

    strncpy(final, c1 + start1, end1 - start1 + 1);
    final[end1 - start1 + 1] = '\0';
    printf("%s", final);
    fclose(f);
}