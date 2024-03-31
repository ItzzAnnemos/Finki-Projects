#include <stdio.h>
#include <ctype.h>

#define MAX 80

void cipher(char *word, int x) {
    if (*word == 0) return;
    if (isalpha(*word)) {
        char f = 'a';
        if (isupper(*word))
            f = 'A';
        *word = f + (*word + x - f) % 26;
    }
    cipher(++word, x);
}

int main() {
    int n, x, i;
    char word[MAX];

    scanf("%d %d", &n, &x);

    for (i = 0; i <= n; i++) {
        fgets(word, MAX, stdin);
        if (word[0] == '\n') { continue; }
        cipher(word, x);
        fprintf(stdout, "%s", word);
    }

    return 0;
}
