package co.fuego.quasar.service;

import co.fuego.quasar.exception.QuasarException;
import co.fuego.quasar.model.Position;
import co.fuego.quasar.model.Satellite;
import co.fuego.quasar.model.TopSecretRequest;
import co.fuego.quasar.service.util.TrilaterationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Objects;

/**
 * Servicio que permite procesar la información de los satélites y obtener la posición de la fuente
 */
@Service
public class LocationService {

    @Autowired
    Environment environment;


    /**
     * Función que procesa la información de los Satélites para encontrar la posición de la fuente
     * @param request Información recibida de los satélites
     * @return Posición de la fuente
     * @throws QuasarException
     */
    public Position calculatePosition(TopSecretRequest request) throws QuasarException {
        if (request.getSatellites().size() != 3) {
            throw new QuasarException("Se deben ingresar las distancias a los tres satélites");
        }
        fillPositions(request);
        double[] distances = request.getDistances();
        double[][] locations = request.getPositions();
        double[] point = TrilaterationUtil.getLocation(locations, distances);
        return new Position(point[0], point[1]);
    }

    private void fillPositions(TopSecretRequest request) {
        for (Satellite sat : request.getSatellites()) {
            String[] strPos = Objects.requireNonNull(environment.getProperty("satellite." + sat.getName())).split(",");
            double[] position = Arrays.stream(strPos).mapToDouble(Double::parseDouble).toArray();
            sat.setPosition(new Position(position[0], position[1]));
        }
    }

}
