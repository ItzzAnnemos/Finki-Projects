#include <stdio.h>
#include <string.h>

// ne menuvaj ovde
void wf() {
    FILE *f = fopen("livce.txt", "w");
    char c;
    while((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int main() {
    wf();

    FILE * f;
    int vlog,tip,tip1,l,i;
    double koef,naj = 0,dobivka,vkupno = 1;
    char c[50],c1[50];
    if ((f = fopen("livce.txt","r")) == NULL) {
        printf("Cant open the file!");
        return -1;
    }

    fscanf(f,"%d",&vlog);
    while((fscanf(f,"%s %d %lf",c,&tip,&koef)) != EOF) {
        vkupno *= koef;
        l = strlen(c);
        if (koef > naj) {
            naj = koef;
            tip1 = tip;
            c1[4] = '\0';
            for (i = 0;i < l;i++) {
                c1[i] = c[i];
            }
        }
    }

    dobivka = vkupno * (float)vlog;
    printf("%s %d %.2lf\n%.2lf",c1,tip1,naj,dobivka);

    fclose(f);
    return 0;
}