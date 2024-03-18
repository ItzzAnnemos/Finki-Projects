package mk.ukim.finki.NP.ZadaciZaVezbanje.ArchiveStore;

import java.time.LocalDate;

public class LockedArchive extends Archive {
    private LocalDate dateToOpen;

    public LockedArchive(int id, LocalDate dateToOpen) {
        super(id);
        this.dateToOpen = dateToOpen;
    }

    public LocalDate getDateToOpen() {
        return dateToOpen;
    }
}
