package mk.ukim.finki.NP.ZadaciZaVezbanje.ArchiveStore;

public class NonExistingItemException extends Exception {

    public NonExistingItemException(int id) {
        super("Item with id " + id + " doesn't exist");
    }
}
