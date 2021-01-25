package co.fuego.quasar.service;

import co.fuego.quasar.exception.QuasarException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MessagesServiceTest {


    MessagesService messagesService;

    @Test
    void getMessage() throws QuasarException {
        messagesService = new MessagesService();
        List<String> message1 = Arrays.asList(new String[]{"Este", "", "", "mensaje", "de", ""});
        List<String> message2 = Arrays.asList(new String[]{"", "es", "", "", "", "emergencia"});
        List<String> message3 = Arrays.asList(new String[]{"", "es", "un", "", "de", ""});

        List<List<String>> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        String expected = "Este es un mensaje de emergencia";
        String message = messagesService.getMessage(messages);
        assertEquals(expected, message);
    }

    @Test()
    void getMessageException() throws QuasarException {
        messagesService = new MessagesService();
        List<String> message2 = Arrays.asList(new String[]{"", "es", "", "", "", "emergencia"});
        List<String> message3 = Arrays.asList(new String[]{"", "es", "un", "", "de", ""});

        List<List<String>> messages = new ArrayList<>();
        messages.add(message2);
        messages.add(message3);

        assertThrows(QuasarException.class, () -> {
            messagesService.getMessage(messages);
        });

    }

    @Test()
    void getMessageException2() throws QuasarException {
        messagesService = new MessagesService();
        List<String> message1 = Arrays.asList(new String[]{"Este", "", "", "mensaje"});
        List<String> message2 = Arrays.asList(new String[]{"", "es", "", "", "", "emergencia"});
        List<String> message3 = Arrays.asList(new String[]{"", "es", "un", "", "de", ""});

        List<List<String>> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);
        messages.add(message3);

        assertThrows(QuasarException.class, () -> {
            messagesService.getMessage(messages);
        });

    }
}