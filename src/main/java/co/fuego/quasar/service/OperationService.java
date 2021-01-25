package co.fuego.quasar.service;

import co.fuego.quasar.entity.Satellites;
import co.fuego.quasar.exception.QuasarException;
import co.fuego.quasar.model.OperationResult;
import co.fuego.quasar.model.Satellite;
import co.fuego.quasar.model.TopSecretRequest;
import co.fuego.quasar.repository.SatellitesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    LocationService locationService;

    @Autowired
    MessagesService messagesService;

    @Autowired
    SatellitesRepository satellitesRepository;

    public OperationResult topSecret(TopSecretRequest request) throws QuasarException {
        OperationResult result = new OperationResult();

        result.setPosition(locationService.calculatePosition(request));
        result.setMessage(messagesService.getMessage(request.getMessages()));
        return result;

    }


    public Satellite topSecretSplitPost(Satellite request, String satelliteName) throws QuasarException {
        Optional<Satellites> satellitesOptional = satellitesRepository.findById(satelliteName);
        if (satellitesOptional.isPresent()) {
            Satellites satellites = satellitesOptional.get();
            satellites.setDistance(request.getDistance());
            satellites.setMessage(String.join(",", request.getMessage()));
            satellitesRepository.save(satellites);
            return new Satellite(satellites);
        } else {
            throw new QuasarException("No se pudo procesar la información");
        }
    }

    public OperationResult topSecretSplit() throws QuasarException {
        List<Satellites> satellitesList = satellitesRepository.findAll();
        List<Satellite> satelliteList = new ArrayList<>();
        satellitesList.forEach(satellites -> satelliteList.add(new Satellite(satellites)));

        return topSecret(new TopSecretRequest(satelliteList));
    }
}
