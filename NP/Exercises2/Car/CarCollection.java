package mk.ukim.finki.NP.ZadaciZaVezbanje2.Car;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CarCollection {
    private List<Car> cars;

    public CarCollection() {
        this.cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void sortByPrice(boolean ascending) {
        Comparator<Car> comparator = Comparator.comparing(Car::getPrice).thenComparing(Car::getPower);
        if (!ascending) {
            comparator = comparator.reversed();
        }

        cars.sort(comparator);
    }

    public List<Car> filterByManufacturer(String manufacturer) {
        List<Car> filtered;
        Comparator<Car> comparator = Comparator.comparing(Car::getModel);

        filtered = cars.stream()
                .filter(car -> car.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());

        filtered.sort(comparator);

        return filtered;
    }

    public List<Car> getList() {
        return cars;
    }
}
