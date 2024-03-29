package mk.ukim.finki.NP.ZadaciZaVezbanje.FileSystem;

public interface IFile {
    public String getFileName();

    public long getFileSize();

    public IFile findLargestFile();

    String getFileInfo(String info);

    public void sortBySize();
}
