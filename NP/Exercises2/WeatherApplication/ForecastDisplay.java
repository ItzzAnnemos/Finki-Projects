package mk.ukim.finki.NP.ZadaciZaVezbanje2.WeatherApplication;

public class ForecastDisplay implements Display {
    private float previousPressure = 0.0f;
    private String forecast;

    private WeatherDispatcher weatherDispatcher;

    public ForecastDisplay(WeatherDispatcher weatherDispatcher) {
        this.weatherDispatcher = weatherDispatcher;
        weatherDispatcher.register(this);
    }

    @Override
    public void update() {
        float pressure = weatherDispatcher.getPressure();
        if (pressure > previousPressure) {
            forecast = "Improving";
        } else if (pressure < previousPressure) {
            forecast = "Cooler";
        } else {
            forecast = "Same";
        }

        previousPressure = pressure;

        System.out.println("Forecast: " + forecast);
    }

    @Override
    public int getPriority() {
        return 1;
    }
}
