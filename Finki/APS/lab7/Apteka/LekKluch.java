package mk.ukim.finki.lab7.Apteka;

import java.util.Objects;

public class LekKluch {
    String ime;
    public LekKluch(String ime) {
        this.ime = ime.toUpperCase();
    }

    @Override
    public int hashCode() {
        // TODO implement
        int hash = 0;

        for (int i = 0; i < ime.length(); i++) {
            char character = ime.charAt(i);
            hash = (29 * hash + (int) character) % 102780;
        }

        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LekKluch lekKluch = (LekKluch) o;
        return Objects.equals(ime, lekKluch.ime);
    }
}
