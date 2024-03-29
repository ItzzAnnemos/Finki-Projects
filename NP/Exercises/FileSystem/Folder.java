package mk.ukim.finki.NP.ZadaciZaVezbanje.FileSystem;

import java.util.ArrayList;
import java.util.Comparator;

public class Folder implements IFile {
    private String name;
    private long size;
    private ArrayList<IFile> files;

    public Folder(String name) {
        this.name = name;
        this.files = new ArrayList<>();
    }

    public void addFile(IFile file) throws FileNameExistsException {
        for (IFile iFile : files) {
            if (iFile.getFileName().equals(file.getFileName())) {
                throw new FileNameExistsException(file.getFileName(), name);
            }
        }
        files.add(file);
    }

    @Override
    public String getFileName() {
        return name;
    }

    @Override
    public long getFileSize() {
        long sum = 0;
        for (IFile file : files) {
            sum += file.getFileSize();
        }
        return sum;
    }

    @Override
    public String getFileInfo(String info) {
        String rez = info + String.format("Folder name: %10s Folder size: %10d\n", name, getFileSize());

        for (IFile file : files) {
            rez += file.getFileInfo(info + "    ");
        }
        return rez;
    }

    @Override
    public void sortBySize() {
        Comparator<IFile> comparator = Comparator.comparing(IFile::getFileSize);
        files.sort(comparator);
        files.forEach(IFile::sortBySize);
    }

    @Override
    public IFile findLargestFile() {
        IFile largest = new File("", 0);
        long max = 0;
        for (int i = 0;i < files.size();i++) {
            if (files.get(i) instanceof Folder) {
                largest = files.get(i).findLargestFile();
                max = largest.getFileSize();
            } else {
                if (files.get(i).getFileSize() > max) {
                    largest = files.get(i);
                    max = files.get(i).getFileSize();
                }
            }
        }

        return largest;
    }
}
