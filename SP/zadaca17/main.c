#include <stdio.h>

int main() {
    int n,b,zb,j;
    scanf("%d",&n);

    for (int i=1;i<n;i++) {
        b = 0;
        for (int a = 1;a<i;a++) {
            if (i % a == 0) {
                b += i;
            }
        }
        if (zb <= b) {
             zb = b;
            j=i;
        }
    }
    printf("%d",j);
    return 0;
}
