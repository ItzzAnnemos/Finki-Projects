package mk.ukim.finki.NP.ZadaciZaVezbanje.FileSystem;

public class FileSystem {
    private Folder rootDirectory;

    public FileSystem() {
        rootDirectory = new Folder("root");
    }

    public void addFile(IFile file) throws FileNameExistsException {
        rootDirectory.addFile(file);
    }

    public long findLargestFile() {
        return rootDirectory.findLargestFile().getFileSize();
    }

    public void sortBySize() {
        rootDirectory.sortBySize();
    }

    @Override
    public String toString() {
        return rootDirectory.getFileInfo("");
    }
}
