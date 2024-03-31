#include <stdio.h>

int main() {
    int m, n;
    scanf("%d%d", &m, &n);

    double matrix[100][100];

    for (int i=0;i<m;i++){
        for (int j=0;j<n;j++){
            scanf("%lf", &matrix[i][j]);
        }
    }

    for (int j=0;j<n;j++){
        double min,max;
        min = max = matrix[0][j];
        for (int i=0;i<m;i++){
            if (matrix[i][j]<min){
                min = matrix[i][j];
            }
            if (matrix[i][j]>max){
                max = matrix[i][j];
            }
        }

        for (int i=0;i<m;i++){
            matrix[i][j] = (matrix[i][j]-min)/(max-min);
        }
    }

    for (int i=0;i<m;i++){
        for (int j=0;j<n;j++){
            printf("%.2lf ", matrix[i][j]);
        }
        printf("\n");
    }

    return 0;
}