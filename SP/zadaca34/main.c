#include <stdio.h>

int main() {
    int a1, a2, c1, c2, tockaA1, tockaA2, tockaB1, tockaB2;
    scanf("%d %d\n", &a1, &a2);
    scanf("%d %d\n", &c1, &c2);

    scanf("%d %d\n", &tockaA1, &tockaA2);
    scanf("%d %d\n", &tockaB1, &tockaB2);

    if ((tockaA1 >= a1) && (tockaA1 <= c1)) {
        if ((tockaA2 >= a2) && (tockaA2 <= c2)) {
            printf("DA\n");
        } else
            printf("NE\n");
    } else
        printf("NE\n");
    if ((tockaB1 >= a1) && (tockaB1 <= c1)) {
        if ((tockaB2 >= a2) && (tockaB2 <= c2)) {
            printf("DA\n");
        } else
            printf("NE\n");
    } else
        printf("NE\n");
    return 0;
}
