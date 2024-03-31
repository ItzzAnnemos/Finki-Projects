#include <stdio.h>

int main() {
    int a[100][100];
    int i,j,n;

    scanf("%d",&n);
    for (i=0;i<n;i++){
        for (j=0;j<n;j++) {
            scanf("%d",&a[i][j]);
        }
    }

    for (i=0;i<n;i++) {
        for (j=0;j<n;j++) {
            if (i == j) {
                a[i][j] = a[i][j] * -1;
            }
        }
    }

    for (i=0;i<n;i++) {
        for (j = 0; j < n; j++) {
            printf("%3d ", a[i][j]);
        }
        printf("\n");
    }
    return 0;
}
