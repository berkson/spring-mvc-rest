package guru.spring.berkson.services;

import guru.spring.berkson.api.v1.mapper.CustomerMapper;
import guru.spring.berkson.api.v1.model.CustomerDTO;
import guru.spring.berkson.domain.Customer;
import guru.spring.berkson.domain.Meta;
import guru.spring.berkson.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * Created by Berkson Ximenes
 * Date: 19/11/2021
 * Time: 21:39
 */
class CustomerServiceTest {
    private static final String FIRSTNAME = "John";

    Customer customer;

    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(CustomerMapper.INSTANCE, customerRepository);
        Meta customerMeta = new Meta(52, 12, 5, "/url/teste/p", "/url/teste/n");
        customer = new Customer(customerMeta, FIRSTNAME, "Jacusi", "/shop/customer/234");
    }

    @Test
    void getAllCustomers() {
        //given
        Meta meta = new Meta(31, 5, 1,
                "/shop/products/?page=1&limit5",
                "/shops/products?page=2$limit5");
        List<Customer> dtos = Arrays.asList(customer, new Customer(
                meta, "Julius", "Janos", "shop/customer/321"
        ));
        when(customerRepository.findAll()).thenReturn(dtos);
        //when
        List<CustomerDTO> customerDTOS = customerService.getAllCustomers();

        //then
        assertNotNull(customerDTOS);
        assertEquals(2, customerDTOS.size());
    }

    @Test
    void getCustomerByFirstName() {
        //given
        when(customerRepository.findByFirstName(FIRSTNAME)).thenReturn(customer);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByFirstName(FIRSTNAME);

        //then
        assertNotNull(customerDTO);
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
    }
}