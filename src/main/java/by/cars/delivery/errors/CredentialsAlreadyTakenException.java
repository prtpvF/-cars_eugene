package by.cars.delivery.errors;

public class CredentialsAlreadyTakenException extends RuntimeException {
    public CredentialsAlreadyTakenException(String message) {
        super(message);
    }
}
