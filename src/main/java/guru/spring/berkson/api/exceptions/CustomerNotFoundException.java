package guru.spring.berkson.api.exceptions;

/**
 * Created by berkson
 * Date: 21/11/2021
 * Time: 10:05
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {

        super(message, cause);
    }

    public CustomerNotFoundException(Throwable cause) {
        super(cause);
    }
}
