#include <stdio.h>

int main() {
    int a[1000], pol[1000], pad[1000];
    int n, i, j = 0, l = 0,q = 0,w = 0,sumapad = 0, sumapol = 0;
    float svpol,svpad;
    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        scanf("%d", &a[i]);
        if (a[i] < 50) {
            sumapad += a[i];
            pad[j] = a[i];
            j++;
        }
        if (a[i] >= 50) {
            sumapol += a[i];
            pol[l] = a[i];
            l++;
        }
    }
    svpad = (float)sumapad / (float)j;
    svpol = (float)sumapol / (float)l;
    printf("Sredna vrednost na padnati %.2f\n",svpad);
    for(w=0;w<j;w++) {
        printf("%d ",pad[w]);
    }
    printf("\n");
    printf("Sredna vrednost na polozeni %.2f\n",svpol);
    for(q=0;q<l;q++) {
        printf("%d ",pol[q]);
    }
    printf("\n");


    return 0;
}
