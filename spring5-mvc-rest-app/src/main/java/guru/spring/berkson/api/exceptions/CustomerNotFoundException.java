package guru.spring.berkson.api.exceptions;

/**
 * Created by berkson
 * Date: 21/11/2021
 * Time: 10:05
 */
public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(Long id){
        super(String.format("Customer id: %d, not found!", id));
    }
}
