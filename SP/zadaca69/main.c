#include <stdio.h>

#define MAX 100

int final = 0, tmp;

void swap(int *x, int *y) {
    tmp = *x;
    *x = *y;
    *y = tmp;
}

void bubble_sort(int a[], int n) {
    int i, j;
    for (i = 0; i < n; i++) {
        for (j = 0; j < n - i - 1; j++) {
            if (a[j] > a[j + 1]) {
                swap(&a[j], &a[j + 1]);
            }
        }
    }
}

int par(int a[], int n) {
    int i, j, temp, count;
    for (i = 0; i < n - 1; i++) {
        count = 1;
        if (a[i] == temp) continue;
        j = i;
        while (a[j] == a[j + 1]) {
            count++;
            j++;
            temp = a[j];
        }
        if ((count % 2 == 0) && (count != 0)) {
            final = a[i];
            break;
        }
    }
    if (final) return 1;
    else return 0;
}

int main() {
    int n, i;
    int a[MAX];

    scanf("%d", &n);
    for (i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }

    bubble_sort(a, n);

    if (par(a, n)) {
        printf("Najmaliot element koj se pojavuva paren broj pati e %d", final);
    } else {
        printf("Nitu eden element ne se pojavuva paren broj pati!");
    }

    return 0;
}