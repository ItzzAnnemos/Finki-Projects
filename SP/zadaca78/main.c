#include <stdio.h>
#include <ctype.h>

struct Student {
    char ime[20];
    char prezime[20];
    int index;
    int points;
};

void norm(char * s) {
    *s = toupper(*s);
    while (*(++s) != '\0') {
        *s = tolower(*s);
    }
}

void sort (struct Student s[],int n) {
    int i,j;
    struct Student temp;
    for (i = 0;i < n;i++) {
        for (j = 0;j < n - i - 1;j++) {
            if (s[j].points < s[j + 1].points) {
                temp = s[j];
                s[j] = s[j + 1];
                s[j + 1] = temp;
            }
        }
    }
}

int main() {
    struct Student studenti[100];
    int n,i,j,temp;

    scanf("%d",&n);
    for (i = 0;i < n;i++) {
        scanf("%s",studenti[i].ime);
        scanf("%s",studenti[i].prezime);
        scanf("%d",&studenti[i].index);

        studenti[i].points = 0;
        for (j = 0;j < 4;j++) {
            scanf("%d",&temp);
            studenti[i].points += temp;
        }

        norm(studenti[i].ime);
        norm(studenti[i].prezime);
    }

    sort(studenti,n);

    for (i = 0;i < n;i++) {
        printf("%s %s %d %d\n",studenti[i].ime, studenti[i].prezime, studenti[i].index, studenti[i].points);
    }

    return 0;
}
