#include <stdio.h>

int main() {
    int n,i,index,cif,j1=0,j2=0,j3=0;
    int gr1[100],gr2[100],gr3[125];
    scanf("%d",&n);

    for (i=0;i<n;i++) {
        scanf("%d",&index);
        cif = index % 10;
        switch (cif) {
            case 0:
            case 1:
            case 2: gr1[j1] = index; j1++; break;
            case 3:
            case 4:
            case 5: gr2[j2] = index; j2++; break;
            case 6:
            case 7:
            case 8:
            case 9: gr3[j3] = index; j3++; break;
        }

    }
    printf("Grupa 1\n");
    for(i=0;i<j1;i++) {
        printf("%d ",gr1[i]);
    }
    printf("\n");
    printf("Grupa 2\n");
    for(i=0;i<j2;i++) {
        printf("%d ",gr2[i]);
    }
    printf("\n");
    printf("Grupa 3\n");
    for(i=0;i<j3;i++) {
        printf("%d ",gr3[i]);
    }
    printf("\n");
    return 0;
}
