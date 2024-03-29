package mk.ukim.finki.NP.ZadaciZaVezbanje2.FileSystem;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FileSystem {
    private Map<Character, Set<File>> folders;

    public FileSystem() {
        this.folders = new HashMap<>();
    }

    public void addFile(char folder, String name, int size, LocalDateTime createdAt) {
        if (folders.containsKey(folder)) {
            folders.get(folder).add(new File(name, size, createdAt));
        } else {
            folders.put(folder, new LinkedHashSet<>());
            folders.get(folder).add(new File(name, size, createdAt));
        }
    }

    public List<File> findAllHiddenFilesWithSizeLessThen(int size) {
        List<File> list = new ArrayList<>();

        for (Set<File> files : folders.values()) {
            list.addAll(files.stream()
                    .filter(file -> file.getName().startsWith(".") && file.getSize() < size)
                    .sorted()
                    .collect(Collectors.toList()));
        }

        return list;
    }

    public int totalSizeOfFilesFromFolders(List<Character> folders) {
        int sum = 0;
        for (int i = 0;i < folders.size();i++) {
            Character c = folders.get(i);
            if (this.folders.containsKey(c)) {
                for (File file : this.folders.get(c)) {
                    sum += file.getSize();
                }
            }
        }

        return sum;
    }

    public Map<Integer, Set<File>> byYear() {
        Map<Integer, Set<File>> mapByYear = new HashMap<>();
        for (Set<File> files : folders.values()) {
            for (File file : files) {
                int year = file.getCreatedOn().getYear();
                if (mapByYear.containsKey(year)) {
                    mapByYear.get(year).add(file);
                } else {
                    mapByYear.put(year, new HashSet<>());
                    mapByYear.get(year).add(file);
                }
            }
        }

        return mapByYear;
    }

    public Map<String, Long> sizeByMonthAndDay() {
        Map<String, Long> map = new HashMap<>();
        for (Set<File> files : folders.values()) {
            for (File file : files) {
                String monthAndDay = file.getCreatedOn().getMonth().toString() + "-" + file.getCreatedOn().getDayOfMonth();
                if (map.containsKey(monthAndDay)) {
                    map.replace(monthAndDay, map.get(monthAndDay) + (long) file.getSize());
                } else {
                    map.put(monthAndDay, (long) file.getSize());
                }
            }
        }

        return map;
    }
}
