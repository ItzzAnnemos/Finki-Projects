package mk.ukim.finki.NP.ZadaciZaVezbanje.ArchiveStore;

import java.time.LocalDate;
import java.util.ArrayList;

public class ArchiveStore {
    ArrayList<Archive> archives;
    ArrayList<String> logs;

    public ArchiveStore() {
        this.archives = new ArrayList<>();
        this.logs = new ArrayList<>();
    }

    public void archiveItem(Archive item, LocalDate date) {
        archives.add(item);
        archives.get(archives.size() - 1).setDate(date);
        String tmp = "Item " + item.getId() + " archived at " + date;
        logs.add(tmp);
    }

    public void openItem(int id, LocalDate date) throws NonExistingItemException {
        Archive tmp = null;
        for (int i = 0; i < archives.size(); i++) {
            if (archives.get(i).getId() == id) {
                tmp = archives.get(i);
            }
        }

        if (tmp == null) {
            throw new NonExistingItemException(id);
        }

        if (tmp instanceof LockedArchive) {
            if (date.isBefore(((LockedArchive) tmp).getDateToOpen())) {
                String temp = "Item " + id + " cannot be opened before " + ((LockedArchive) tmp).getDateToOpen();
                logs.add(temp);
            } else {
                String temp = "Item " + id + " opened at " + date;
                logs.add(temp);
            }
        } else if (tmp instanceof SpecialArchive) {
            if (((SpecialArchive) tmp).getCount() < ((SpecialArchive) tmp).getMaxOpen()) {
                String temp = "Item " + id + " opened at " + date;
                logs.add(temp);
                ((SpecialArchive) tmp).setCount();
            } else {
                String temp = "Item " + id + " cannot be opened more than " + ((SpecialArchive) tmp).getMaxOpen() + " times";
                logs.add(temp);
            }
        }
    }

    public String getLog() {
        String rez = "";
        for (int i = 0;i < logs.size();i++) {
            rez += logs.get(i);
            rez += "\n";
        }

        return rez;
    }
}
