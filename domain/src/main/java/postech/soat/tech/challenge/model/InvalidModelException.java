package postech.soat.tech.challenge.model;

public class InvalidModelException extends RuntimeException {
    public InvalidModelException(String className, String message) {
        super("Invalid property on" + className + ": " + message);
    }
}
