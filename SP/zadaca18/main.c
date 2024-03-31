#include <stdio.h>

int main() {
    int br1,br2,cifri = 0,cifri2 = 0;
    scanf("%d",&br1);
    while (br1) {
        cifri++;
        br1/=10;
    }

    while(scanf("%d",&br2)) {
        int tmp = br2;
        while (tmp) {
            cifri2++;
            tmp /= 10;
        }
        if (cifri == cifri2) {
            printf("%d\n",br2);
        }
    }
    return 0;
}
