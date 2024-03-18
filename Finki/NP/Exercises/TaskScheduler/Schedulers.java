package mk.ukim.finki.NP.ZadaciZaVezbanje.TaskScheduler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Schedulers<T> {
    public static <T> TaskScheduler<T> getOrdered() {

        // vashiot kod ovde (annonimous class)
        return new TaskScheduler<T>() {
            @Override
            public List<T> schedule(T[] list) {
                T[] tmp;
                tmp = Arrays.copyOf(list, list.length);
                for (int i = 0; i < tmp.length; i++) {
                    for (int j = 0; j < tmp.length - i - 1; j++) {
                        if (((Task) tmp[j]).getOrder() > ((Task) tmp[j + 1]).getOrder()) {
                            T temp = tmp[j];
                            tmp[j] = tmp[j + 1];
                            tmp[j + 1] = temp;
                        }
                    }
                }
                return new ArrayList<>(Arrays.asList(tmp));
            }
        };
    }

    public static <T> TaskScheduler<T> getFiltered(int order) {

        return list -> {
            List<T> filteredList = new ArrayList<>();
            for (T t : list) {
                if (((Task) t).getOrder() < order) {
                    filteredList.add(t);
                }
            }

            return filteredList;
        };
    }
}
