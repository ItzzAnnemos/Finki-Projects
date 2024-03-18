package mk.ukim.finki.NP.ZadaciZaVezbanje2.TasksManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.*;

public class TaskManager {
    private Map<String, List<Task>> tasks;

    public TaskManager() {
        this.tasks = new TreeMap<>();
    }

    public void readTasks(InputStream inputStream) {
        Scanner sc = new Scanner(inputStream);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(",");

            String category = parts[0];

            if (!tasks.containsKey(category)) {
                tasks.put(category, new ArrayList<>());
            }

            if (parts.length == 3) {
                tasks.get(category).add(new Task(parts[1], parts[2]));
            } else if (parts.length == 5) {
                try {
                    tasks.get(category).add(new TaskWithPriorityAndDeadline(parts[1], parts[2], LocalDateTime.parse(parts[3]), Integer.parseInt(parts[4])));
                } catch (DeadlineNotValidException e) {
                    System.out.println(e.getMessage());
                }
            } else if (parts.length == 4 && parts[3].length() > 1) {
                try {
                    tasks.get(category).add(new TaskWithDeadline(parts[1], parts[2], LocalDateTime.parse(parts[3])));
                } catch (DeadlineNotValidException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                tasks.get(category).add(new TaskWithPriority(parts[1], parts[2], Integer.parseInt(parts[3])));
            }
        }
    }

    void printTasks(OutputStream os, boolean includePriority, boolean includeCategory) {
        PrintWriter pw = new PrintWriter(os);
        if (includeCategory) {
            if (includePriority) {
                tasks.entrySet()
                        .forEach(entry -> {
                            pw.println(entry.getKey().toUpperCase());
                            entry.getValue().stream()
                                    .sorted(Comparator.comparing(Task::getPriority)
                                            .thenComparing(Comparator.nullsLast(Comparator.comparing(Task::getTimeUntilNow))))
                                    .forEach(pw::println);

                        });
            } else {
                tasks.entrySet()
                        .forEach(entry -> {
                            pw.println(entry.getKey().toUpperCase());
                            entry.getValue().stream()
                                    .sorted(Comparator.nullsLast(Comparator.comparing(Task::getTimeUntilNow)))
                                    .forEach(pw::println);

                        });
            }
        } else {
            if (includePriority) {
                tasks.values().stream()
                        .flatMap(Collection::stream)
                        .sorted(Comparator.comparing(Task::getPriority)
                                .thenComparing(Comparator.nullsLast(Comparator.comparing(Task::getTimeUntilNow))))
                        .forEach(pw::println);
            } else {
                tasks.values().stream()
                        .flatMap(Collection::stream)
                        .sorted(Comparator.nullsLast(Comparator.comparing(Task::getTimeUntilNow)))
                        .forEach(pw::println);
            }
        }

        pw.flush();
    }
}
