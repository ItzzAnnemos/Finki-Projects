#include <stdio.h>

float average[10];
int naj[10];

struct Employee {
    char ime[50];
    int embg[15];
    char sector[20];
    int plata;
};

struct Company {
    char ime[50];
    struct Employee vraboteni[50];
    int n;
};

void printEmployeesBellowAverageSalary(struct Company *comp, int n) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < comp[i].n; j++) {
            if ((float) comp[i].vraboteni[j].plata < average[i]) {
                printf("%s %d %s %d\n", comp[i].vraboteni[j].ime, *comp[i].vraboteni[j].embg,
                       comp[i].vraboteni[j].sector,
                       comp[i].vraboteni[j].plata);
            }
        }
    }
}

void printHighestSalaryEmployee(struct Company *comp, int n, const char *sector) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < comp[i].n; j++) {
            if (sector == comp[i].vraboteni[j].sector) {
                if (naj[i] == comp[i].vraboteni[j].plata) {
                    printf("%s %d %s %d\n", comp[i].vraboteni[j].ime, *comp[i].vraboteni[j].embg,
                           comp[i].vraboteni[j].sector,
                           comp[i].vraboteni[j].plata);
                }
            }
        }
    }
}

int main() {
    struct Company comp[50];
    int n, i, j, suma = 0;
    char sector[20];
    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        suma = 0;
        scanf("%s", comp[i].ime);
        scanf("%d", &comp[i].n);
        for (j = 0; j < comp[i].n; j++) {
            scanf("%s %d %s %d", comp[i].vraboteni[j].ime, comp[i].vraboteni[j].embg, comp[i].vraboteni[j].sector,
                  &comp[i].vraboteni[j].plata);
            suma += comp[i].vraboteni[j].plata;
            if (naj[i] < comp[i].vraboteni[j].plata) {
                naj[i] = comp[i].vraboteni[j].plata;
            }
        }
        average[i] = (float) suma / (float) comp[i].n;

    }
    scanf("%s", sector);

    printf("Employees with salaries below the average:\n");
    printEmployeesBellowAverageSalary(comp, n);

    printf("Employees with highest salaries for given department:\n");
    printHighestSalaryEmployee(comp, n, sector);

    return 0;
}
