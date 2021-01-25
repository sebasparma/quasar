package co.fuego.quasar.exception;

public class QuasarException extends Exception {

    public QuasarException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuasarException(String message) {
        super(message);
    }
}
