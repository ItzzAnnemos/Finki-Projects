package mk.ukim.finki.NP.ZadaciZaVezbanje.FileSystem;

public class File implements IFile {
    private String name;
    private long size;

    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        return size;
    }

    @Override
    public String getFileInfo(String info) {
        return info + String.format("File name: %10s File size: %10d\n", name, getFileSize());
    }

    @Override
    public IFile findLargestFile() {
        return this;
    }

    @Override
    public void sortBySize() {

    }
}
