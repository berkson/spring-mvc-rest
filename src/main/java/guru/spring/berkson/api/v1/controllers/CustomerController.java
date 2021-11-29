package guru.spring.berkson.api.v1.controllers;

import guru.spring.berkson.api.exceptions.CustomerNotFoundException;
import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.api.v1.model.CustomersDTO;
import guru.spring.berkson.config.SwaggerConfig;
import guru.spring.berkson.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping(CustomerController.BASE_URL)
@Api(tags = {SwaggerConfig.CUSTOMER_TAG}, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {

    public static final String BASE_URL = "/api/v1/customers";
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ApiOperation(value = "Retorna uma lista de clientes", notes = "sem parâmetros")
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomersDTO> getAllCustomers() {
        return new ResponseEntity<>(new CustomersDTO(customerService.getAllCustomers())
                , HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um cliente", notes = "usa o primeiro nome")
    @GetMapping(value = "/{firstname}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> getCustomerByFirstName(@PathVariable String firstname) {
        return new ResponseEntity<>(
                customerService.getCustomerByFirstname(firstname), HttpStatus.OK);
    }

    @ApiOperation(value = "Retorna um cliente buscando usando o id")
    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getCustomerById(@PathVariable Long id) {
        try {
            return customerService.getCustomerById(id);
        } catch (Exception e) {
            throw new CustomerNotFoundException(id);
        }
    }

    @ApiOperation(value = "Cria um novo Cliente")
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> createNewCustomer(@RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(
                customerService.createNewCustomer(customerDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Atualiza os dados de um cliente")
    @RequestMapping(value = "/id/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        return customerService.updateCustomer(id, customerDTO);
    }

    @ApiOperation("Deleta um cliente")
    @DeleteMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
