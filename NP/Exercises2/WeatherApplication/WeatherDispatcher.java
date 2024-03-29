package mk.ukim.finki.NP.ZadaciZaVezbanje2.WeatherApplication;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeatherDispatcher {
    private float temperature;
    private float humidity;
    private float pressure;
    private List<Display> displays;

    public WeatherDispatcher() {
        this.displays = new ArrayList<>();
    }

    public float getTemperature() {
        return temperature;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;

        notifyDisplays();
        System.out.println();
    }

    public void register(Display display) {
        if (!displays.contains(display)) {
            displays.add(display);
        }
    }

    public void remove(Display display) {
        displays.remove(display);
    }

    private void notifyDisplays() {
        displays.stream()
                .sorted(Comparator.comparing(Display::getPriority))
                .forEach(Display::update);
    }
}
