#include <stdio.h>

int main() {
    int a[100][100];
    int n,i,j,naj;
    int dolzina = 1;
    scanf("%d",&n);
    for (i = 0;i < n;i++) {
        for (j = 0;j < n;j++) {
            scanf("%d",&a[i][j]);
        }
    }
    naj = 0;
    for (i = 0;i < n;i++) {
        dolzina = 1;
        for (j = 0;j < n - 1;j++) {
            if (a[i][j] < a[i][j+1]) {
                dolzina++;
            }
        }
        if (dolzina > naj) {
            naj = dolzina;
        }
    }
    printf("%d",naj);
    return 0;
}