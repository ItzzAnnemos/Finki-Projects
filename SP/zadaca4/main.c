#include <stdio.h>

int main() {
    int kWh;
    float cena,suma;
    scanf("%d",&kWh);
    if (kWh > 850) {
        cena = cena + (kWh - 850) * 13.5;
        kWh = kWh - (kWh - 850);
    }
    if (kWh <= 850) {
        cena = cena + (kWh - 650) * 11;
        kWh = kWh - (kWh - 650);
    }
    if (kWh <= 650) {
        cena = cena + (kWh - 500) * 7.5;
        kWh = kWh - (kWh - 500);
    }
    else {
        cena = cena + (kWh - 500) * 5;
    }

    if (cena <= 7000) {
        cena = cena + (cena / 100) * 10;
    }
    else  {
        cena = cena + (cena / 100) * 18;
    }
    printf("%f", cena);
}
