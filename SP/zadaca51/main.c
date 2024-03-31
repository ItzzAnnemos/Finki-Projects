#include <stdio.h>
#include <ctype.h>
#include <string.h>

void writeToFile() {
    FILE *f = fopen("text.txt", "w");
    char c;
    while ((c = getchar()) != '#') {
        fputc(c, f);
    }
    fclose(f);
}

int e_samoglaska(char c) {
    switch (c) {
        case 'a':
        case 'e':
        case 'i':
        case 'o':
        case 'u':
        case 'A':
        case 'E':
        case 'I':
        case 'O':
        case 'U':
            return 1;
    }
    return 0;
}

int main() {

    writeToFile();

    FILE *dat;
    char z, w;
    int naj = 0;
    if ((dat = fopen("text.txt", "r")) == NULL) {
        printf("Cant open the file %s\n", "text.txt");
        return -1;
    }

    while (!feof(dat)) {
        w = fgetc(dat);
        if (isalpha(w) && isalpha(z)) {
            if (e_samoglaska(w) && e_samoglaska(z)) {
                printf("%c%c\n", tolower(z), tolower(w));
                naj++;
            }
        }
        z = w;
    }

    printf("%d", naj);

    fclose(dat);

    return 0;
}
