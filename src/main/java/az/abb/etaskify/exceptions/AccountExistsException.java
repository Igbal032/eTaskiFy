package az.abb.etaskify.exceptions;


public class AccountExistsException extends RuntimeException{
    public AccountExistsException(String message) {
        super(message);
    }
}
