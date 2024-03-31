#include <stdio.h>

float f(int * a,int start, int n) {
    int s = start;
    if (start == (n-1)) {
        return a[s];
    } else return a[s]+ ((float)1 / f(a, ++start, n));
}

int main() {
    int a[100];
    int n, i,start;

    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }

    start = 0;
    printf("%f",f(a,start,n));

    return 0;
}
