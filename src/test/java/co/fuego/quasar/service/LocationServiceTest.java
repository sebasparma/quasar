package co.fuego.quasar.service;

import co.fuego.quasar.exception.QuasarException;
import co.fuego.quasar.model.Position;
import co.fuego.quasar.model.Satellite;
import co.fuego.quasar.model.TopSecretRequest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
class LocationServiceTest {

    @Mock
    Environment environment;

    @InjectMocks
    LocationService locationService;



    @Test
    void calculatePosition() throws QuasarException {

        MockitoAnnotations.initMocks(this);
        Satellite kenobi = new Satellite();
        Satellite skywalker = new Satellite();
        Satellite sato = new Satellite();
        List<String> message1 = Arrays.asList(new String[]{"Este", "", "", "mensaje", "de", ""});
        List<String> message2 = Arrays.asList(new String[]{"", "es", "", "", "", "emergencia"});
        List<String> message3 = Arrays.asList(new String[]{"", "es", "un", "", "de", ""});

        kenobi.setName("kenobi");
        kenobi.setDistance(100);
        kenobi.setMessage(message1);

        skywalker.setName("skywalker");
        skywalker.setDistance(115.5);
        skywalker.setMessage(message2);

        sato.setName("sato");
        sato.setDistance(142.7);
        sato.setMessage(message3);

        List<Satellite> satellites = new ArrayList<>();
        satellites.add(kenobi);
        satellites.add(skywalker);
        satellites.add(sato);
        
        TopSecretRequest request = new TopSecretRequest(satellites);

        Mockito.when(environment.getProperty(Mockito.eq("satellite.kenobi"))).thenReturn("-500,-200");
        Mockito.when(environment.getProperty(Mockito.eq("satellite.skywalker"))).thenReturn("100,-100");
        Mockito.when(environment.getProperty(Mockito.eq("satellite.sato"))).thenReturn("500,100");

        assertNotNull(locationService.calculatePosition(request));
    }
}