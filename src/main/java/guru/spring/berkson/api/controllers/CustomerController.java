package guru.spring.berkson.api.controllers;

import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.api.v1.model.CustomersDTO;
import guru.spring.berkson.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
