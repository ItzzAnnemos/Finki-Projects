#include <stdio.h>

#define MAX 100

void printFile() {
    FILE *f = fopen("matrica.txt", "r");
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
    //Vasiot kod tuka
    FILE *f;
    int n, m, i, j, r, k, min = 99999;
    int Anxm[MAX][MAX];
    scanf("%d %d", &n, &m);
    scanf("%d %d", &r, &k);

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            scanf("%d", &Anxm[i][j]);
            if (Anxm[i][j] < min) {
                min = Anxm[i][j];
            }
        }
    }


    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            if ((i < r) && (j < k)) {
                Anxm[i][j] = min;
            }
        }
    }

    f = fopen("matrica.txt", "w");

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            fprintf(f, "%d ", Anxm[i][j]);
        }
        fprintf(f, "\n");
    }

    fclose(f);
    // do tuka
    printFile();
    return 0;
}