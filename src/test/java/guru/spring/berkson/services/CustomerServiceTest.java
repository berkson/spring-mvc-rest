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
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Created by Berkson Ximenes
 * Date: 19/11/2021
 * Time: 21:39
 */
class CustomerServiceTest {
    private static final String FIRSTNAME = "John";
    private static final Long CUSTOMER_ID = 5L;

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
        customer.setId(CUSTOMER_ID);
    }

    @Test
    void testGetAllCustomers() {
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
    void getCustomerByFirstname() {
        //given
        when(customerRepository.findByFirstname(FIRSTNAME)).thenReturn(customer);

        //when
        CustomerDTO customerDTO = customerService.getCustomerByFirstname(FIRSTNAME);

        //then
        assertNotNull(customerDTO);
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
    }

    @Test
    void getById() {
        //given
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(customer));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(CUSTOMER_ID);

        //then
        assertEquals(FIRSTNAME, customerDTO.getFirstname());
    }

    @Test
    void emptyGetById() {
        //given
        when(customerRepository.findById(CUSTOMER_ID)).thenReturn(Optional.of(new Customer()));

        //when
        CustomerDTO customerDTO = customerService.getCustomerById(CUSTOMER_ID);

        //then
        assertNotNull(customerDTO);
        assertNull(customerDTO.getFirstname());
        assertNull(customerDTO.getLastname());
    }

    @Test
    void getByIdDoNotExists() {
        //given
        when(customerRepository.findById(CUSTOMER_ID)).thenThrow(new NoSuchElementException());

        //then
        assertDoesNotThrow(() -> customerService.getCustomerById(CUSTOMER_ID));

    }

}