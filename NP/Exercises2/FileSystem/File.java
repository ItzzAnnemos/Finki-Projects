package mk.ukim.finki.NP.ZadaciZaVezbanje2.FileSystem;

import java.time.LocalDateTime;

public class File implements Comparable<File> {
    private String name;
    private int size;
    private LocalDateTime createdOn;

    public File(String name, int size, LocalDateTime createdOn) {
        this.name = name;
        this.size = size;
        this.createdOn = createdOn;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    @Override
    public String toString() {
        return String.format("%-10s %5dB %s", name, size, createdOn.toString());
    }

    @Override
    public int compareTo(File other) {
        int dateComparison = this.createdOn.compareTo(other.createdOn);
        if (dateComparison != 0) {
            return dateComparison;
        }

        int nameComparison = this.name.compareTo(other.name);
        if (nameComparison != 0) {
            return nameComparison;
        }

        return Integer.compare(this.size, other.size);
    }
}
