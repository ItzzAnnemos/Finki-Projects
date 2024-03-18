package mk.ukim.finki.NP.ZadaciZaVezbanje.WeatherStation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Weather {
    private float temp;
    private float wind;
    private float humidity;
    private float visibility;
    private Date date;

    public Weather(float temp, float wind, float humidity, float visibility, Date date) {
        this.temp = temp;
        this.wind = wind;
        this.humidity = humidity;
        this.visibility = visibility;
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public float getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        return (String.format("%.1f %.1f km/h %.1f%% %.1f km ", temp, wind, humidity, visibility) + df.format(date));
    }
}
