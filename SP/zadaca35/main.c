#include <stdio.h>

int main() {
    int cas, min, sec;
    scanf("%d", &cas);
    scanf("%d", &min);
    scanf("%d", &sec);

    if (cas == 0) {
        printf("%02d:%02d:%02dAM", cas + 12, min, sec);
    } else if (cas < 12) {
        printf("%02d:%02d:%02dAM", cas, min, sec);
    } else if (cas > 12) {
        printf("%02d:%02d:%02dPM", cas - 12, min, sec);
    } else {
        printf("%02d:%02d:%02dNOON", cas, min, sec);
    }

    return 0;
}
