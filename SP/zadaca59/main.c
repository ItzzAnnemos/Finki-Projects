#include <stdio.h>
#define MAX 100

int main() {
    int a[MAX][MAX];
    int x,n,m,i,j,suma;
    scanf("%d",&x);
    scanf("%d %d",&n,&m);

    for (i = 0;i < n;i++) {
        for (j = 0;j < m;j++) {
            scanf("%d",&a[i][j]);
        }
    }

    for (i = 0;i < n;i++) {
        suma = 0;
        for (j = 0;j < m;j++) {
            suma += a[i][j];
        }
        for (j = 0;j < m;j++) {
            if (suma > x) {
                a[i][j] = 1;
            } else if (suma < x) {
                a[i][j] = -1;
            } else {
                a[i][j] = 0;
            }
        }
    }

    for (i = 0;i < n;i++) {
        for (j = 0;j < m;j++) {
            printf("%d ",a[i][j]);
        }
        printf("\n");
    }

    return 0;
}
