package mk.ukim.finki.lab7.Apteka;

public class Lek {
    String ime;
    int pozLista;
    int cena;
    int kolicina;

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getCena() {
        return cena;
    }

    public void setCena(int cena) {
        this.cena = cena;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public int getPozLista() {
        return pozLista;
    }

    public Lek(String ime, int pozLista, int cena, int kolicina) {
        this.ime = ime.toUpperCase();
        this.pozLista = pozLista;
        this.cena = cena;
        this.kolicina = kolicina;
    }

    @Override
    public String toString() {
        if (pozLista == 1) return ime + "\n" + "POZ\n" + cena + "\n" + kolicina;
        else return ime + "\n" + "NEG\n" + cena + "\n" + kolicina;
    }
}
