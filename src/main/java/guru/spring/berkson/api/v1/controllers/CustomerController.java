package guru.spring.berkson.api.v1.controllers;

import guru.spring.berkson.api.exceptions.CustomerNotFoundException;
import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.api.v1.model.CustomersDTO;
import guru.spring.berkson.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Berkson Ximenes
 * Date: 18/11/2021
 * Time: 23:12
 */
@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomersDTO> getAllCustomers() {
        return new ResponseEntity<>(new CustomersDTO(customerService.getAllCustomers())
                , HttpStatus.OK);
    }

    @GetMapping(value = "/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstname) {
        return new ResponseEntity<>(
                customerService.getCustomerByFirstname(firstname), HttpStatus.OK);
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        try {
            return customerService.getCustomerById(id);
        } catch (Exception e) {
            throw new CustomerNotFoundException(id);
        }
    }
}
