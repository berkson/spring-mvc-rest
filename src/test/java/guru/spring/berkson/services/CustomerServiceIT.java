package guru.spring.berkson.services;

import guru.spring.berkson.api.v1.mapper.CustomerMapper;
import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.api.v1.model.MetaDTO;
import guru.spring.berkson.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Berkson Ximenes
 * Date: 22/11/2021
 * Time: 21:00
 */
@DataJpaTest
@ExtendWith(SpringExtension.class)
public class CustomerServiceIT {
    private static final String FIRSTNAME = "John";
    private static final String apiURI = "/api/v1/customers/id/";

    CustomerService customerService;
    CustomerMapper customerMapper;
    @Autowired
    CustomerRepository customerRepository;
    CustomerDTO client;

    @BeforeEach
    void setUp() {
        customerMapper = CustomerMapper.INSTANCE;
        MetaDTO newMeta = new MetaDTO(10, 6, 5,
                "/url/teste/h", "/url/teste/z");
        client = new CustomerDTO(newMeta, FIRSTNAME, "Jacusi", apiURI);
        customerService = new CustomerServiceImpl(customerMapper, customerRepository);
    }

    @Test
    void createNewCustomer() {
        CustomerDTO newCustomerDTO = customerService.createNewCustomer(client);

        assertNotNull(newCustomerDTO);
        assertEquals(FIRSTNAME, newCustomerDTO.getFirstname());
        assertNotEquals(apiURI, newCustomerDTO.getCustomerUrl());
        assertEquals(10, newCustomerDTO.getMeta().getCount());
        assertEquals(6, newCustomerDTO.getMeta().getLimite());
    }
}
