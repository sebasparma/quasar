package co.fuego.quasar.service;

import co.fuego.quasar.exception.QuasarException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Servicio que permite procesar la información de los satélites y obtener el mensaje
 */
@Service
public class MessagesService {

    /**
     * Procesa los mensajes recibidos de cada satélite
     * @param messages Mensajes recibidos
     * @return Mensaje compilado
     * @throws QuasarException
     */
    public String getMessage(List<List<String>> messages) throws QuasarException {
        if (messages.size() != 3)
            throw new QuasarException("Son necesarios al menos tres mensajes");

        if (!haveSameSize(messages))
            throw new QuasarException("Los mensajes deben tener el mismo tamaño");

        return compileMessage(messages);
    }

    private boolean haveSameSize(List<List<String>> messages) {
        int size = messages.get(0).size();
        if (size == 0)
            return false;
        for (List<String> message : messages) {
            if (message.size() != size)
                return false;
        }
        return true;
    }

    private String compileMessage(List<List<String>> messages) {
        List<String> compiledMessage = new ArrayList<>();
        for (List<String> message : messages) {
            for (int i = 0; i < message.size(); i++) {
                if (compiledMessage.size() <= i)
                    compiledMessage.add("");
                if (!"".equals(message.get(i))) {
                    compiledMessage.set(i, message.get(i));
                }
            }
        }

        return String.join(" ", compiledMessage);
    }

}
