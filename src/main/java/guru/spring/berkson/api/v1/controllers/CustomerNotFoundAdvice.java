package guru.spring.berkson.api.v1.controllers;

import guru.spring.berkson.api.exceptions.CustomerNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by berkson
 * Date: 21/11/2021
 * Time: 10:21
 */
@RestControllerAdvice
public class CustomerNotFoundAdvice {

    @ExceptionHandler(CustomerNotFoundException.class)
    ResponseEntity<String> customerNotFoundHandler(CustomerNotFoundException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
