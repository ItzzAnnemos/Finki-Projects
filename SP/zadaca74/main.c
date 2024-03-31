#include <stdio.h>

struct Transaction {
    int id;
    int sum;
    int comm;
    int type;
};

int main() {
    struct Transaction tr[50];
    int n, i, flag = 0;
    scanf("%d", &n);

    for (i = 0; i < n; i++) {
        scanf("%d", &tr[i].id);
        scanf("%d", &tr[i].sum);
        scanf("%d", &tr[i].comm);
        scanf("%d", &tr[i].type);

        if (tr[i].type == 1) {
            printf("%d %d\n", tr[i].id, tr[i].sum + tr[i].comm);
            flag = 1;
        }

    }

    if (!flag) {
        printf("No credit card transaction");
    }
    return 0;
}
