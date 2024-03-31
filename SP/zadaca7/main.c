#include <stdio.h>

int main() {
    int z, a, b, j = 0, i = 0;
    float procent = 1;
    scanf("%d\n", &z);
    while (!((a == 0) && (b == 0))) {
        scanf("%d %d", &a, &b);
        j++;
        if ((a + b) == z)
            i++;
    }
    printf("%d%d",i,j);
    procent = (float) (100.00 / (j-1)) * (float) i;
    printf("Vnesovte %d parovi od broevi chij zbir e %d\n", i, z);
    printf("Procentot na parovi so zbir %d e %.2f%%", z, procent);
    return 0;
}
