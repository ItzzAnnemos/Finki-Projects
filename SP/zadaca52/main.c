#include <stdio.h>
#include <string.h>

#define MAX 100

//ne menuvaj!
void wtf() {
    FILE *f = fopen("broevi.txt", "w");
    char c;
    while ((c = getchar()) != EOF) {
        fputc(c, f);
    }
    fclose(f);
}

int MSG(int n) {
    while(n >= 10) {
        n /=10;
    }
    return n;
}

int main() {
    wtf();

    int i,n,x,tmp,max,maxN;
    FILE *f;
    if ((f = fopen("broevi.txt", "r")) == NULL) {
        printf("Cant open the file!");
        return -1;
    }

    while (!feof(f)) {
        fscanf(f,"%d",&n);
        if (n == 0) break;
        max = 0;
        maxN = 0;
        for (i = 0;i < n;i++) {
            fscanf(f,"%d",&x);
            tmp = MSG(x);
            if (tmp > max) {
                max = tmp;
                maxN = x;
            }
        }
        printf("%d\n",maxN);
    }
    fclose(f);
}