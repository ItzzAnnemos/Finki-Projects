package mk.ukim.finki.NP.ZadaciZaVezbanje.ArchiveStore;

public class SpecialArchive extends Archive {
    private int maxOpen;

    private int count;

    public SpecialArchive(int id, int maxOpen) {
        super(id);
        this.maxOpen = maxOpen;
        count = 0;
    }

    public int getMaxOpen() {
        return maxOpen;
    }

    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count += 1;
    }
}
