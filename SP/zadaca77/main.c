#include <stdio.h>

struct Date {
    int day;
    int month;
    int year;
};

int compare(struct Date d1, struct Date d2) {
    if (d1.year > d2.year) {
        return 1;
    } else if (d2.year > d1.year) {
        return 2;
    } else {
        if (d1.month > d2.month) {
            return 1;
        } else if (d2.month > d1.month) {
            return 2;
        } else {
            if (d1.day > d2.day) {
                return 1;
            } else if (d2.day > d1.day) {
                return 2;
            } else {
                return 0;
            }
        }
    }
}

int razlika(struct Date d1, struct Date d2) {
    int razlika = d1.day - d2.day;
    razlika += (d1.month - d2.month) * 30;
    razlika += (d1.year - d2.year) * 365;
    return razlika;

}

int main() {
    struct Date d1;
    struct Date d2;
    int check;

    scanf("%d.%d.%d", &d1.day, &d1.month, &d1.year);
    scanf("%d.%d.%d", &d2.day, &d2.month, &d2.year);

    check = compare(d1,d2);
    if (check == 1) {
        printf("%d",razlika(d1,d2));
    } else if (check == 2) {
        printf("%d",razlika(d2,d1));
    } else {
        printf("Datumite se isti!");
    }

    return 0;
}
