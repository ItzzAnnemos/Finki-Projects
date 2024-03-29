package mk.ukim.finki.NP.ZadaciZaVezbanje.ArchiveStore;

import java.time.LocalDate;

public class Archive {
    private int id;
    private LocalDate date;

    public Archive(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
