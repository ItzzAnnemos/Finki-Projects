package mk.ukim.finki.NP.ZadaciZaVezbanje.FileSystem;

public class FileNameExistsException extends Exception {

    public FileNameExistsException(String name, String folder) {
        super("There is already a file named " + name + " in the folder " + folder);
    }
}
