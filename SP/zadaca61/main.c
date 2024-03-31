#include <stdio.h>
#define MAX 100

int poramnet(int a) {
    if (a == 0)
        return 0;
    else {
        if (a % 10 == 9)
            return 7 + poramnet(a / 10) * 10;
        else
            return a % 10 + poramnet(a / 10) * 10;
    }
}

int main() {
    int a,j = 0,i,k,tmp;
    int b[MAX];

    while (scanf("%d", &a)) {
        b[j] = poramnet(a);
        j++;
    }
    for (i = 0;i < j;i++) {
        for (k = (i + 1);k < j;k++) {
            if (b[i] > b[k]) {
                tmp = b[i];
                b[i] = b[k];
                b[k] = tmp;
            }
        }
    }

    if (j < 5) {
        for (i = 0;i < j;i++) {
            printf("%d ",b[i]);
        }
    }
    else {
        for (i = 0;i < 5;i++) {
            printf("%d ",b[i]);
        }
    }
    return 0;
}
