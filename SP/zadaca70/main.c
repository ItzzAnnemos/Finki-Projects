#include <stdio.h>
#include <math.h>

#define MAX 50

void printFile() {

    FILE *f = fopen("output.txt", "r");
    char line[1000];
    while (!feof(f)) {
        fgets(line, 1000, f);
        if (feof(f))
            break;
        printf("%s", line);
    }
    fclose(f);
}

void wtf() {
    FILE *f = fopen("input.txt", "w");

    int row, column, i, j;
    float el;
    scanf("%d %d", &row, &column);

    fprintf(f, "%d %d\n", row, column);

    for (i = 0; i < row; ++i) {
        for (j = 0; j < column; ++j) {
            scanf("%f", &el);
            fprintf(f, "%.2f ", el);
        }
        fputc('\n', f);
    }
    fclose(f);

    return;
}

int main() {
    wtf();
    // Your code starts here!
    FILE *f, *out;
    float a[MAX][MAX], b[MAX][MAX];
    int i, j, m, n, r1, k1, r2, k2;
    char nvm;

    if ((f = fopen("input.txt", "r")) == NULL) {
        printf("Cant open the file");
        return -1;
    }

    fscanf(f, "%d %d", &n, &m);
    fscanf(f, "%c", &nvm);

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            fscanf(f, "%f", &a[i][j]);
        }
    }

    fscanf(stdin, "%d %d", &r1, &k1);
    fscanf(stdin, "%d %d", &r2, &k2);

    for (i = r1; i <= r2; i++) {
        for (j = k1; j <= k2; j++) {
            if (i == 0) {
                if (j == 0) {
                    b[i][j] = (a[i][j] + a[i + 1][j] + a[i][j + 1] + a[i + 1][j + 1]) / (float)4;
                } else if (j == m - 1) {
                    b[i][j] = (a[i][j] + a[i][j - 1] + a[i + 1][j - 1] + a[i + 1][j]) / (float)4;
                } else {
                    b[i][j] = (a[i][j] + a[i + 1][j] + a[i][j + 1] + a[i + 1][j + 1] + a[i][j - 1] + a[i + 1][j - 1]) / (float)6;
                }
            } else if (i == n - 1) {
                if (j == 0) {
                    b[i][j] = (a[i][j] + a[i - 1][j] + a[i][j + 1] + a[i - 1][j + 1]) / (float)4;
                } else if (j == m - 1) {
                    b[i][j] = (a[i][j] + a[i][j - 1] + a[i - 1][j - 1] + a[i - 1][j]) / (float)4;
                } else {
                    b[i][j] = (a[i][j] + a[i][j - 1] + a[i - 1][j - 1] + a[i - 1][j] + a[i - 1][j + 1] + a[i][j + 1]) / (float)6;
                }
            } else {
                if (j == 0) {
                    b[i][j] = (a[i][j] + a[i - 1][j] + a[i - 1][j + 1] + a[i][j + 1] + a[i + 1][j] + a[i + 1][j + 1]) / (float)6;
                } else if (j == m - 1) {
                    b[i][j] = (a[i][j] + a[i - 1][j] + a[i - 1][j - 1] + a[i][j - 1] + a[i + 1][j - 1] + a[i + 1][j]) / (float)6;
                } else {
                    b[i][j] = (a[i][j] + a[i - 1][j - 1] + a[i - 1][j] + a[i - 1][j + 1] + a[i][j + 1] + a[i][j - 1] + a[i + 1][j - 1] + a[i + 1][j] + a[i + 1][j + 1]) / (float)9;
                }
            }
        }
    }

    for (i = 0;i < n;i++) {
        for (j = 0;j < m;j++) {
            if ((i >= r1) && (i <= r2)) {
                if ((j >= k1) && (j <= k2)) {
                    a[i][j] = b[i][j];
                }
            }
        }
    }

    out = fopen("output.txt", "w");

    for (i = 0; i < n; i++) {
        for (j = 0; j < m; j++) {
            fprintf(out, "%.2f ", a[i][j]);
        }
        fprintf(out, "\n");
    }

    fclose(f);
    fclose(out);
    // Your code ends here!
    printFile();

    return 0;
}
