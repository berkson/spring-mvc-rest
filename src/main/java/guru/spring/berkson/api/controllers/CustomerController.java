package guru.spring.berkson.api.controllers;

import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {
        return new ResponseEntity<>(
                customerService.getAllCustomers(), HttpStatus.OK);
    }

    @GetMapping("/{firstname}")
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstname) {
        return new ResponseEntity<>(
                customerService.getCustomerByFirstName(firstname), HttpStatus.OK);
    }
}
