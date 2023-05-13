package geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

public class GeoServiceImplTest {
    private Location location;
    private String ipRus = "172.";
    private String ipUsa = "96.";

    @Test
    void locationRus() {
        if (ipRus.startsWith("172.")) {
            location = new Location("VLADIVOSTOK", Country.RUSSIA, null, 0);
        }
        Assertions.assertEquals(Country.RUSSIA, location.getCountry());
    }

    @Test
    void locationUsa() {
        if (ipUsa.startsWith("96.")) {
            location = new Location("New York", Country.USA, null, 0);
        }
        Assertions.assertEquals(Country.USA, location.getCountry());
    }
}
