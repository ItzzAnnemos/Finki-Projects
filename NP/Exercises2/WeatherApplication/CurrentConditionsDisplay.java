package mk.ukim.finki.NP.ZadaciZaVezbanje2.WeatherApplication;

public class CurrentConditionsDisplay implements Display {
    private float temperature;
    private float humidity;
    private WeatherDispatcher weatherDispatcher;

    public CurrentConditionsDisplay(WeatherDispatcher weatherDispatcher) {
        this.weatherDispatcher = weatherDispatcher;
        weatherDispatcher.register(this);
    }

    @Override
    public void update() {
        this.temperature = weatherDispatcher.getTemperature();
        this.humidity = weatherDispatcher.getHumidity();

        System.out.println("Temperature: " + temperature + "F");
        System.out.println("Humidity: " + humidity + "%");
    }

    @Override
    public int getPriority() {
        return 0;
    }
}
