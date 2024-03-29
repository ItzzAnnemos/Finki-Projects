package mk.ukim.finki.lab6.MVR;

public class Gragjanin {
    private String imePrezime;
    private int licna;
    private int pasos;
    private int vozacka;

    public Gragjanin(String imePrezime, int licna, int pasos, int vozacka) {
        this.imePrezime = imePrezime;
        this.licna = licna;
        this.pasos = pasos;
        this.vozacka = vozacka;
    }

    public boolean finished() {
        if (licna == 0 && pasos == 0 && vozacka == 0)
            return true;
        else
            return false;
    }

    public void setLicna(int licna) {
        this.licna = licna;
    }

    public void setPasos(int pasos) {
        this.pasos = pasos;
    }

    public void setVozacka(int vozacka) {
        this.vozacka = vozacka;
    }

    public String getImePrezime() {
        return imePrezime;
    }

    public int getLicna() {
        return licna;
    }

    public int getPasos() {
        return pasos;
    }

    public int getVozacka() {
        return vozacka;
    }
}
