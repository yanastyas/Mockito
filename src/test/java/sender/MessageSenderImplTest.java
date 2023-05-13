package sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {
    private static final String VLADIVOSTOK_IP = "172.0.32.11";
    private static final String NEW_YORK_IP = "96.44.183.149";
    private MessageSenderImpl msi;
    @Mock
    private GeoServiceImpl gsi;
    @Mock
    private LocalizationServiceImpl lsi;

    @BeforeEach
    void setUp() {
        msi = new MessageSenderImpl(gsi, lsi);
    }

    @Test
    void textRus() {
        Mockito.when(gsi.byIp(Mockito.eq(VLADIVOSTOK_IP))).thenReturn(new Location("VLADIVOSTOK", Country.RUSSIA, "Svetlanskaya", 20));
        Mockito.when(lsi.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, VLADIVOSTOK_IP);
        String actualResult = msi.send(map);
        String expectedResult = "Добро пожаловать";
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    void textEng() {
        Mockito.when(gsi.byIp(Mockito.eq(NEW_YORK_IP))).thenReturn(new Location("New York", Country.USA, " One", 52));
        Mockito.when(lsi.locale(Country.USA)).thenReturn("Hi");
        Map<String, String> map = new HashMap<>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, NEW_YORK_IP);
        String actualResult = msi.send(map);
        String expectedResult = "Hi";
        Assertions.assertEquals(expectedResult, actualResult);
    }
}