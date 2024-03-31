#include <stdio.h>

int main() {
    int n,i,j,zbir,najgolem,rez;
    scanf("%d",&n);
    rez=0;
    for (i = 1;i < n;++i) {
        zbir = 0;
        for (j = 2;j < i;++j) {
            if (i % j == 0) {
                zbir+=j;
            }
        }
        if(zbir>najgolem) {
            najgolem = zbir;
            rez = i;
        }
    }
    printf("%d",rez);
}
