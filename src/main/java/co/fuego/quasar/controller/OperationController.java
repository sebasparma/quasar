package co.fuego.quasar.controller;

import co.fuego.quasar.exception.QuasarException;
import co.fuego.quasar.model.OperationResult;
import co.fuego.quasar.model.Satellite;
import co.fuego.quasar.model.TopSecretRequest;
import co.fuego.quasar.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Controlador que provee servicios para el cumplimiento de la Operación fuego de Quasar
 */
@Controller
@RequestMapping("/")
public class OperationController {

    @Autowired
    OperationService operationService;

    /**
     * Nivel 2 de la operación fuego de Quasar
     * @param request
     * @return
     */
    @PostMapping("/topsecret")
    public ResponseEntity<OperationResult> topSecret(@RequestBody TopSecretRequest request) {
        try {
            OperationResult result = operationService.topSecret(request);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (QuasarException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    /**
     * Nivel 3 de la Operación fuego de Quasar, servicio POST
     * @param satelliteName
     * @param request
     * @return
     */
    @PostMapping("/topsecret_split/{satellite_name}")
    public ResponseEntity<Satellite> topSecretSplit(@PathVariable("satellite_name") String satelliteName
            , @RequestBody Satellite request) {
        try {
            Satellite result = operationService.topSecretSplitPost(request, satelliteName);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (QuasarException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Nivel 3 de la Operación fuego de Quasar, servicio GET
     * @return
     */
    @GetMapping("/topsecret_split")
    public ResponseEntity<OperationResult> topSecretSplitGet() {
        OperationResult result;
        try {
            result = operationService.topSecretSplit();
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (QuasarException e) {
            result = new OperationResult();
            result.setMessage("No hay suficiente información");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(result);
        }
    }

}
