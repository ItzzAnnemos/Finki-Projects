#include <stdio.h>

struct Tocka{
    int x;
    int y;
};

struct Krug{
    struct Tocka centar;
    int r;
};

int main() {
    int n, maxIndex = 0;
    float max = 0.0f, radius = 0.0f;
    struct Krug krugovi[100];

    scanf("%d", &n);
    for (int i = 0; i < n;i++) {
        scanf("%d %d %d", &krugovi[i].centar.x, &krugovi[i].centar.y, &krugovi[i].r);
        radius = krugovi[i].r * krugovi[i].r * 3.14;
        if(radius > max){
            max = radius;
            maxIndex = i;
        }
    }
    printf("Largest circle has area of: %.2f with centre: (%d,%d)", max, krugovi[maxIndex].centar.x, krugovi[maxIndex].centar.y);
}