package mk.ukim.finki.NP.ZadaciZaVezbanje.LogSystem;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LogSystem<T extends ILog> {
    private ArrayList<T> logsList;

    public LogSystem(ArrayList<T> elements) {
        this.logsList = new ArrayList<>();
        this.logsList.addAll(elements);
    }

    void printResults() {

        //TODO define concrete log processors with lambda expressions
        LogProcessor<T> firstLogProcessor = logsList -> {
            String target = "INFO";
            return logsList.stream()
                    .filter(log -> target.equals(log.getType()))
                    .collect(Collectors.toCollection(ArrayList::new));
        };

        LogProcessor<T> secondLogProcessor = logsList -> {
            int max = 100;
            return logsList.stream()
                    .filter(log -> log.getMessage().length() < max)
                    .collect(Collectors.toCollection(ArrayList::new));
        };

        LogProcessor<T> thirdLogProcessor = logsList -> logsList.stream()
                .sorted(Comparator.comparingLong(ILog::getTimestamp))
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println("RESULTS FROM THE FIRST LOG PROCESSOR");
        firstLogProcessor.processLogs(logsList).forEach(l -> System.out.println(l.toString()));

        System.out.println("RESULTS FROM THE SECOND LOG PROCESSOR");
        secondLogProcessor.processLogs(logsList).forEach(l -> System.out.println(l.toString()));

        System.out.println("RESULTS FROM THE THIRD LOG PROCESSOR");
        thirdLogProcessor.processLogs(logsList).forEach(l -> System.out.println(l.toString()));
    }
}
