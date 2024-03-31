#include <stdio.h>

int main() {
    int n, i, broj, mor;
    scanf("%d", &n);
    if (n < 100000) {
        for (i = 0; i < n; i++) {
            scanf("%d", &broj);
            mor = broj % 5;
            switch (mor) {
                case 0: {
                    printf("-----\n");
                    break;
                }
                case 1: {
                    printf(".----\n");
                    break;
                }
                case 2: {
                    printf("..---\n");
                    break;
                }
                case 3: {
                    printf("...--\n");
                    break;
                }
                case 4: {
                    printf("....-\n");
                    break;
                }
            }

        }
    }
    return 0;
}
