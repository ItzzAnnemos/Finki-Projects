#include <stdio.h>

int main() {
    int i,tiptop = 0;
    for (i = 1; i <= 1000; i++) {

        if ((i % 3 == 0) && (i % 5 == 0)) {
            tiptop = 1;
        }
        else if (i % 3 == 0) {
            tiptop = 2;
        }
        else if (i % 5 == 0) {
            tiptop = 3;
        }
        else {
            tiptop = 0;
        }
        switch (tiptop) {
            case 1:{printf("TipTop\t"); break;}
            case 2:{printf("Tip\t"); break;}
            case 3:{printf("Top\t"); break;}
            default: printf("%d\t",i);
        }

    }
    return 0;
}
