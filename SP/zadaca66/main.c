#include <stdio.h>

void printFile() {
    FILE *f = fopen("shema.txt", "r");
    char line[1000];
    while (!feof(f)) {
        fgets(line, 1000, f);
        if (feof(f))
            break;
        printf("%s", line);
    }
    fclose(f);
}

int main() {
    int n = 0, l, i, j,y, broj, num = 1,check;
    int a[20][20];
    FILE *f;

    scanf("%d", &broj);

    for (l = 0; l < 20; l++) {
        if (l * l >= broj) {
            n = l;
            break;
        }
    }

    j = 0;
    check = 0;
    for (i = 0; i < n; i++) {
        if (check == 0) {
            for (j = 0; j < n; j++) {
                if (num <= broj) {
                    a[j][i] = num;
                } else {
                    a[j][i] = 0;
                }
                num++;
            }
            check = !check;
        }
        else if (check) {
            for (y = n - 1; y >= 0; y--,j++) {
                if (num <= broj) {
                    a[y][i] = num;
                } else {
                    a[y][i] = 0;
                }
                num++;
            }
            check = !check;
        }
    }

    f = fopen("shema.txt", "w");

    for (i = 0; i < n; i++) {
        for (j = 0; j < n; j++) {
            fprintf(f, "%d ", a[i][j]);
        }
        fprintf(f, "\n");
    }

    fclose(f);
    // do tuka
    printFile();
    return 0;
}