package mk.ukim.finki.NP.ZadaciZaVezbanje2.StopCorona;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public interface ILocation{
    double getLongitude();

    double getLatitude();

    LocalDateTime getTimestamp();
}
